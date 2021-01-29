package com.rabbitown.yalib.module.database.connecter;

import com.rabbitown.yalib.module.database.Database;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseImpl implements Database {
    @Override
    public Connection loadDatabase(String databaseName) {
        Connection connection;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            Bukkit.getServer().getLogger().info("[SQLITE][INFO] 数据库加载成功");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getServer().getLogger().warning("[SQLITE][ERROR] 连接数据库发生错误");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Connection loadDatabase(String databaseURL, String username, String password) {
        return null;
    }

    @Override
    public void execute(Connection connection, String sql) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
