package com.ydsy.web.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String method = httpRequest.getMethod();

        // 设置允许跨域访问的域名，这里假设只允许特定的域名访问
        // 你可以根据实际需求设置为"*"表示允许所有域名
        String allowedOrigin = "http://example.com";
        httpResponse.setHeader("Access-Control-Allow-Origin", allowedOrigin);

        // 设置允许的请求方法
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

        // 设置允许携带的头部信息
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        // 设置预检请求的缓存时间（2小时）
        httpResponse.setHeader("Access-Control-Max-Age", "7200");

        // 如果是OPTIONS请求，则直接返回，不继续执行后续操作
        if ("OPTIONS".equalsIgnoreCase(method)) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            // 继续执行后续操作
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 过滤器销毁时调用的方法，通常不需要实现
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 过滤器初始化时调用的方法，通常不需要实现
    }
}
