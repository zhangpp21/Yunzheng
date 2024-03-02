package com.ydsy.web.servlet;
import com.ydsy.pojo.User;
import com.ydsy.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收用户名和密码
        String account= request.getParameter("account");
        String password = request.getParameter("password");
        //2. 调用service查询
        User user = service.login(account, password);
        //3. 判断user释放为null
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        if(user != null){
           //登录成功
            //将登陆成功后的user对象，存储到session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/basicServlet");
        }else {

            // 登录失败,
            writer.write("登陆失败");
            // 存储错误信息到request
            request.setAttribute("login_msg","用户名或密码错误");

            // 跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}