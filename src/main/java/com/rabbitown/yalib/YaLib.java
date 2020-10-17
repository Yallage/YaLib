package com.rabbitown.yalib;

import com.rabbitown.yalib.command.CommandManager;
import com.rabbitown.yalib.common.DependFile;
import com.rabbitown.yalib.common.Resources;
import com.rabbitown.yalib.database.DataBaseService;
import com.rabbitown.yalib.database.impl.SQLiteDataBaseImpl;
import com.rabbitown.yalib.locale.YLocale;
import com.rabbitown.yalib.nms.NMSBase;
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
        if (!loadNMS()) {
            getLogger().info("BAD!");
            getPluginLoader().disablePlugin(this);
            return;
        }

        /*/////////////////////////// TEST ////////////////////////////////
        // 下载依赖
        DependFile dependFile = new DependFile();
        dependFile.setList();
        dependFile.download();
        // 初始化SQLITE
        DataBaseService dataBaseService = new SQLiteDataBaseImpl();
        Resources.SQLLiteConnection = dataBaseService.loadDatabase("./libs/test.db");
        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";
        ////////////////////////////////////////////////////////////////*/

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