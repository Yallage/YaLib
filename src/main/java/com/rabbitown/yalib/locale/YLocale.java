package com.rabbitown.yalib.locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class YLocale {
    /**
     * 获取hashmap的消息文字
     * Get HashMap Message
     *
     * @param configPath 消息配置文件路径
     * @param deep       是否深度搜索Keys
     * @return 返回消息Map
     * @author Hanbings
     */
    public HashMap<String, String> getMessageOfHashMap(String configPath, boolean deep) {
        FileConfiguration data = YamlConfiguration.loadConfiguration(new File(configPath));
        HashMap<String, String> map = new HashMap<>();
        String[] strings = data.getKeys(deep).toArray(new String[0]);
        for (String string : strings) {
            map.put(string, ChatColor.translateAlternateColorCodes('&', (String) Objects.requireNonNull(data.getString(string))));
        }
        return map;
    }

    /**
     * 获取玩家语言
     * Get player language
     *
     * @param player 离线玩家对象
     * @author Hanbings
     */
    public String getPlayerLanguage(OfflinePlayer player) {
        Plugin plugin = com.rabbitown.yalib.YaLib.getPlugin(com.rabbitown.yalib.YaLib.class);
        if (Objects.requireNonNull(plugin.getConfig().getString("storage")).equals("yaml")) {
            FileConfiguration data = YamlConfiguration.loadConfiguration(new File("plugins/YaLib/data/languageData.yml"));
            if (data.getString(Objects.requireNonNull(player.getName())) == null) {
                return plugin.getConfig().getString("language");
            } else {
                return data.getString(Objects.requireNonNull(player.getName()));
            }
        }
        return plugin.getConfig().getString("language");
    }

    /**
     * 设置玩家语言
     * Set player language
     *
     * @param player 离线玩家对象
     * @author Hanbings
     */
    public void setPlayerLanguage(OfflinePlayer player, String language) {
        FileConfiguration data = YamlConfiguration.loadConfiguration(new File("plugins/YaLib/data/languageData.yml"));
        data.set(Objects.requireNonNull(player.getName()) + ".language", language);
        data.set(Objects.requireNonNull(player.getName()) + ".uuid", player.getUniqueId().toString());
    }

    /**
     * 获取指定信息
     * Get Message
     *
     * @param configPath 语言配置文件路径
     * @param keys       配置键
     * @author Hanbings
     */
    public String getMessage(String configPath, String keys) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(configPath));
        return ChatColor.translateAlternateColorCodes('&', (String) Objects.requireNonNull(config.get(keys)));
    }

    /**
     * 发送指定信息
     * Send Message
     *
     * @param player     玩家对象
     * @param configPath 语言配置文件路径
     * @param keys       配置键
     * @author Hanbings
     */
    public void sendMessage(Player player, String configPath, String keys) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(configPath));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) Objects.requireNonNull(config.get(keys))));
    }
}
