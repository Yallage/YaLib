package com.rabbitown.yalib

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Yoooooory
 */
object YaLibCenter {

    private val pluginMap = mutableMapOf<String, JavaPlugin>()

    fun registerPlugin(plugin: JavaPlugin) = registerPlugin(plugin, plugin.javaClass.`package`.name)

    fun registerPlugin(plugin: JavaPlugin, prefix: String) {
        pluginMap[prefix] = plugin
    }

    fun getPlugin(name: String): JavaPlugin? {
        pluginMap.forEach { if (name.startsWith(it.key)) return it.value }
        return null
    }

}