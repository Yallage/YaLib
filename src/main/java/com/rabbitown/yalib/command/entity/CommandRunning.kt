package com.rabbitown.yalib.command.entity

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.util.regex.Pattern

/**
 * @author Yoooooory
 */
data class CommandRunning(
    val sender: CommandSender, val command: Command, val alias: String, val args: Array<out String>
) {

    private val argRegex = Regex("\\{(\\w+)(: ?(.+))?}")

    val argMap = mutableMapOf<String, Any>()

    fun getArgument(key: String, type: Class<*>): Any? {
        val value = when (key) {
            in argMap -> argMap[key]
            "running" -> this
            "sender" -> sender
            "command" -> command
            "label", "alias" -> alias
            "args" -> args
            else -> return null
        }
        return if (type.isInstance(value)) type.cast(value) else {
            val str = value.toString()
            when (type.name) {
                "java.lang.String" -> str
                "int" -> str.toInt()
                "org.bukkit.entity.Player" -> Bukkit.getPlayer(str)
                "org.bukkit.OfflinePlayer" -> Bukkit.getOfflinePlayer(str)
                "char" -> UnsupportedOperationException("Why not String?")
                else -> error("Argument type mismatch.")
            }
        }
    }

    internal fun putArguments(path: String, args: String) {
        val params = mutableListOf<String>()
        val m = Pattern.compile(escape(path).replace(argRegex) {
            with(it.groups) {
                params += this[1]!!.value
                "(${this[3]?.value ?: ".+"})"
            }
        }).matcher(args)
        if (m.find()) for (i in 1..m.groupCount()) argMap[params[i - 1]] = m.group(i)
    }

    private fun escape(string: String) = string.replace("\\", "\\\\").replace("(", "\\(")

    /** Auto generated. */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommandRunning

        if (sender != other.sender) return false
        if (command != other.command) return false
        if (alias != other.alias) return false
        if (!args.contentEquals(other.args)) return false
        if (argRegex != other.argRegex) return false
        if (argMap != other.argMap) return false

        return true
    }

    /** Auto generated. */
    override fun hashCode(): Int {
        var result = sender.hashCode()
        result = 31 * result + command.hashCode()
        result = 31 * result + alias.hashCode()
        result = 31 * result + args.contentHashCode()
        result = 31 * result + argRegex.hashCode()
        result = 31 * result + argMap.hashCode()
        return result
    }

}