package com.rabbitown.yalib.common.model;

import javax.mail.internet.InternetAddress;

public class EMail {
    // 发件人邮箱
    InternetAddress mailFrom;
    // 收件人邮箱
    InternetAddress mailTo;
    // 主题
    String subject;
    // 内容
    String content;

    public EMail(InternetAddress mailFrom, InternetAddress mailTo, String subject, String content) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMailFrom(InternetAddress mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailTo(InternetAddress mailTo) {
        this.mailTo = mailTo;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public InternetAddress getMailFrom() {
        return mailFrom;
    }

    public InternetAddress getMailTo() {
        return mailTo;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }
}
