package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.DirectionApplication;
import com.ydsy.pojo.LeaveRequest;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.DirectionApplicationService;
import com.ydsy.util.BasicResultVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllDirectionRequestServlet")
public class SelectAllDirectionRequestServlet extends HttpServlet {

    private final DirectionApplicationService directionApplicationService = new DirectionApplicationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 获取session中的user数据
         */
        HttpSession session = request.getSession();
        User manager = (User) session.getAttribute("user");

        /**
         * 获取本方向的所有未审批的方向申请数据
         */
        List<DirectionApplication> directionApplications = directionApplicationService.selectAllByApplicantDirection(manager);

        /**
         * 向前端返回成功码和未审批的所有方向申请数据
         */
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(BasicResultVO.success("查看总假条成功", directionApplications)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
