package com.rabbitown.yalib.command

import org.bukkit.command.PluginCommand
import javax.annotation.RegEx

/**
 * Represent a command handler.
 *
 * @author Yoooooory
 */
open class CommandHandler(
    val command: PluginCommand,
    val sender: CommandSenderType = CommandSenderType.ALL,
    val senderMessage: String = "§cOnly {sender} can use the command.",
    @RegEx vararg val path: String
)