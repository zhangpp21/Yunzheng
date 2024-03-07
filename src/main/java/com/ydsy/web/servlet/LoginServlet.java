package com.ydsy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.UserService;
import com.ydsy.util.BasicResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型为 JSON
        response.setContentType("application/json;charset=utf-8");

        // 使用 BufferedReader 读取请求体中的 JSON 数据
        BufferedReader reader = request.getReader();
        String jsonString = reader.readLine();

        // 使用 JSON 库解析 JSON 数据为 User 对象
        User user = JSON.parseObject(jsonString, User.class);

        // 获取用户名和密码
        String account = user.getAccount();
        String password = user.getPassword();

        // 调用 service 查询
        User authenticatedUser = service.login(account, password);

        // 根据查询结果设置响应内容
        if (authenticatedUser != null) {
            // 登录成功
            // 将登录成功后的用户对象存储到 session
            request.getSession().setAttribute("user", authenticatedUser);
            // 返回成功的 JSON 响应
            response.getWriter().write(JSON.toJSONString(BasicResultVO.success("登录成功")));
            // 重定向到基本 Servlet
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/UpdateProfileServlet");
        } else {
            // 登录失败
            // 返回失败的 JSON 响应
            response.getWriter().write(JSON.toJSONString(BasicResultVO.fail("用户名或密码错误")));
        }
    }

    // 如果需要处理 GET 请求，可以保留 doGet 方法，但通常登录不需要 GET 请求
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 可以选择处理 GET 请求，或简单地重定向到 POST 请求
        response.sendRedirect(request.getRequestURI() + "?error=unsupported_method");
    }
}