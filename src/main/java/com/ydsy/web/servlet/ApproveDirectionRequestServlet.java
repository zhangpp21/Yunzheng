package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.DirectionApplication;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.DirectionApplicationService;
import com.ydsy.service.impl.DirectionService;
import com.ydsy.service.impl.UserService;
import com.ydsy.util.BasicResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/approveDirectionRequestServlet")
public class ApproveDirectionRequestServlet extends HttpServlet {

    private final DirectionApplicationService directionApplicationService = new DirectionApplicationService();
    private final UserService userService = new UserService();
    private final DirectionService directionService = new DirectionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * 获取session中的user数据
         */
        HttpSession session = request.getSession();
        User manager = (User) session.getAttribute("user");

        /**
         * 将审批数据存储到pojo中
         */
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        int approvalStatus = Integer.parseInt(request.getParameter("approvalStatus"));
        int approverId = manager.getUserId();

        DirectionApplication directionApplication = directionApplicationService.selectByApplicationId(applicationId);
        directionApplication.setApproverId(approverId);
        directionApplication.setApprovalStatus(approvalStatus);

        /**
         * 将pojo中的审批数据更新到数据库中
         */
        directionApplicationService.updateApproverIdAndStatus(directionApplication);

        /**
         * 获取并修改用户方向和方向人数
         */

        if (approvalStatus == 1) {
            manager.setDirectionId(directionApplication.getApplicationDirection());
            userService.updateAll(manager);
            directionService.updateCount();
        }

        /**
         * 向前端返回成功码和审批后的假条数据
         */
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(BasicResultVO.success("审批成功", directionApplication)));

        /**
         * 重定向回查询所有方向请求页面
         */
        response.sendRedirect(request.getContextPath() + "/selectAllDirectionRequestServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
