package com.rabbitown.yalib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Main Class
 *
 * @author Hanbings
 */
public final class YaLib extends JavaPlugin {
    /**
     * 启动插件的时候执行这个方法
     *
     * @author Hanbings
     */
    @Override
    public void onEnable() {
        File config = new File("plugins/YaWhitelist/config.yml");
        if (config.exists()) {
            if (this.getConfig().getBoolean("YaLibLogo")) {
                Bukkit.getServer().getLogger().info(ChatColor.BLUE + "_____.___.      .____    ._____.    ");
                Bukkit.getServer().getLogger().info(ChatColor.BLUE + "\\__  |   |____  |    |   |__\\_ |__  ");
                Bukkit.getServer().getLogger().info(ChatColor.BLUE + " /   |   \\__  \\ |    |   |  || __ \\ ");
                Bukkit.getServer().getLogger().info(ChatColor.BLUE + " \\____   |/ __ \\|    |___|  || \\_\\ \\");
                Bukkit.getServer().getLogger().info(ChatColor.BLUE + " / ______(____  /_______ \\__||___  /");
                Bukkit.getServer().getLogger().info(ChatColor.BLUE + " \\/           \\/        \\/       \\/  ");
            }
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + "[YaLib] YaLib依赖已加载 感谢使用YaLib");
        } else {
            Bukkit.getServer().getLogger().info(ChatColor.GREEN + "[YaLib] 未找到配置文件 正在创建配置文件");
            saveResource("config.yml", false);
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + "_____.___.      .____    ._____.    ");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + "\\__  |   |____  |    |   |__\\_ |__  ");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + " /   |   \\__  \\ |    |   |  || __ \\ ");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + " \\____   |/ __ \\|    |___|  || \\_\\ \\");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + " / ______(____  /_______ \\__||___  /");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + " \\/           \\/        \\/       \\/  ");
            Bukkit.getServer().getLogger().info(ChatColor.BLUE + "[YaLib] YaLib依赖已加载 感谢使用YaLib");
        }
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
}
