package com.rabbitown.yalib.locale;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
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
    public HashMap<String, HashMap<String, String>> getMessageOfHashMap(String configPath, boolean deep) {
        HashMap<String, HashMap<String, String>> objectMap = new HashMap<>();
        HashMap<String, HashMap<String, String>> multiLanguage = new HashMap<>();
        String[] languageFileList = getFileList(configPath);
        for (String s : languageFileList) {
            objectMap.put(s, new HashMap<String, String>());
            FileConfiguration data = YamlConfiguration.loadConfiguration(new File(configPath + "/" + s));
            String[] strings = data.getKeys(deep).toArray(new String[0]);
            for (String string : strings) {
                objectMap.get(s).put(string, ChatColor.translateAlternateColorCodes('&', (String) Objects.requireNonNull(data.getString(string))));
            }
            multiLanguage.put(s.substring(0, s.length() - 4), objectMap.get(s));
        }
        return multiLanguage;
    }

    /**
     * 获取目录中文件列表
     *
     * @param path 被扫描的目录
     * @return 文件列表Array
     */
    public String[] getFileList(String path) {
        return new File(path).list();
    }

    /**
     * 快速验证配置文件是否存在
     * Verify that all language files in the plugin language directory exist
     * If not exist will create that
     *
     * @param resource   在插件内的资源目录 如 "language/xx.yml"
     * @param config     语言文件目录 如 "plugins/xxx/language/xx.yml"
     * @param javaPlugin 插件实例 用于保存文件
     * @author Hanbings
     */
    public void verifyLanguageExist(String resource, String config, JavaPlugin javaPlugin) {
        File temp = new File(config);
        if (!temp.exists()) {
            javaPlugin.saveResource(resource, true);
        }
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
        Plugin plugin = com.rabbitown.yalib.YaLib.getPlugin(com.rabbitown.yalib.YaLib.class);
        if (Objects.requireNonNull(plugin.getConfig().getString("storage")).equals("yaml")) {
            FileConfiguration data = YamlConfiguration.loadConfiguration(new File("plugins/YaLib/data/languageData.yml"));
            data.set(Objects.requireNonNull(player.getName()) + ".language", language);
            data.set(Objects.requireNonNull(player.getName()) + ".uuid", player.getUniqueId().toString());
        }
    }

    /**
     * 获取默认语言
     * Get default language
     *
     * @return 默认语言
     * @author Hanbings
     */
    public String getDefaultLanguage() {
        Plugin plugin = com.rabbitown.yalib.YaLib.getPlugin(com.rabbitown.yalib.YaLib.class);
        return plugin.getConfig().getString("language");
    }

    /**
     * 获取控制台默认语言
     * Get console default language
     *
     * @return 控制台默认语言
     * @author Hanbings
     */
    public String getConsoleDefaultLanguage() {
        Plugin plugin = com.rabbitown.yalib.YaLib.getPlugin(com.rabbitown.yalib.YaLib.class);
        return plugin.getConfig().getString("console-language");
    }
}
