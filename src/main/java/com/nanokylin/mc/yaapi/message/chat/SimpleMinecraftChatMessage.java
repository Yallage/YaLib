package com.nanokylin.mc.yaapi.message.chat;

import com.nanokylin.mc.yaapi.YaAPI;
import com.nanokylin.mc.yaapi.message.IMinecraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SimpleMinecraftChatMessage implements IMinecraftChatMessage {
    private long time = 100L;
    private long delay = 0L;

    /**
     * 仅仅是提供一个空的构造方法
     */
    public SimpleMinecraftChatMessage() {
    }

    /**
     * 提供一个设置每一次发送ChatMessage的间隔时间设定 以及延迟时间
     *
     * @param time ChatMessageArray中每一条消息发送的时间间隔 单位 tick 默认为100tick 5s
     * @param delay 延迟执行的时间 单位 tick 默认为0tick
     */
    public SimpleMinecraftChatMessage(long time, long delay) {
        this.time = time;
        this.delay = delay;
    }

    @Override
    public void sendChatMessage(String message, Player player) {
        player.sendMessage(message);
    }

    @Override
    public void sendChatMessageGroup(ArrayList<String> messages, Player player) {
        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                player.sendMessage(messages.get(count));
                count = count + 1;
                if (count >= messages.size()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(YaAPI.getInstance(), delay, time);
    }
}
