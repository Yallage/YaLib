package com.rabbitown.yalib.database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;

public class MySQL {
    // 数据库驱动
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // 数据库链接
    private String URL = "jdbc:mysql://localhost:3306/test_demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // 数据库用户名
    private String user = "root";
    // 数据库密码
    private String password = "12345678";
    // 数据库版本
    private int mySqlVersion = 5;
    // 链接实例
    private Connection connection = null;

    /**
     * 设置数据库链接
     *
     * @param URL 数据库链接
     * @author Hanbings
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * 设置数据库用户名
     *
     * @param user 数据库用户名
     * @author Hanbings
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 设置数据库密码
     *
     * @param password 数据库密码
     * @author Hanbings
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 设置驱动字符串
     *
     * @param JDBC_DRIVER 驱动包名称
     * @author Hanbings
     */
    public void setJDBC_DRIVER(String JDBC_DRIVER) {
        this.JDBC_DRIVER = JDBC_DRIVER;
    }

    /**
     * 设置数据库版本号
     *
     * @param mySqlVersion 指定的数据库版本 8 或 其他的数字
     */
    public void setMySqlVersion(int mySqlVersion) {
        this.mySqlVersion = mySqlVersion;
    }

    /**
     * 启动数据库链接 必须传入参数的方法
     * 警告：此方法将更改原本数据库参数的内部变量值
     *
     * @param URL          数据库链接
     * @param user         数据库用户名
     * @param password     数据库密码
     * @param mySqlVersion 数据库版本
     * @return 一个已数据库链接的实例
     * @author Hanbings
     */
    public Connection setConnection(String URL, String user, String password, int mySqlVersion) {
        this.setURL(URL);
        this.setUser(user);
        this.setPassword(password);
        this.setMySqlVersion(mySqlVersion);
        if (getMySqlVersion() == 8) {
            this.setJDBC_DRIVER("com.mysql.cj.jdbc.Drive");
        } else {
            this.setJDBC_DRIVER("com.mysql.jdbc.Driver");
        }
        // 注册驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 设置数据库
        try {
            this.connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    /**
     * 启动数据库链接 提前设置了参数的方法
     * 警告：必须是在设置了连接数据库必须的参数才能使用此方法
     *
     * @return 一个已数据库链接的实例
     */
    public Connection setConnection() {
        if (getMySqlVersion() == 8) {
            this.setJDBC_DRIVER("com.mysql.cj.jdbc.Drive");
        } else {
            this.setJDBC_DRIVER("com.mysql.jdbc.Driver");
        }
        // 注册驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 设置数据库
        try {
            this.connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取数据库链接
     *
     * @return 数据库链接
     * @author Hanbings
     */
    public String getURL() {
        return URL;
    }

    /**
     * 获取用户名
     *
     * @return 数据库用户名
     * @author Hanbings
     */
    public String getUser() {
        return user;
    }

    /**
     * 获取密码MD5
     *
     * @return 一个MD5的密码
     * @author Hanbings
     */
    public String getPassword() {
        return getMD5(password, 0, password.length());
    }

    /**
     * 获取驱动字符串
     *
     * @return 驱动字符串
     * @author Hanbings
     */
    public String getJDBC_DRIVER() {
        return JDBC_DRIVER;
    }

    /**
     * 获取MySQL版本
     *
     * @return MySQL版本
     * @author Hanbings
     */
    public int getMySqlVersion() {
        return mySqlVersion;
    }

    /**
     * 获取已连接数据库的实例
     *
     * @return 已连接数据库的实例
     * @author Hanbings
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * 生成MD5
     *
     * @param s 输入的字符串
     * @return 生成后的MD5
     * @author Hanbings
     */
    public String getMD5(String s, int start, int end) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes(StandardCharsets.UTF_8);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).substring(start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
