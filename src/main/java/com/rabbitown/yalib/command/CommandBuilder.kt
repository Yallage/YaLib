package com.rabbitown.yalib.command

import com.rabbitown.yalib.YaLib
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandMap
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.SimplePluginManager

/**
 * Gives a easier way to build a command.
 *
 * @author Yoooooory
 */
class CommandBuilder(val command: PluginCommand) {

    constructor(name: String) : this(name, YaLib.instance)
    constructor(name: String, owner: Plugin)
            : this(with(PluginCommand::class.java.getDeclaredConstructor(String::class.java, Plugin::class.java)) {
        isAccessible = true
        newInstance(name, owner)
    })

    /* information filling functions */
    fun name(name: String) = run { command.name = name; this }
    fun description(description: String) = run { command.description = description; this }
    fun aliases(aliases: List<String>) = run { command.aliases = aliases; this }
    fun aliases(vararg aliases: String) = aliases(aliases.toList())
    fun usage(usage: String) = run { command.usage = usage; this }
    fun permission(permission: String) = run { command.permission = permission; this }
    fun permissionMessage(message: String) = run { command.permissionMessage = message; this }
    fun executor(executor: CommandExecutor) = run { command.setExecutor(executor); this }
    fun tab(completer: TabCompleter) = run { command.tabCompleter = completer; this }

    fun register() = try {
        with(SimplePluginManager::class.java.getDeclaredField("commandMap")) {
            isAccessible = true
            (get(Bukkit.getPluginManager()) as CommandMap).register(command.name, command)
        }
    } catch (e: Exception) {
        false
    }

}