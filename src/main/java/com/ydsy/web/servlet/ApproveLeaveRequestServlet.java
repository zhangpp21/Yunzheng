package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.Participation;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.LeaveRequestService;
import com.ydsy.service.impl.ParticipationService;
import com.ydsy.util.BasicResultVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ApproveLeaveRequestServlet")
public class ApproveLeaveRequestServlet extends HttpServlet {

    private final LeaveRequestService leaveRequestService = new LeaveRequestService();
    private final ParticipationService participationService = new ParticipationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
          获取session中的user数据
         */
        HttpSession session = request.getSession();
        User manager = (User) session.getAttribute("user");

        /*
        将审批数据存储在pojo中
         */
        int leaveRequestId = Integer.parseInt(request.getParameter("leaveRequestId"));
        int approveStatus = Integer.parseInt(request.getParameter("approveStatus"));
        int approveId = manager.getUserId();

        LeaveRequest leaveRequest = leaveRequestService.selectByLeaveRequestId(leaveRequestId);
        leaveRequest.setApproveStatus(approveStatus);
        leaveRequest.setApproveId(approveId);

        /*
        将pojo中的审批数据更新到数据库中
         */
        leaveRequestService.UpdateLeaveRequestApproval(leaveRequest);

        /*
        获取并更新参会情况
         */
        Participation participation = participationService.selectByMeetingIdAndParticipantId
            (leaveRequest.getLeaveRequestMeeting(), leaveRequest.getApplicantId());
        if (approveStatus == 1)         // 批准
        {
            participation.setLeaveStatus("Approved");
        } else if (approveStatus == 0)            // 不批准
        {
            participation.setLeaveStatus("Rejected");
        }
        participationService.updateLeaveStatus(participation);

        /*
        向前端返回成功码和审批后的假条数据
         */
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(BasicResultVO.success("审批成功", leaveRequest)));

        /*
        重定向回查询总假条页面
         */
        response.sendRedirect(request.getContextPath() + "/leaveRequestServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
