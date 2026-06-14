package com.textbook.web.service;

import com.textbook.web.dao.UserDao;
import com.textbook.web.entity.User;

public class UserService {
    // 实例化 UserDao
    private UserDao userDao = new UserDao();

    /**
     * 处理登录业务
     */
    public User login(String username, String password) {
        // 这里可以加一些额外的业务逻辑，比如判断密码长度等
        // 目前直接调用 Dao 层去数据库查
        return userDao.login(username, password);
    }

    /**
     * 修改密码业务
     */
    public boolean updatePassword(int userId, String newPassword) {
        return userDao.updatePassword(userId, newPassword);
    }

    /**
     * 添加用户业务
     */
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    /**
     * 获取所有教师业务
     */
    public java.util.List<User> getTeacherList() {
        return userDao.getTeacherList();
    }
}