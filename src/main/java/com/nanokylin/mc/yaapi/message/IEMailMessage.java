package com.nanokylin.mc.yaapi.message;

import com.nanokylin.mc.yaapi.common.model.EMail;

public interface IEMailMessage {
    /**
     * 发送一件邮件
     *
     * @param mail 邮件
     */
    void sendMail(EMail mail);
}
