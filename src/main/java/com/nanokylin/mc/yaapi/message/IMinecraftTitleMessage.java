package com.nanokylin.mc.yaapi.message;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public interface IMinecraftTitleMessage {

    /**
     * 发送一条title消息
     *
     * @param message 消息
     * @param player  接受消息的玩家
     *
     */
    public void sendTitle(String message, Player player);

    /**
     * 发送一条title消息
     *
     * @param messages 消息组
     * @param player   接受消息的玩家
     */
    public void sendTitles(ArrayList<String> messages, Player player);

    /**
     * 发送一条subTitle消息
     *
     * @param message 消息
     * @param player  接受消息的玩家
     */
    public void sendSubTitle(String message, Player player);

    /**
     * 发送一组subTitle消息
     *
     * @param messages 消息组
     * @param player   接受消息的玩家
     */
    public void sendSubTitles(ArrayList<String> messages, Player player);
}
