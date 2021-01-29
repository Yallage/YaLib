package com.rabbitown.yalib.common.model;

import javax.mail.Session;

public class EMailServer {
    // Session实例:
    Session session;
    // 邮件服务器地址
    String serverAddress;
    // 邮件服务器端口
    int serverPort;
    // 是否需要用户认证
    boolean isAuth;
    // 是否需要TLS加密
    boolean isTLS;
    // 是否开启调试模式
    boolean isDebug;
    // 邮箱用户名
    String username;
    // 邮箱密码
    String password;

    public EMailServer(String serverAddress, int serverPort, boolean isAuth, boolean isTLS, boolean isDebug, String username, String password) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.isAuth = isAuth;
        this.isTLS = isTLS;
        this.isDebug = isDebug;
        this.username = username;
        this.password = password;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public boolean isTLS() {
        return isTLS;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setTLS(boolean TLS) {
        isTLS = TLS;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getServerPort() {
        return serverPort;
    }

    public Session getSession() {
        return session;
    }

    public String getServerAddress() {
        return serverAddress;
    }
}
