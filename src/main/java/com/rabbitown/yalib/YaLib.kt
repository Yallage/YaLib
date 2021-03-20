package com.rabbitown.yalib

import com.rabbitown.yalib.common.Logger
import com.rabbitown.yalib.module.locale.I18NPlugin
import com.rabbitown.yalib.module.locale.LocaleManager
import com.rabbitown.yalib.module.locale.impl.PrefixedLocale
import org.bukkit.plugin.java.JavaPlugin

/**
 * The main class of YaLib.
 *
 * @author Hanbings, Milkory
 */
class YaLib : JavaPlugin(), I18NPlugin {

    init {
        instance = this
        YaLibCentral.registerPlugin(this)
    }

    override fun onLoad() {
        LocaleManager.load()
    }

    override fun onEnable() {
        saveDefaultConfig()
        Logger.info("I'm in!")
    }

    override fun onDisable() {
        Logger.info("I'm out!")
    }

    override fun getNewLocale() = PrefixedLocale.newDefault(this)

    companion object {
        @JvmStatic lateinit var instance: YaLib private set
    }

}