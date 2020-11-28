package com.rabbitown.yalib

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Yoooooory
 */
object YaLibCenter {

    val pluginMap = mutableMapOf<String, JavaPlugin>("com.rabbitown.yalib" to YaLib.instance)

    fun registerPlugin(prefix: String, plugin: JavaPlugin) {
        pluginMap[prefix] = plugin
    }

    fun getPlugin(name: String): JavaPlugin? {
        pluginMap.forEach { if (name.startsWith(it.key)) return it.value }
        return null
    }

}