package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.Participation;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.LeaveRequestService;
import com.ydsy.service.impl.MeetingService;
import com.ydsy.service.impl.ParticipationService;
import com.ydsy.util.BasicResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/leaveRequestServlet")
public class LeaveRequestServlet extends HttpServlet {

    private final MeetingService meetingService = new MeetingService();
    private final LeaveRequestService leaveRequestService = new LeaveRequestService();
    private final ParticipationService participationService = new ParticipationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int leaveRequestMeeting = Integer.parseInt(request.getParameter("leaveRequestMeeting"));
        String leaveRequestReason = request.getParameter("leaveRequestReason");

        /**
         * 获取session中的user数据
         */
        HttpSession session = request.getSession();
        User student = (User) session.getAttribute("user");

        /**
         * 判断会议id存不存在
         */
        if (!meetingService.selectAllMeetingId().contains(leaveRequestMeeting)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("会议id不存在")));
            response.sendRedirect(request.getRequestURI());
            return;
        }

        /**
         * 判断此假条是否已经申请过
         */
        Participation participation = participationService.selectByMeetingIdAndParticipantId(leaveRequestMeeting, student.getUserId());
        if (participation != null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.success("你已发送过此会议的请假申请",participation)));
            response.sendRedirect(request.getRequestURI());
            return;
        }

        /**
         * 将未审批假条数据存储在pojo中
         */
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setApplicantId(student.getUserId());

        leaveRequest.setLeaveRequestMeeting(leaveRequestMeeting);
        leaveRequest.setLeaveRequestReason(leaveRequestReason);

        /**
         * 将未审批假条数据存储到数据库中
         */
        leaveRequestService.addNeoLeaveRequest(leaveRequest);

        /**
         * 向前端返回成功码和未审批假条数据
         */
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(BasicResultVO.success("提交申请成功", leaveRequest)));


        /**
         * 重定向回此界面
         */
        response.sendRedirect(request.getRequestURI());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
