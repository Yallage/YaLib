package com.rabbitown.yalib.database;

import java.sql.Connection;

/**
 * 用于对数据库处理的统一执行方法集合类
 * 才发现SQL语句是通用的 拿到链接的实例之后直接起飞
 * 芜湖！芜湖！
 *
 * @author Hanbings
 */
public class Executor {
    // 连接的实例
    Connection connection = null;

    /**
     * 设置连接实例
     *
     * @param connection 传入已经连接数据库的实例
     * @author Hanbings
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * 获取已连接数据库的实例
     *
     * @return 已连接数据库的实例
     * @author Hanbings
     */
    public Connection getConnection() {
        return this.connection;
    }
}
