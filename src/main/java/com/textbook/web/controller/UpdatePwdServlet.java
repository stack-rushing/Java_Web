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

@WebServlet("/updatePwd")
public class UpdatePwdServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPwd = req.getParameter("oldPwd");
        String newPwd = req.getParameter("newPwd");
        String confirmPwd = req.getParameter("confirmPwd");

        // 获取当前登录的用户信息
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        String errorMsg = null;

        // 规则1：输入正确的旧密码
        if (!loginUser.getV_pass().equals(oldPwd)) {
            errorMsg = "旧密码输入错误！";
        }
        // 规则2：两次输入的新密码相同
        else if (!newPwd.equals(confirmPwd)) {
            errorMsg = "两次输入的新密码不一致！";
        }
        // 规则3：新密码长度不小于6位
        else if (newPwd.length() < 6) {
            errorMsg = "新密码长度不能小于6位！";
        }
        // 规则4：不允许出现6位完全相同的情况 (使用正则表达式校验：所有字符都和第一个字符相同)
        else if (newPwd.matches("^(.)\\1{5,}$")) {
            errorMsg = "新密码不允许出现完全相同的字符（如111111或aaaaaa）！";
        }

        // 如果有错误信息，带着错误信息跳回修改页面
        if (errorMsg != null) {
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("/updatePwd.jsp").forward(req, resp);
            return;
        }

        // 校验全部通过，执行修改数据库操作
        boolean success = userService.updatePassword(loginUser.getId(), newPwd);
        if (success) {
            // 修改成功后，更新 Session 中的密码，或者直接强制用户重新登录
            loginUser.setV_pass(newPwd);
            session.setAttribute("loginUser", loginUser);

            req.setAttribute("msg", "密码修改成功！");
            req.getRequestDispatcher("/updatePwd.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "系统异常，修改失败！");
            req.getRequestDispatcher("/updatePwd.jsp").forward(req, resp);
        }
    }
}