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

    constructor(
        name: String, plugin: JavaPlugin = YaLib.instance,
        aliases: List<String> = listOf(),
        description: String = "No description provided.", permission: String = "",
        permissionMessage: String = "§cYou don't have permission to use this command.",
        usage: String = "", path: List<String> = listOf()
    ) : this(
        CommandBuilder(name, plugin).aliases(aliases).description(description).permission(permission)
            .permissionMessage(permissionMessage).usage(usage).command, path
    )

    fun getUsage() = command.usage
    fun getDescription() = command.description
    fun getSenderMessage() = "§cOnly {sender} can use the command."
    fun getPermissionMessage() = command.permissionMessage ?: ""

}