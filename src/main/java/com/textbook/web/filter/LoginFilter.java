package com.textbook.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// /* 表示拦截全站所有的请求
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取当前用户请求的路径
        String uri = request.getRequestURI();

        // 2. 划定“白名单”：如果是去登录页、或者是提交登录请求，直接放行
        if (uri.contains("/login.jsp") || uri.contains("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. 检查 Session 中是否有我们刚才登录成功时存入的 "loginUser"
        HttpSession session = request.getSession();
        Object user = session.getAttribute("loginUser");

        if (user != null) {
            // 已经登录过，放行
            filterChain.doFilter(request, response);
        } else {
            // 没有登录，拦截下来，重定向回登录页
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}