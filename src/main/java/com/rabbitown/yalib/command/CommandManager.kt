package com.rabbitown.yalib.command

import org.bukkit.command.PluginCommand
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Yoooooory
 */
class CommandManager {

    val commands = mutableMapOf<PluginCommand, List<CommandHandler>>()

    fun register(plugin: JavaPlugin, vararg handlers: CommandHandler): List<CommandHandler> =
        register(plugin, handlers.toList())

    fun register(plugin: JavaPlugin, handlers: List<CommandHandler>): List<CommandHandler> {
        val success = mutableListOf<CommandHandler>()
        for (handler in handlers) {
            CommandFactory.getCommands(handler, plugin).forEach {
                if (commands.containsKey(it)) {
                    // Add the handler to a command if it is exist.
                    commands.replace(it, commands[it]!! + handlers)
                } else {
                    it.setExecutor { s, c, l, a -> CommandProcessor(it).onCommand(s, c, l, a, commands[it]) }
                    if (!CommandFactory.registerCommand(it, plugin)) return@forEach
                    commands[it] = handlers.toList()
                }
                success += handler
            }
        }
        return success
    }

}