package com.rabbitown.yalib

import org.bukkit.plugin.java.JavaPlugin
import com.rabbitown.yalib.locale.YLocale
import org.bukkit.Bukkit
import com.rabbitown.yalib.nms.NMSBase
import java.lang.InstantiationException
import java.lang.IllegalAccessException
import java.lang.ClassNotFoundException
import com.rabbitown.yalib.command.CommandManager
import lombok.Getter
import org.bukkit.ChatColor
import java.lang.Exception

/**
 * The main class.
 *
 * @author Hanbings, Yoooooory
 */
class YaLib : JavaPlugin() {

    override fun onEnable() {
        YLocale.verifyLanguageExist("config.yml", "plugins/YaWhitelist/config.yml", this)
        YLocale.verifyLanguageExist("data/languageData.yml", "plugins/YaWhitelist/message.yml", this)
        if (!loadNMS()) {
            logger.severe("The version of your server is not supported.")
            pluginLoader.disablePlugin(this)
            return
        }
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
        lateinit var instance: YaLib
            private set
        @JvmStatic
        lateinit var NMS: NMSBase
            private set
        @JvmStatic
        val CommandManager = CommandManager()
    }

    init {
        instance = this
    }

}