package com.ydsy.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        // 允许来自所有域的跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的请求方法
        response.setHeader("Access-Control-Allow-Methods", "*");
        // 允许的请求头部信息
        response.setHeader("Access-Control-Allow-Headers", "*");
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }

    // 可选的初始化和销毁方法
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化代码
    }

    @Override
    public void destroy() {
        // 销毁代码
    }

}
