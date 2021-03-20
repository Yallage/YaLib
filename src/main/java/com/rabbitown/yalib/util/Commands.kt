package com.rabbitown.yalib.util

import com.rabbitown.yalib.util.ReflectUtil.Companion.access
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.PluginCommand
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.SimplePluginManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.UnsupportedOperationException

/**
 * A util class to operator [PluginCommand].
 *
 * @author Milkory
 */
class Commands {
    companion object {

        /** Create a new instance of [PluginCommand]. */
        @JvmStatic
        fun newPluginCommand(name: String, owner: Plugin) =
            with(PluginCommand::class.java.getDeclaredConstructor(String::class.java, Plugin::class.java)) {
                isAccessible = true
                newInstance(name, owner) as PluginCommand
            }

        /** Get the corresponding registered command instance, or a null value will be returned back.
         *  @see getCommandOrThis */
        @JvmStatic
        fun getCommand(command: PluginCommand) = checkPlugin(command.plugin).getCommand(command.name)

        /** Get the corresponding registered command instance, or the parameter will be returned back. */
        @JvmStatic
        fun getCommandOrThis(command: PluginCommand) = getCommand(command) ?: command

        /** Check if the plugin is a [JavaPlugin]. */
        @JvmStatic
        private fun checkPlugin(plugin: Plugin): JavaPlugin = if (plugin is JavaPlugin) plugin
        else throw UnsupportedOperationException("Unknown plugin type.")

        /** Register a [PluginCommand] to [PluginManager]. */
        @JvmStatic
        fun registerCommand(command: PluginCommand): Boolean {
            return with(SimplePluginManager::class.java.getDeclaredField("commandMap").access()) {
                (get(Bukkit.getPluginManager()) as CommandMap).register(getPluginName(command), command)
            }
        }

        /** Get the name of command's plugin owner. */
        @JvmStatic
        fun getPluginName(command: PluginCommand) = command.plugin.name.toLowerCase(Locale.ENGLISH)

    }
}