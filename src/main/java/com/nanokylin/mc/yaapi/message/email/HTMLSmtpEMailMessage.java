package com.nanokylin.mc.yaapi.message.email;

import com.nanokylin.mc.yaapi.common.model.EMail;
import com.nanokylin.mc.yaapi.common.model.EMailServer;
import com.nanokylin.mc.yaapi.message.IEMailMessage;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class HTMLSmtpEMailMessage implements IEMailMessage {
    static Session session;

    public HTMLSmtpEMailMessage(EMailServer mailServer) {
        Properties props = new Properties();
        props.put("mail.smtp.host", mailServer.getServerAddress()); // SMTP主机名
        props.put("mail.smtp.port", mailServer.getServerPort()); // 主机端口号
        props.put("mail.smtp.auth", mailServer.isAuth()); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", mailServer.isTLS()); // 启用TLS加密
        // 获取Session实例:
        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailServer.getUsername(), mailServer.getPassword());
            }
        });
        // 开启调试模式
        session.setDebug(mailServer.isDebug());
        // 返回实例
        mailServer.setSession(session);
    }

    /**
     * 发送一封HTML邮件
     */
    @Override
    public void sendMail(EMail mail) {
        MimeMessage message = new MimeMessage(session);
        try {
            // 设置发送方地址:
            message.setFrom(mail.getMailFrom());
            // 设置接收方地址:
            message.setRecipient(Message.RecipientType.TO, mail.getMailTo());
            // 设置邮件主题:
            message.setSubject(mail.getSubject(), "UTF-8");
            // 设置邮件正文:
            message.setText(mail.getContent(), "UTF-8", "html");
            // 发送:
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
