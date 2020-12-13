package com.rabbitown.yalib.command.entity

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

/**
 * @author Yoooooory
 */
data class CommandRunning(val sender: CommandSender, val command: Command, val label: String, val args: Array<out String>) {

    val pathArgMap = mutableMapOf<String, Any>()

}