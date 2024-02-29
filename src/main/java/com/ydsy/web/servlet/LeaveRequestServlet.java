package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.LeaveRequestService;
import com.ydsy.service.impl.MeetingService;
import com.ydsy.util.BasicResultVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/leaveRequestServlet")
public class LeaveRequestServlet extends HttpServlet {

    private final MeetingService meetingService = new MeetingService();
    private final LeaveRequestService leaveRequestService = new LeaveRequestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int leaveRequestMeeting = Integer.parseInt(request.getParameter("leaveRequestMeeting"));
        String leaveRequestReason = request.getParameter("leaveRequestReason");

        /*
        判断会议id是否存在
         */
        List<Integer> meetingIds = meetingService.selectAllMeetingId();
        /*
        存在
         */
        if (meetingIds.contains(leaveRequestMeeting)) {
            /*
          获取session中的user数据
         */
            HttpSession session = request.getSession();
            User student = (User) session.getAttribute("user");

        /*
        将未审批假条数据存储在pojo中
         */
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setApplicantId(student.getUserId());
            leaveRequest.setLeaveRequestMeeting(leaveRequestMeeting);
            leaveRequest.setLeaveRequestReason(leaveRequestReason);

        /*
        将未审批假条数据存储到数据库中
         */
            leaveRequestService.addNeoLeaveRequest(leaveRequest);

        /*
        向前端返回成功码和未审批假条数据
         */
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.success("提交申请成功", leaveRequest)));
        }
        /*
        不存在
         */
        else {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("会议id不存在")));
        }

        /*
        重定向回此界面
         */
        response.sendRedirect(String.valueOf(request.getRequestURL()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
