package com.rabbitown.yalib

import com.rabbitown.yalib.command.CommandManager
import com.rabbitown.yalib.common.command.CommandGUI
import com.rabbitown.yalib.common.command.CommandMain
import com.rabbitown.yalib.gui.GUIListener
import org.bukkit.plugin.java.JavaPlugin
import com.rabbitown.yalib.nms.NMSBase
import com.rabbitown.yalib.locale.I18NPlugin
import com.rabbitown.yalib.locale.Locale
import com.rabbitown.yalib.util.FileUtil
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.defaults.BukkitCommand
import java.lang.Exception

/**
 * The main class.
 *
 * @author Hanbings, Yoooooory
 */
class YaLib : JavaPlugin(), I18NPlugin {

    init {
        instance = this
    }

    override val locale = Locale(this, "zh_CN")

    override fun onLoad() {
        YaLibCentral.registerPlugin(this)
    }

    override fun onEnable() {
        CommandManager.register(CommandMain())
        saveDefaultConfig()
        FileUtil.saveResource(this, "data/languageData.yml")
        if (!loadNMS()) {
            logger.severe("The version of your server is not supported.")
            pluginLoader.disablePlugin(this)
            return
        }
        GUIListener(this);
        CommandGUI(this)
        logger.info(ChatColor.BLUE.toString() + "[YaLib] YaLib依赖已加载 感谢使用YaLib")
    }

    override fun onDisable() {
        logger.info(ChatColor.BLUE.toString() + "[YaLib] YaLib依赖已卸载")
    }

    private fun loadNMS(): Boolean {
        try {
            NMS = Class.forName("com.rabbitown.yalib.nms." + server.javaClass.getPackage().name.substring(23))
                .newInstance() as NMSBase
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    companion object {

        @JvmStatic
        lateinit var NMS: NMSBase
            private set

        @JvmStatic
        lateinit var instance: YaLib
            private set

    }

}