package com.rabbitown.yalib

import com.rabbitown.yalib.module.locale.I18NPlugin
import com.rabbitown.yalib.module.locale.LocaleManager
import org.bukkit.plugin.Plugin

/**
 * @author Yoooooory
 */
object YaLibCentral {

    private val pluginMap = mutableMapOf<Plugin, Array<String>>()

    fun registerPlugin(plugin: Plugin) = registerPlugin(plugin, arrayOf(plugin.javaClass.`package`.name))

    fun registerPlugin(plugin: Plugin, prefixes: Array<String>) {
        if (plugin is I18NPlugin) LocaleManager.register(plugin)
        pluginMap[plugin] = prefixes
    }

    fun getPlugin(pack: String): Plugin? {
        pluginMap.forEach { entry ->
            if (entry.value.any { pack.startsWith(it) }) return entry.key
        }
        return null
    }

    fun getPlugin(clazz: Class<*>) = getPlugin(clazz.`package`.name)

}