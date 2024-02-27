package com.ydsy.web.servlet;

import com.ydsy.pojo.User;
import com.ydsy.service.LeaveRequestService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/leaveRequestServlet")
public class LeaveRequestServlet extends HttpServlet {

    private final LeaveRequestService service = new LeaveRequestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timestamp leaveRequestTime = Timestamp.valueOf(request.getParameter("leaveRequestTime"));
        String leaveRequestReason = request.getParameter("leaveRequestReason");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        service.addNeoLeaveRequest(user.getUserId(),leaveRequestTime,leaveRequestReason);

        response.setContentType("application/json;charset=utf-8");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
