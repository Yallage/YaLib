package com.rabbitown.yalib.module.command

import org.bukkit.command.PluginCommand

/**
 * The manager of command module.
 *
 * @author Milkory
 */
object CommandManager {

    private val handlerMap = mutableMapOf<PluginCommand, List<CommandRemote>>()

    fun register(vararg remotes: CommandRemote): List<CommandRemote> = register(remotes.toList())

    fun register(remotes: List<CommandRemote>): List<CommandRemote> {
        val success = mutableListOf<CommandRemote>()
        for (remote in remotes) {
            val command = remote.command
            if (command in handlerMap) {
                // Add the remote to a command if it is exist.
                val executor = command.executor
                if (executor is CommandProcessor)
                    try {
                        executor.addRemote(remote)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        continue
                    } else continue
                handlerMap[command] = handlerMap[command]!! + remote
            } else {
                command.setExecutor(CommandProcessor(remote))
                try {
                    CommandBuilder(command).register()
                } catch (e: Exception) {
                    continue
                }
                handlerMap[command] = listOf(remote)
            }
            success += remote
        }
        return success
    }

}