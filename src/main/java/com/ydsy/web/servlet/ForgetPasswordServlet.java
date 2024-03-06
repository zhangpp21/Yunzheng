package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.UserService;
import com.ydsy.util.BasicResultVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/forgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getReader().readLine();
        JSONObject jsonObject = JSON.parseObject(json);
        String account = jsonObject.getString("account");// 假设用户提交了用户名
        String password = jsonObject.getString("password");
        // 调用 service 查询
        User user = service.verifyUser(account);
        if (user == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("用户信息不存在，请注册用户")));
            return;
        }
        String newPassword = user.getCheckCode();

        // 存储新密码到数据库或缓存中，以便用户下次登录时验证
        boolean updatePassword = service.updatePassword(account, newPassword);
        if(updatePassword){
            if(Objects.equals(password, newPassword)){
                // 向用户返回成功消息
                response.setContentType("application/json;charset=utf-8");
                // 返回成功的 JSON 响应
                response.getWriter().write(JSON.toJSONString(BasicResultVO.success("修改密码成功")));
            }
        }else{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("修改密码失败")));
        }

    }

}

