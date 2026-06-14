package com.textbook.web.service;

import com.textbook.web.dao.BookDao;
import com.textbook.web.entity.Book;
import com.textbook.web.entity.PageBean;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public PageBean<Book> findBookByPage(int pageNo, int pageSize, String keyword) {
        PageBean<Book> pb = new PageBean<>();
        pb.setPageNo(pageNo);
        pb.setPageSize(pageSize);

        // 1. 查总条数并设置
        int totalCount = bookDao.getTotalCount(keyword);
        pb.setTotalCount(totalCount);

        // 2. 计算 LIMIT 的起始索引
        int start = (pageNo - 1) * pageSize;

        // 3. 查当前页的数据集合并设置
        List<Book> list = bookDao.getPageList(start, pageSize, keyword);
        pb.setList(list);

        return pb;
    }

    /**
     * 删除教材业务
     */
    public boolean deleteBook(int id) {
        return bookDao.deleteBookById(id);
    }

    /**
     * 添加教材业务
     */
    public boolean addBook(Book book) {
        return bookDao.addBook(book);
    }

    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }
}