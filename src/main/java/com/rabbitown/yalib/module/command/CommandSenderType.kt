package com.rabbitown.yalib.module.command

import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

/**
 * Enum for the types of command senders.
 *
 * @author Yoooooory
 */
enum class CommandSenderType {

    PLAYER, CONSOLE, OTHER;

    companion object {

        @JvmStatic
        val ALL: Array<String> = emptyArray()

        fun checkSender(sender: CommandSender, required: CommandSenderType) = when(sender) {
            is Player -> required == PLAYER
            is ConsoleCommandSender -> required == CONSOLE
            else -> required == OTHER
        }

    }

}