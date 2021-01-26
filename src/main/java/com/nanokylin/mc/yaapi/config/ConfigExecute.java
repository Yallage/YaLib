package com.nanokylin.mc.yaapi.config;

import com.nanokylin.mc.yaapi.event.listener.PlayerTeleportListener;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * 处理配置文件加载出来的功能
 */
public class ConfigExecute {
    public ConfigExecute() {
        // 提供一个空的构造方法
    }

    /**
     * 配置文件执行器
     *
     * @param plugin 插件实例
     */
    public ConfigExecute(JavaPlugin plugin) {
        // 传入神奇的插件方法
        File temp = new File("./plugins/YaAPI/config.yml");
        if (!temp.exists()) {
            plugin.saveResource("config.yml", true);
        }
        // 清除合成表
        if (plugin.getConfig().getBoolean("UsingClearRecipes")) {
            this.clearRecipes(plugin);
        }
        // 修复在传送的时候也能保持窗口的错误
        if (plugin.getConfig().getBoolean("ClosePlayerInventoryOnTeleport")) {
            registerPlayerTeleportListener(plugin);
        }
    }

    /**
     * 清除合成表
     *
     * @param plugin 插件实例
     */
    public void clearRecipes(JavaPlugin plugin) {
        plugin.getServer().clearRecipes();
        plugin.getServer().getLogger().info(Color.GREEN + "[YaAPI] Patch: Clear All Recipes");
    }

    /**
     * 修复在传送的时候也能保持窗口的错误
     *
     * @param plugin 插件实例
     */
    public void registerPlayerTeleportListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new PlayerTeleportListener(), plugin);
        plugin.getServer().getLogger().info(Color.GREEN + "[YaAPI] Patch: Close Player Inventory On Teleport Patched");
    }
}
