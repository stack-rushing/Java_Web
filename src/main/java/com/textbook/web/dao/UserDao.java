package com.textbook.web.dao;

import com.textbook.web.entity.User;
import com.textbook.web.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.util.List;
import java.sql.SQLException;

public class UserDao {
    // 创建 QueryRunner 对象，自动从我们写的 JDBCUtils 中获取连接池
    private QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    /**
     * 根据账号和密码查询用户 (登录功能)
     */
    public User login(String username, String password) {
        // 编写 SQL 语句，使用 ? 作为占位符防止 SQL 注入
        String sql = "SELECT * FROM t_user WHERE v_name = ? AND v_pass = ?";
        try {
            // BeanHandler 会自动把查询到的这行数据，打包成我们刚刚写的 User 实体类对象
            return runner.query(sql, new BeanHandler<>(User.class), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // 如果查不到或者报错，就返回 null
        }
    }

    /**
     * 根据用户 ID 修改密码
     */
    public boolean updatePassword(int userId, String newPassword) {
        String sql = "UPDATE t_user SET v_pass = ? WHERE id = ?";
        try {
            int rows = runner.update(sql, newPassword, userId);
            return rows > 0; // 如果影响的行数大于0，说明修改成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加新用户 (可用于添加管理员或教师)
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO t_user (v_name, v_pass, role) VALUES (?, ?, ?)";
        try {
            int rows = runner.update(sql, user.getV_name(), user.getV_pass(), user.getRole());
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取所有教师列表 (role = '1')
     */
    public List<User> getTeacherList() {
        String sql = "SELECT * FROM t_user WHERE role = '1'";
        try {
            // 注意这里用的是 BeanListHandler，因为查出来的是多条数据
            return runner.query(sql, new org.apache.commons.dbutils.handlers.BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}