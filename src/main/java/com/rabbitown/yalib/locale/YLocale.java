package com.rabbitown.yalib.locale;

import com.rabbitown.yalib.YaLib;
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
    public static HashMap<String, HashMap<String, String>> getMessageOfHashMap(String configPath, boolean deep) {
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
    public static String[] getFileList(String path) {
        return new File(path).list();
    }

    /**
     * Verify that all language files in the plugin language directory exist
     * If not exist will create one.
     *
     * @param resource 在插件内的资源目录 如 "language/xx.yml"
     * @param config   语言文件目录 如 "plugins/xxx/language/xx.yml"
     * @param plugin   插件实例 用于保存文件
     * @author Hanbings
     */
    public static void verifyLanguageExist(String resource, String config, JavaPlugin plugin) {
        File temp = new File(config);
        if (!temp.exists()) {
            plugin.saveResource(resource, true);
        }
    }

    /**
     * Get a player's language setting.
     *
     * @param player An {@link OfflinePlayer} object.
     * @return The player's language setting.
     * @author Hanbings
     */
    public static String getPlayerLanguage(OfflinePlayer player) {
        YaLib plugin = YaLib.instance;

        if (Objects.requireNonNull(plugin.getConfig().getString("storage")).equals("yaml")) {
            FileConfiguration data = YamlConfiguration.loadConfiguration(new File("plugins/YaLib/data/languageData.yml"));
            if (data.get(Objects.requireNonNull(player.getName())) != null) {
                return data.getString(Objects.requireNonNull(player.getName()));
            }
        }
        return plugin.getConfig().getString("Language");
    }

    /**
     * Set a player's language.
     *
     * @param player An {@link OfflinePlayer} object.
     * @author Hanbings
     */
    public static void setPlayerLanguage(OfflinePlayer player, String language) {
        if (Objects.requireNonNull(YaLib.instance.getConfig().getString("storage")).equals("yaml")) {
            FileConfiguration data = YamlConfiguration.loadConfiguration(new File("plugins/YaLib/data/languageData.yml"));
            data.set(Objects.requireNonNull(player.getName()) + ".language", language);
            data.set(Objects.requireNonNull(player.getName()) + ".uuid", player.getUniqueId().toString());
        }
    }

    /**
     * Get player's default language.
     *
     * @return Player's default language.
     * @author Hanbings
     */
    public static String getDefaultLanguage() {
        return YaLib.instance.getConfig().getString("language");
    }

    /**
     * Get console's default language.
     *
     * @return Console's default language.
     * @author Hanbings
     */
    public static String getConsoleDefaultLanguage() {
        return YaLib.instance.getConfig().getString("console-language");
    }
}
