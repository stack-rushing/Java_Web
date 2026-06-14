package com.textbook.web.controller;

import com.textbook.web.entity.Book;
import com.textbook.web.entity.PageBean;
import com.textbook.web.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取当前页码（如果第一次访问，默认为第 1 页）
        String pageNoStr = req.getParameter("pageNo");
        int pageNo = 1;
        if (pageNoStr != null && !pageNoStr.trim().isEmpty()) {
            pageNo = Integer.parseInt(pageNoStr);
        }

        // 设置每页显示 5 条数据
        int pageSize = 5;

        // 2. 获取搜索关键字
        String keyword = req.getParameter("keyword");

        // 3. 调用 Service 拿到包装好的分页对象
        PageBean<Book> pb = bookService.findBookByPage(pageNo, pageSize, keyword);

        // 4. 将数据和搜索词存入 request，转发给 JSP 渲染
        req.setAttribute("pb", pb);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("/bookList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}