package com.rabbitown.yalib.test;

import com.rabbitown.yalib.database.MySQL;

import java.sql.*;

/**
 * 测试类 可随时删除
 *
 * @author Hanbings
 */
public class TestMySQL {
    Connection conn;

    public void Test() {
        Statement stmt = null;
        // 加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获得数据库的连接
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://192.168.59.129:3306/YaLib", "YaLib", "3219065882");
            // 执行查询
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
