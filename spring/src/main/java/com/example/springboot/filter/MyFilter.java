package com.example.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter {

    // 使用SLF4J记录日志
    private Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
            throws IOException, ServletException {

        // 将ServletResponse强制类型转换为HttpServletResponse以处理HTTP响应头
        HttpServletResponse response = (HttpServletResponse) sResponse;

        // 设置CORS相关响应头，解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许任何来源访问
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 允许发送凭据（如Cookie）
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE"); // 允许的HTTP方法
        response.setHeader("Access-Control-Max-Age", "3600"); // 预检请求（OPTIONS请求）的缓存时间
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization"); // 允许的请求头字段
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 不缓存响应
        response.setHeader("Pragma", "no-cache"); // 兼容HTTP 1.0
        response.setHeader("Expires", "0"); // 兼容HTTP 1.0

        // 继续处理请求，将请求和响应传递给下一个过滤器或Servlet
        chain.doFilter(sRequest, sResponse);
    }

    @Override
    public void destroy() {
        // 销毁方法
    }
}
