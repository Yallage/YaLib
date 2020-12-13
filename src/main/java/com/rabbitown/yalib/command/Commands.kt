package com.rabbitown.yalib.command

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

        fun newPluginCommand(name: String, owner: Plugin) = with(PluginCommand::class.java.getDeclaredConstructor(String::class.java, Plugin::class.java)) {
            isAccessible = true
            newInstance(name, owner) as PluginCommand
        }

        fun getCommand(command: PluginCommand) = checkPlugin(command.plugin).getCommand(command.name)

        fun getCommandOrThis(command: PluginCommand) = getCommand(command) ?: command

        fun checkPlugin(plugin: Plugin): JavaPlugin = if (plugin is JavaPlugin) plugin
        else throw UnsupportedOperationException("Unknown plugin type.")

        fun registerCommand(command: PluginCommand) =
            with(SimplePluginManager::class.java.getDeclaredField("commandMap")) {
                isAccessible = true
                (get(Bukkit.getPluginManager()) as CommandMap).register(getPluginName(command), command)
            }

        fun getPluginName(command: PluginCommand) = command.plugin.name.toLowerCase(Locale.ENGLISH)

    }
}