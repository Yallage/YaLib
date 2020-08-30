package com.rabbitown.yalib.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabExecutor
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
                    it.setExecutor(object : TabExecutor {
                        val processor = CommandProcessor(it)
                        override fun onTabComplete(
                            s: CommandSender, c: Command, l: String, a: Array<String>
                        ): MutableList<String> = processor.onTabComplete(s, c, l, a, commands[it])

                        override fun onCommand(s: CommandSender, c: Command, l: String, a: Array<String>): Boolean =
                            processor.onCommand(s, c, l, a, commands[it])
                    })
//                    it.setExecutor { s, c, l, a -> CommandProcessor(it).onCommand(s, c, l, a, commands[it]) }
                    if (!CommandFactory.registerCommand(it, plugin)) return@forEach
                    commands[it] = handlers.toList()
                }
                success += handler
            }
        }
        return success
    }

}