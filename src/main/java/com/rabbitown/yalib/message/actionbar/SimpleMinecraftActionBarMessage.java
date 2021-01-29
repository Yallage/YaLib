package com.rabbitown.yalib.message.actionbar;

import com.rabbitown.yalib.message.IMinecraftActionBarMessage;

import java.util.ArrayList;

import com.rabbitown.yalib.YaLib;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SimpleMinecraftActionBarMessage implements IMinecraftActionBarMessage {
    private long time = 100L;
    private long delay = 0L;

    /**
     * 仅仅是提供一个空的构造方法
     */
    public SimpleMinecraftActionBarMessage() {
    }

    /**
     * 提供一个设置每一次发送ActionBar的间隔时间设定
     *
     * @param time  ActionBarArray中每一条消息发送的时间间隔 单位 tick 默认为100tick 5s
     * @param delay 延迟执行的时间 单位 tick 默认为0tick
     */
    public SimpleMinecraftActionBarMessage(long time, long delay) {
        this.time = time;
        this.delay = delay;
    }

    @Override
    public void sendActionBar(String message, Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    @Override
    public void sendActionBarGroup(ArrayList<String> messages, Player player) {
        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messages.get(count)));
                count = count + 1;
                if (count >= messages.size()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(YaLib.getInstance(), delay, time);
    }
}
