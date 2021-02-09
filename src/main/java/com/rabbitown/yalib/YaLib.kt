package com.rabbitown.yalib

import com.rabbitown.yalib.module.locale.I18NPlugin
import com.rabbitown.yalib.module.locale.Locale
import com.rabbitown.yalib.util.FileUtil
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

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
        saveDefaultConfig()
        FileUtil.saveResource(this, "data/languageData.yml")
        logger.info(ChatColor.BLUE.toString() + "[YaLib] YaLib依赖已加载 感谢使用YaLib")
    }

    override fun onDisable() {
        logger.info(ChatColor.BLUE.toString() + "[YaLib] YaLib依赖已卸载")
    }

    companion object {

        @JvmStatic
        lateinit var instance: YaLib
            private set

    }

}