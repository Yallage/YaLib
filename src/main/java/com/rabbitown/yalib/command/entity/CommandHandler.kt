package com.rabbitown.yalib.command.entity

import com.rabbitown.yalib.command.CommandRemote
import com.rabbitown.yalib.command.CommandResult
import com.rabbitown.yalib.command.Limitable
import com.rabbitown.yalib.command.annotation.*
import org.bukkit.command.CommandSender
import java.lang.reflect.Method
import java.util.Comparator

/**
 * Represents a command handler.
 *
 * @author Yoooooory
 */
abstract class CommandHandler(val handler: Method) : Limitable {

    override val access = Access.get(handler)
    override val path = Path.get(handler)
    override val priority = Priority.get(handler)

    protected fun getInvokeResult(remote: CommandRemote, running: CommandRunning): Any? =
        handler.invoke(remote, *handler.parameters.map { running.getArgument(it.name) }.toTypedArray())

    companion object {
        fun sortByPriority(): Comparator<CommandHandler> = Comparator.comparingInt(CommandHandler::priority)
    }

}

class ActionHandler(
    method: Method, completers: List<Method>? = emptyList(),
    senderDeniedHandlers: List<Method>? = emptyList(),
    permissionDeniedHandlers: List<Method>? = emptyList()
) : CommandHandler(method) {

    val id = method.name
    val action = Action.get(method)

    val completers = (completers ?: emptyList())
        .sortedWith(Comparator.comparingInt(Priority.Companion::get)).map {
            CompleterHandler(it.getDeclaredAnnotation(Completer::class.java).id, it)
        }
    val senderDeniedHandlers = (senderDeniedHandlers ?: emptyList())
        .sortedWith(Comparator.comparingInt(Priority.Companion::get)).map {
            AccessHandler(it.getDeclaredAnnotation(SenderDeniedHandler::class.java).id, it)
        }
    val permissionDeniedHandlers = (permissionDeniedHandlers ?: emptyList())
        .sortedWith(Comparator.comparingInt(Priority.Companion::get)).map {
            AccessHandler(it.getDeclaredAnnotation(PermissionDeniedHandler::class.java).id, it)
        }

    fun invoke(remote: CommandRemote, running: CommandRunning) {
        val result = getInvokeResult(remote, running)
        if (result != null) running.sender.sendMessage(result.toString())
    }

}

class AccessHandler(val id: String, method: Method) : CommandHandler(method) {

    fun invoke(remote: CommandRemote, running: CommandRunning) {
        running.pathArgMap["access"] = remote.access
        running.pathArgMap["sender"] = remote.access.sender
        running.pathArgMap["permission"] = remote.access.permission
        val result = getInvokeResult(remote, running)
        if (result != null) running.sender.sendMessage(result.toString())
    }

    companion object {
        fun getAccessibleOrNull(handlers: List<AccessHandler>, sender: CommandSender) = handlers.firstOrNull {
            CommandResult.getCommandResult(it, sender) == CommandResult.SUCCESS
        }
    }

}

class CompleterHandler(val id: String, method: Method) : CommandHandler(method) {

    private val argRegex = Regex("\\{(\\w+)(: ?(.+))?}")

    fun invoke(index: Int, path: String, remote: CommandRemote, running: CommandRunning): List<String> {
        val key = path.replace(argRegex) { it.groups[1]?.value ?: error("") }.split(" ")[index]
        running.pathArgMap["key"] = key
        val result = getInvokeResult(remote, running)
        if (result is Map<*, *>) {
            return when (val value = result[key]) {
                is Iterable<*> -> value.map { it.toString() }.filter { it.startsWith(running.args.last()) }
                is Array<*> -> value.map { it.toString() }.filter { it.startsWith(running.args.last()) }
                else -> listOf(value.toString())
            }
        } else if (result is Iterable<*>) return result.map { it.toString() }.toList()
        error("Completer only accepts iterable return value.")
    }

    companion object {
        fun getAccessibleOrNull(handlers: List<CompleterHandler>, sender: CommandSender) = handlers.firstOrNull {
            CommandResult.getCommandResult(it, sender) == CommandResult.SUCCESS
        }
    }

}