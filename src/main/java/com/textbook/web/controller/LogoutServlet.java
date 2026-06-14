package com.textbook.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取当前的 Session
        HttpSession session = req.getSession();

        // 2. 销毁 Session（清除登录状态）
        session.invalidate();

        // 3. 重定向回登录页面
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}