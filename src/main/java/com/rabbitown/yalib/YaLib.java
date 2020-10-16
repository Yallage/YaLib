package com.rabbitown.yalib;

import com.rabbitown.yalib.command.CommandManager;
import com.rabbitown.yalib.locale.YLocale;
import com.rabbitown.yalib.nms.NMSBase;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Main Class
 *
 * @author Hanbings
 */
public final class YaLib extends JavaPlugin {

    public static YaLib instance;

    public static CommandManager CommandManager = new CommandManager();

    public static NMSBase NMS;

    public YaLib() {
        instance = this;
    }

    /**
     * 启动插件的时候执行这个方法
     *
     * @author Hanbings
     */
    @Override
    public void onEnable() {
        // 生成默认配置文件 第二个参数表示是否替换
        YLocale.verifyLanguageExist("config.yml", "plugins/YaWhitelist/config.yml", this);
        YLocale.verifyLanguageExist("data/languageData.yml", "plugins/YaWhitelist/message.yml", this);
        // 显示Logo
        if (this.getConfig().getBoolean("YaLibLogo")) {
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + "_____.___.      .____    ._____.    ");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + "\\__  |   |____  |    |   |__\\_ |__  ");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + " /   |   \\__  \\ |    |   |  || __ \\ ");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + " \\____   |/ __ \\|    |___|  || \\_\\ \\");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + " / ______(____  /_______ \\__||___  /");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + " \\/           \\/        \\/       \\/  ");
        }
        Bukkit.getServer().getLogger().info(ChatColor.BLUE + "[YaLib] YaLib依赖已加载 感谢使用YaLib");
    }

    /**
     * 关闭插件的时候执行这个方法
     *
     * @author Hanbings
     */
    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info(ChatColor.BLUE + "[YaLib] YaLib依赖已卸载");
    }

    private boolean loadNMS() {
        try {
            NMS = (NMSBase) Class.forName("com.rabbitown.yalib.nms." + getServer().getClass().getPackage().getName().substring(23)).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}