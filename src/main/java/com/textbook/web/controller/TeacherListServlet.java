package com.textbook.web.controller;

import com.textbook.web.entity.User;
import com.textbook.web.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacherList")
public class TeacherListServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 调用 Service 查出所有教师
        List<User> teacherList = userService.getTeacherList();

        // 2. 存入 request
        req.setAttribute("teacherList", teacherList);

        // 3. 转发到 JSP 页面展示
        req.getRequestDispatcher("/teacherList.jsp").forward(req, resp);
    }
}