package com.nanokylin.mc.yaapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class YaAPI extends JavaPlugin {

    static YaAPI yaAPIInstance;

    public YaAPI() {
        yaAPIInstance = getInstance();
    }

    public static YaAPI getInstance() {
        return yaAPIInstance;
    }

    @Override
    public void onEnable() {
        Bukkit.getServer().getLogger().info("[YaAPI] Running");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("[YaAPI] Stopping");
    }
}
