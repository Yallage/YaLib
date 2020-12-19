package com.rabbitown.yalib.command.entity

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.util.regex.Pattern

/**
 * @author Yoooooory
 */
data class CommandRunning(
    val sender: CommandSender, val command: Command, val label: String, val args: Array<out String>
) {

    private val argRegex = Regex("\\{(\\w+)(: ?(.+))?}")

    val pathArgMap = mutableMapOf<String, Any>()

    fun getArgument(key: String) = when (key) {
        in pathArgMap -> pathArgMap[key]
        "running" -> this
        "sender" -> sender
        "command" -> command
        "label" -> label
        "args" -> args
        else -> null
    }

    internal fun putArguments(path: String, args: String) {
        val params = mutableListOf<String>()
        val m = Pattern.compile(escape(path).replace(argRegex) {
            with(it.groups) {
                params += this[1]!!.value
                "(${this[3]?.value ?: ".+"})"
            }
        }).matcher(args)
        if (m.find()) for (i in 1..m.groupCount()) pathArgMap[params[i - 1]] = m.group(i)
    }

    private fun escape(string: String) = string.replace("\\", "\\\\").replace("(", "\\(")

}