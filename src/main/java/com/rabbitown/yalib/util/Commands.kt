package com.rabbitown.yalib.util

import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.PluginCommand
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.SimplePluginManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.UnsupportedOperationException

/**
 * Util class for [PluginCommand].
 *
 * @author Yoooooory
 */
class Commands {
    companion object {

        @JvmStatic
        fun newPluginCommand(name: String, owner: Plugin) =
            with(PluginCommand::class.java.getDeclaredConstructor(String::class.java, Plugin::class.java)) {
                isAccessible = true
                newInstance(name, owner) as PluginCommand
            }

        @JvmStatic
        fun getCommand(command: PluginCommand) = checkPlugin(command.plugin).getCommand(command.name)

        @JvmStatic
        fun getCommandOrThis(command: PluginCommand) = getCommand(command) ?: command

        @JvmStatic
        fun checkPlugin(plugin: Plugin): JavaPlugin = if (plugin is JavaPlugin) plugin
        else throw UnsupportedOperationException("Unknown plugin type.")

        @JvmStatic
        fun registerCommand(command: PluginCommand) =
            with(SimplePluginManager::class.java.getDeclaredField("commandMap")) {
                isAccessible = true
                (get(Bukkit.getPluginManager()) as CommandMap).register(getPluginName(command), command)
            }

        @JvmStatic
        fun getPluginName(command: PluginCommand) = command.plugin.name.toLowerCase(Locale.ENGLISH)

    }
}