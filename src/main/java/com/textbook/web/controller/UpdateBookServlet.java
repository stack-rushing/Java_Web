package com.textbook.web.controller;

import com.textbook.web.entity.Book;
import com.textbook.web.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        book.setId(Integer.parseInt(req.getParameter("id"))); // 注意这里多了一个隐藏的 ID
        book.setTname(req.getParameter("tname"));
        book.setTauthor(req.getParameter("tauthor"));
        book.setPress(req.getParameter("press"));
        book.setIsbn(req.getParameter("isbn"));
        book.setType(req.getParameter("type"));
        book.setTdescript(req.getParameter("tdescript"));
        book.setPrice(Double.parseDouble(req.getParameter("price")));
        book.setCount(Integer.parseInt(req.getParameter("count")));

        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/bookList");
    }
}