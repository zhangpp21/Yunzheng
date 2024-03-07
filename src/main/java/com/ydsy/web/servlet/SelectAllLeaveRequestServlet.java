package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.LeaveRequestService;
import com.ydsy.util.BasicResultVO;
import com.ydsy.util.PojoReceiveRequestDataUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllLeaveRequestServlet")
public class SelectAllLeaveRequestServlet extends HttpServlet {

    private final LeaveRequestService leaveRequestService = new LeaveRequestService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpServletResponse resp = response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "4200");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        User user = PojoReceiveRequestDataUtil.pojoReceiveRequestDataUtil(request, User.class);

        /**
         * 获取session中的user数据
         */
        HttpSession session = request.getSession();
        User manager = (User) session.getAttribute("user");

        /**
         * 获取本方向的所有未审批的假条数据
         */
        List<LeaveRequest> leaveRequests = leaveRequestService.selectAllByApplicantDirection(manager);

        /**
         * 向前端返回成功码和未审批的所有假条数据
         */
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(BasicResultVO.success("查看总假条成功", leaveRequests)));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
