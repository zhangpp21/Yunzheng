package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.LeaveRequestService;
import com.ydsy.util.BasicResultVO;

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

        Timestamp leaveRequestTime = Timestamp.valueOf(request.getParameter("leaveRequestTime"));
        String leaveRequestReason = request.getParameter("leaveRequestReason");

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
        leaveRequest.setLeaveRequestTime(leaveRequestTime);
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
