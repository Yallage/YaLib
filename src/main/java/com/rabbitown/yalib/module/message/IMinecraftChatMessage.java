package com.rabbitown.yalib.module.message;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public interface IMinecraftChatMessage {
    /**
     * 发送一条 Chat 消息
     *
     * @param message 消息内容
     * @param player  接受消息的玩家
     */
    void sendChatMessage(String message, Player player);

    /**
     * 发送一组 Chat 消息
     * <b>警告： 若不使用Async实现 此方法可能会长时间阻塞主线程</b>
     *
     * @param messages 消息组
     * @param player   接受消息的玩家
     */
    void sendChatMessageGroup(ArrayList<String> messages, Player player);
}
