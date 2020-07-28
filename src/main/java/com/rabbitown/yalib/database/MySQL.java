package com.rabbitown.yalib.database;

import java.sql.*;

public class MySQL {
    // 数据库链接
    String dataBaseURL = "jdbc:mysql://localhost:3306/test_demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // 数据库用户名
    String dataBaseUser = "root";
    // 数据库密码
    String passWord = "12345678";
    // 链接实例
    Connection connection = null;

    /**
     * 链接数据库并返回connection实例
     *
     * @author Hanbings
     */
    public Connection getConnection() {
        try {
            this.connection = DriverManager.getConnection(dataBaseURL, dataBaseUser, passWord);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.connection;
    }

    /**
     * 获取数据库链接
     *
     * @return 数据库链接
     * @author Hanbings
     */
    public String getGetDataBaseURL() {
        return dataBaseURL;
    }

    /**
     * 获取数据库用户名
     *
     * @return 数据库用户名
     * @author Hanbings
     */
    public String getDataBaseUser() {
        return dataBaseUser;
    }

    /**
     * 获取数据库密码
     *
     * @return 密码字符串
     * @author Hanbings
     */
    public String getPassWord() {
        return "抱歉 这个不能给";
    }


    /**
     * 设置数据库链接
     *
     * @param dataBaseURL 数据库链接
     * @author Hanbings
     */
    public void setDataBaseURL(String dataBaseURL) {
        this.dataBaseURL = dataBaseURL;
    }

    /**
     * 设置数据库链接
     *
     * @param dataBaseUser 数据库用户
     * @author Hanbings
     */
    public void setDataBaseUser(String dataBaseUser) {
        this.dataBaseUser = dataBaseUser;
    }

    /**
     * 设置数据库密码
     *
     * @param dataBasePassword 数据库密码
     * @author Hanbings
     */
    public Boolean setDataBasePassword(String dataBasePassword) {
        this.passWord = dataBasePassword;
    }

}
