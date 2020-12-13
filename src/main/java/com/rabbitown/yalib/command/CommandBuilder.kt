package com.rabbitown.yalib.command

import com.rabbitown.yalib.YaLib
import org.bukkit.command.CommandExecutor
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.Plugin

/**
 * Represent a [PluginCommand] builder.
 *
 * @author Yoooooory
 */
class CommandBuilder(val command: PluginCommand) {

    constructor(name: String) : this(name, YaLib.instance)
    constructor(name: String, owner: Plugin) : this(Commands.newPluginCommand(name, owner))

    /* information filling functions */
    fun name(name: String) = this.apply { command.name = name }
    fun description(description: String) = this.apply { command.description = description }
    fun aliases(aliases: List<String>) = this.apply { command.aliases = aliases }
    fun aliases(vararg aliases: String) = aliases(aliases.toList())
    fun usage(usage: String) = this.apply { command.usage = usage; this }
    fun permission(permission: String) = this.apply { command.permission = permission }
    fun permissionMessage(message: String) = this.apply { command.permissionMessage = message }
    fun executor(executor: CommandExecutor) = this.apply { command.setExecutor(executor) }
    fun tab(completer: TabCompleter) = this.apply { command.tabCompleter = completer }

    fun register() = if (Commands.registerCommand(command)) command else null

}