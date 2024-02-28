package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.User;
import com.ydsy.service.LeaveRequestService;
import com.ydsy.util.RespStatusEnum;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/leaveRequestServlet")
public class LeaveRequestServlet extends HttpServlet {

    private final LeaveRequestService leaveRequestService = new LeaveRequestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String string = request.getParameter("leaveRequestTime");
        Timestamp leaveRequestTime = Timestamp.valueOf(request.getParameter("leaveRequestTime"));
        String leaveRequestReason = request.getParameter("leaveRequestReason");
        // HttpSession session = request.getSession();
        // User user = (User) session.getAttribute("user");

        User user = leaveRequestService.selectUserByUserId(1);

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setApplicantId(user.getUserId());
        leaveRequest.setLeaveRequestTime(leaveRequestTime);
        leaveRequest.setLeaveRequestReason(leaveRequestReason);

        leaveRequestService.addNeoLeaveRequest(leaveRequest);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(RespStatusEnum.SUCCESS));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
