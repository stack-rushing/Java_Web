package com.textbook.web.controller;

import com.textbook.web.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取前端点击删除时传过来的教材 ID
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            int id = Integer.parseInt(idStr);
            // 2. 调用 Service 执行删除
            bookService.deleteBook(id);
        }

        // 3. 删除完成后，重定向回教材列表页（它会自动重新查询最新数据并展示）
        resp.sendRedirect(req.getContextPath() + "/bookList");
    }
}