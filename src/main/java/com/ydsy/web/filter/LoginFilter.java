package com.ydsy.web.filter;

import com.ydsy.pojo.User;
import com.ydsy.util.JobEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("")
public class LoginFilter implements Filter {

    /**
     * 把不需要拦截的网址写入对应的职位端的String组即可
     * 未登录需重定向的登录界面url填写在在末尾对应提示处
     *
     * @param request  the <code>ServletRequest</code> object contains the client's request
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "4200");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response);

        return;

        /**
         * 对所有人都不拦截的网址
        String[] urls = {"/imgs/", "/css/", "/loginServlet", "/registerServlet", "index.jsp"};
        String url = req.getRequestURL().toString();
        for (String u : urls) {
            if (url.contains(u)) {
                chain.doFilter(request, response);
                return;
            }
        }

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        *//**
         * 对学生端不拦截的网址
         *//*
        String[] studentUrls = {"/leaveRequestServlet"};
        *//**
         * 对管理端不拦截的网址
         *//*
        String[] managerUrls = {"/selectAllLeaveRequestServlet", "/approveLeaveRequestServlet"};
        *//**
         * 对大总管不拦截的网址
         *//*
        String[] bigManagerUrls = {};

        if (user != null) {
            if (user.getJobId() == JobEnum.JOB_STUDENT.getJobId()) {
                for (String u : studentUrls) {
                    if (url.contains(u)) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
            } else if (user.getJobId() == JobEnum.JOB_MANAGER.getJobId()) {
                for (String u : managerUrls) {
                    if (url.contains(u)) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
            } else if (user.getJobId() == JobEnum.JOB_BIG_MANAGER.getJobId()) {
                for (String u : bigManagerUrls) {
                    if (url.contains(u)) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        }

        *//**
         * 没有登录, 返回登录界面
         *//*
        else {
            req.setAttribute("login_msg", "您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        }*/
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
