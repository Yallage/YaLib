package com.rabbitown.yalib

import org.bukkit.plugin.java.JavaPlugin
import com.rabbitown.yalib.nms.NMSBase
import com.rabbitown.yalib.locale.I18NPlugin
import com.rabbitown.yalib.locale.Locale
import com.rabbitown.yalib.util.FileUtil
import org.bukkit.ChatColor
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
        YaLibCenter.registerPlugin("com.rabbitown.yalib", this)
    }

    override fun onEnable() {
        saveDefaultConfig()
        FileUtil.saveResource(this, "data/languageData.yml")
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
        lateinit var NMS: NMSBase
            private set

        @JvmStatic
        lateinit var instance: YaLib
            private set

    }

}