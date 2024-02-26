package com.ydsy.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter("")
public class BasicFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
