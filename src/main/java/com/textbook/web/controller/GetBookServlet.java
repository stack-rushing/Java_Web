package com.textbook.web.controller;

import com.textbook.web.entity.Book;
import com.textbook.web.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getBook")
public class GetBookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookService.getBookById(id);
        req.setAttribute("book", book); // 把查到的书塞进 request
        req.getRequestDispatcher("/updateBook.jsp").forward(req, resp); // 转发到修改页面
    }
}