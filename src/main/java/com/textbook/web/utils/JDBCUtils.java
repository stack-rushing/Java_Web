package com.textbook.web.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    // 定义一个连接池对象
    private static DataSource dataSource;

    // 静态代码块，在类加载时自动执行，只执行一次
    static {
        try {
            // 1. 加载 resources 目录下的 druid.properties 配置文件
            Properties properties = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);

            // 2. 使用 Druid 工厂类初始化连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接池初始化失败，请检查 druid.properties 配置！");
        }
    }

    /**
     * 获取数据库连接池对象 (后续供 DBUtils 使用)
     * @return DataSource
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 从连接池中获取一个数据库连接
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}