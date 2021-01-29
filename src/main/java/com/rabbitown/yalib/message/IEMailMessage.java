package com.rabbitown.yalib.message;

import com.rabbitown.yalib.common.model.EMail;

public interface IEMailMessage {
    /**
     * 发送一件邮件
     *
     * @param mail 邮件
     */
    void sendMail(EMail mail);
}
