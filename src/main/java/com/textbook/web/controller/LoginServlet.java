package com.textbook.web.controller;

import com.textbook.web.entity.User;
import com.textbook.web.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// 这个注解定义了浏览器的访问路径
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取前端表单提交的账号和密码 (对应 input 标签的 name 属性)
        String v_name = req.getParameter("v_name");
        String v_pass = req.getParameter("v_pass");

        // 2. 调用 Service 层进行数据库验证
        User user = userService.login(v_name, v_pass);

        if (user != null) {
            // 登录成功：将查询到的用户信息存入 Session（这步非常关键，以后的权限拦截全靠它）
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", user);

            // 重定向到主页 (等下我们要把自带的 index.jsp 改造成主页)
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            // 登录失败：把错误信息放到 request 中，转发回登录页
            req.setAttribute("error", "账号或密码错误，请重新输入！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果用 GET 方式访问，也转交给 doPost 处理
        doPost(req, resp);
    }
}