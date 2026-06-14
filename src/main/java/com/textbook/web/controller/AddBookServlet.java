package com.textbook.web.controller;

import com.textbook.web.entity.Book;
import com.textbook.web.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取表单数据
        String tname = req.getParameter("tname");
        String tauthor = req.getParameter("tauthor");
        String press = req.getParameter("press");
        String isbn = req.getParameter("isbn");
        String type = req.getParameter("type");
        String tdescript = req.getParameter("tdescript");

        // 注意转换数字类型
        double price = Double.parseDouble(req.getParameter("price"));
        int count = Integer.parseInt(req.getParameter("count"));

        // 2. 封装到 Book 对象中
        Book book = new Book();
        book.setTname(tname);
        book.setTauthor(tauthor);
        book.setPress(press);
        book.setIsbn(isbn);
        book.setType(type);
        book.setTdescript(tdescript);
        book.setPrice(price);
        book.setCount(count);

        // 3. 调用 Service 保存到数据库
        bookService.addBook(book);

        // 4. 保存成功后，重定向回教材列表页查看最新数据
        resp.sendRedirect(req.getContextPath() + "/bookList");
    }
}