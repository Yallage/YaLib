package com.nanokylin.mc.yaapi.message.title;

import com.nanokylin.mc.yaapi.message.IMinecraftTitleMessage;
import com.rabbitown.yalib.YaLib;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SimpleMinecraftTitleMessage implements IMinecraftTitleMessage {

    private String title = "";
    private String subTitle = "";
    private int fadeIn = 10;
    private int stay = 70;
    private int fadeOut = 20;
    private long time = 100L;
    private long delay = 0L;

    public SimpleMinecraftTitleMessage() {
    }

    /**
     * 构造
     *
     * @param fadeIn  渐入
     * @param stay    保持
     * @param fadeOut 渐出
     */
    public SimpleMinecraftTitleMessage(int fadeIn, int stay, int fadeOut) {
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    /**
     * 构造 <b>如果使用sendSubTitle则会忽略设置的subTitle</b>
     *
     * @param subTitle 小标题
     * @param fadeIn   渐入
     * @param stay     保持
     * @param fadeOut  渐出
     */
    public SimpleMinecraftTitleMessage(String subTitle, int fadeIn, int stay, int fadeOut) {
        this.subTitle = subTitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    /**
     * 构造 <b>如果使用sendSubTitle则会忽略设置的subTitle sendTitle则忽略title</b>
     *
     * @param title    大标题
     * @param subTitle 小标题
     * @param fadeIn   渐入
     * @param stay     保持
     * @param fadeOut  渐出
     */
    public SimpleMinecraftTitleMessage(String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        this.title = title;
        this.subTitle = subTitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    /**
     * 构造 <b>如果使用sendSubTitle则会忽略设置的subTitle sendTitle则忽略title</b>
     *
     * @param title    大标题
     * @param subTitle 小标题
     * @param fadeIn   渐入
     * @param stay     保持
     * @param fadeOut  渐出
     * @param time     ActionBarArray中每一条消息发送的时间间隔 单位 tick 默认为100tick 5s
     */
    public SimpleMinecraftTitleMessage(String title, String subTitle, int fadeIn, int stay, int fadeOut, long time) {
        this.title = title;
        this.subTitle = subTitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
        this.time = time;
    }

    /**
     * 构造 <b>如果使用sendSubTitle则会忽略设置的subTitle sendTitle则忽略title</b>
     *
     * @param title    大标题
     * @param subTitle 小标题
     * @param fadeIn   渐入
     * @param stay     保持
     * @param fadeOut  渐出
     * @param time     ActionBarArray中每一条消息发送的时间间隔 单位 tick 默认为100tick 5s
     * @param delay    延迟执行的时间 单位 tick 默认为0tick
     */
    public SimpleMinecraftTitleMessage(String title, String subTitle, int fadeIn, int stay, int fadeOut, long time, long delay) {
        this.title = title;
        this.subTitle = subTitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
        this.time = time;
        this.delay = delay;
    }

    @Override
    public void sendTitle(String message, Player player) {
        player.sendTitle(message, subTitle, fadeIn, stay, fadeOut);
    }

    @Override
    public void sendTitles(ArrayList<String> messages, Player player) {

        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                player.sendTitle(messages.get(count), subTitle, fadeIn, stay, fadeOut);
                count = count + 1;
                if (count >= messages.size()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(YaLib.getInstance(), delay, time);
    }

    @Override
    public void sendSubTitle(String message, Player player) {
        player.sendTitle(title, message, fadeIn, stay, fadeOut);
    }

    @Override
    public void sendSubTitles(ArrayList<String> messages, Player player) {
        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                player.sendTitle(title, messages.get(count), fadeIn, stay, fadeOut);
                count = count + 1;
                if (count >= messages.size()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(YaLib.getInstance(), delay, time);
    }
}
