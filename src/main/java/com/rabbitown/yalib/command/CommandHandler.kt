package com.rabbitown.yalib.command

import com.rabbitown.yalib.YaLib
import org.bukkit.command.PluginCommand
import org.bukkit.plugin.java.JavaPlugin
import javax.annotation.RegEx

/**
 * Represent a command handler.
 *
 * @author Yoooooory
 */
@Suppress("unused")
open class CommandHandler(val command: PluginCommand, @RegEx val path: List<String>) {

    val sender: CommandSenderType = CommandSenderType.ALL
    val senderMessage: String = "§cOnly {sender} can use the command."

    constructor(
        name: String, plugin: JavaPlugin = YaLib.instance,
        aliases: List<String> = listOf(),
        description: String = "No description provided.", permission: String = "",
        permissionMessage: String = "§cYou don't have permission to use this command.",
        usage: String = "", path: List<String> = listOf()
    ) : this(CommandFactory.newPluginCommand(name, plugin).apply {
        this.aliases = aliases
        this.description = description
        this.permission = permission
        this.permissionMessage = permissionMessage
        this.usage = usage
    }, path)

}