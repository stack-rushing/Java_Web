package com.textbook.web.controller;

import com.textbook.web.entity.User;
import com.textbook.web.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAdmin")
public class AddAdminServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String v_name = req.getParameter("v_name");
        String v_pass = req.getParameter("v_pass");

        // 封装 User 对象
        User user = new User();
        user.setV_name(v_name);
        user.setV_pass(v_pass);
        user.setRole("0"); // 核心：0代表教务管理员，1代表教师

        // 调用 Service 保存
        boolean success = userService.addUser(user);

        if (success) {
            req.setAttribute("msg", "✨ 教务管理员 【" + v_name + "】 添加成功！");
        } else {
            req.setAttribute("error", "❌ 添加失败，可能是系统异常或账号已存在！");
        }

        // 带着提示信息回到添加页面
        req.getRequestDispatcher("/addAdmin.jsp").forward(req, resp);
    }
}