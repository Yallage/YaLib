package com.rabbitown.yalib.util.database;

import java.sql.Connection;

/**
 * 因为IDEA的图标包没有DAO的图标 所以忽略DAO包吧！
 * Happy Lazy
 */
public interface Database {
    /**
     * 尝试加载数据库
     * 若数据库不存在则会创建一个新的数据库db文件
     * 支持数据库 若使用的数据库不支持这个方法则会返回null
     * 支持数据库 SQLite
     * @param databaseName 数据库名称
     */
    Connection loadDatabase(String databaseName);
    /**
     * 尝试加载数据库
     * 支持数据库 若使用的数据库不支持这个方法则会返回null
     * 支持数据库 MySQL
     * @param databaseURL 数据库链接
     * @param username 用户名
     * @param password 密码
     */
    public Connection loadDatabase(String databaseURL, String username, String password);
    /**
     * 尝试使用数据库执行SQL语句
     * 若数据库不支持则会报错
     * //TODO: SQL语句注入检测 Happy Lazy
     * 支持数据库 若使用的数据库不支持这个方法则会返回null
     * 支持数据库 SQLite, MySQL
     * @param connection 数据库连接
     */
    void execute(Connection connection, String sql);
}
