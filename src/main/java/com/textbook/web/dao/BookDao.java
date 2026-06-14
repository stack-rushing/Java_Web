package com.textbook.web.dao;

import com.textbook.web.entity.Book;
import com.textbook.web.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    /**
     * 获取总记录数 (支持模糊查询)
     */
    public int getTotalCount(String keyword) {
        String sql = "SELECT COUNT(*) FROM t_book WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += "AND (tname LIKE ? OR tauthor LIKE ? OR press LIKE ? OR isbn LIKE ?) ";
            String likeKey = "%" + keyword + "%";
            params.add(likeKey); params.add(likeKey); params.add(likeKey); params.add(likeKey);
        }

        try {
            // ScalarHandler 用于处理单值查询结果（如 COUNT(*)）
            Long count = runner.query(sql, new ScalarHandler<>(), params.toArray());
            return count.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 分页查询教材数据 (支持模糊查询)
     */
    public List<Book> getPageList(int start, int pageSize, String keyword) {
        String sql = "SELECT * FROM t_book WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += "AND (tname LIKE ? OR tauthor LIKE ? OR press LIKE ? OR isbn LIKE ?) ";
            String likeKey = "%" + keyword + "%";
            params.add(likeKey); params.add(likeKey); params.add(likeKey); params.add(likeKey);
        }

        // 加入 MySQL 分页关键字 LIMIT
        sql += " LIMIT ?, ? ";
        params.add(start);
        params.add(pageSize);

        try {
            return runner.query(sql, new BeanListHandler<>(Book.class), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 根据 ID 删除教材
     */
    public boolean deleteBookById(int id) {
        String sql = "DELETE FROM t_book WHERE id = ?";
        try {
            int rows = runner.update(sql, id);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加新教材
     */
    public boolean addBook(Book book) {
        String sql = "INSERT INTO t_book (tname, tauthor, press, price, isbn, tdescript, count, type, pic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int rows = runner.update(sql,
                    book.getTname(), book.getTauthor(), book.getPress(),
                    book.getPrice(), book.getIsbn(), book.getTdescript(),
                    book.getCount(), book.getType(), ""); // 图片暂时传空字符串
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据 ID 查询单本教材 (用于数据回显)
     */
    public Book getBookById(int id) {
        String sql = "SELECT * FROM t_book WHERE id = ?";
        try {
            return runner.query(sql, new BeanHandler<>(Book.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新教材信息
     */
    public boolean updateBook(Book book) {
        String sql = "UPDATE t_book SET tname=?, tauthor=?, press=?, price=?, isbn=?, tdescript=?, count=?, type=? WHERE id=?";
        try {
            int rows = runner.update(sql,
                    book.getTname(), book.getTauthor(), book.getPress(),
                    book.getPrice(), book.getIsbn(), book.getTdescript(),
                    book.getCount(), book.getType(), book.getId());
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}