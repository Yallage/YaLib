package com.rabbitown.yalib.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabExecutor

/**
 * @author Yoooooory
 */
internal class CommandProcessor(val command: PluginCommand) : TabExecutor {

    val remotes = mutableListOf<CommandRemote>()

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): List<String> {
        TODO("Not yet implemented")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        TODO("Not yet implemented")
    }

}