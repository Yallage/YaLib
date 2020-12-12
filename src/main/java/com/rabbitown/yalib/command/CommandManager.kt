package com.rabbitown.yalib.command

import org.bukkit.command.PluginCommand

/**
 * YaLib central command manager.
 *
 * @author Yoooooory
 */
object CommandManager {

    private val handlerMap = mutableMapOf<PluginCommand, List<CommandRemote>>()

    fun register(vararg remotes: CommandRemote): List<CommandRemote> = register(remotes.toList())

    fun register(remotes: List<CommandRemote>): List<CommandRemote> {
        val success = mutableListOf<CommandRemote>()
        for (handler in remotes) {
            val command = handler.command
            if (handlerMap.containsKey(command)) {
                // Add the handler to a command if it is exist.
                handlerMap[command] = handlerMap[command]!! + handler
            } else {
                command.setExecutor(CommandProcessor(command))
                try {
                    CommandBuilder(command).register()
                } catch (e: Exception) {
                    continue
                }
                handlerMap[command] = listOf(handler)
            }
            success += handler
        }
        return success
    }

}