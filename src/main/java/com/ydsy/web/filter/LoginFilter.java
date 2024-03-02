package com.ydsy.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        //判断访问资源路径是否和登陆注册相关
        String[] urls = {"/login.jsp","/imgs/","/css/","/loginServlet","/registerServlet","/checkCodeServlet"};
        //获取当前访问的路径
        String url = req.getRequestURL().toString();
        for(String u : urls){
            if(url.contains(u)){
                //找到了
                //放行
                chain.doFilter(request,response);
                return;
            }
        }
        //1.判断session中是否有user
        HttpSession session =req.getSession();
        Object user = session.getAttribute("user");
        //2.判断user是否为null
        if (user != null){

        }else{
            req.setAttribute("login_msg","您尚未登陆！");
            req.getRequestDispatcher("login.jsp").forward(req,response);
        }
        //放行
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
