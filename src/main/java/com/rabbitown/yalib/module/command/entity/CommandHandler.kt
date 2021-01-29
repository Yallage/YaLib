package com.rabbitown.yalib.module.command.entity

import com.rabbitown.yalib.module.command.CommandRemote
import com.rabbitown.yalib.module.command.CommandResult
import com.rabbitown.yalib.module.command.HandlerEntity
import com.rabbitown.yalib.module.command.MainHandler
import com.rabbitown.yalib.module.command.annotation.*
import org.bukkit.command.CommandSender
import java.lang.reflect.Method
import java.util.Comparator

/**
 * Represents a command handler.
 *
 * @author Yoooooory
 */
abstract class CommandHandler(val handler: Method) : HandlerEntity {

    override val access = Access.get(handler)
    override val priority = Priority.get(handler)

    protected fun getInvokeResult(remote: CommandRemote, running: CommandRunning): Any? =
        handler.invoke(remote, *handler.parameters.map { running.getArgument(it.name, it.type) }.toTypedArray())

    companion object {
        fun sortByPriority(): Comparator<CommandHandler> = Comparator.comparingInt(CommandHandler::priority).reversed()
    }

}

class ActionHandler(
    method: Method, completers: List<Method>? = emptyList(),
    senderDeniedHandlers: List<Method>? = emptyList(),
    permissionDeniedHandlers: List<Method>? = emptyList()
) : CommandHandler(method), MainHandler {

    val id = method.name
    val action: Array<out String>
    override val ignoreCase: Boolean
    override val path: String = Path.get(method).path

    init {
        val annotation = Action.get(method)
        action = annotation.action
        ignoreCase = annotation.ignoreCase
    }

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

    fun invoke(remote: CommandRemote, handler: HandlerEntity, running: CommandRunning) {
        running.argMap["access"] = handler.access
        running.argMap["senderType"] = handler.access.sender
        running.argMap["perm"] = handler.access.permission
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

    fun invoke(index: Int, path: String, remote: CommandRemote, running: CommandRunning): List<String> =
        invoke(path.replace(argRegex) { it.groups[1]?.value ?: error("") }.split(" ")[index], remote, running)

    fun invoke(key: String, remote: CommandRemote, running: CommandRunning): List<String> {
        running.argMap["key"] = key
        return when (val result = getInvokeResult(remote, running)) {
            is String -> listOf(result)
            is Map<*, *> -> when (val value = result[key]) {
                is Array<*> -> value.map { it.toString() }.filter { it.startsWith(running.args.last(), true) }
                is Iterable<*> -> value.map { it.toString() }.filter { it.startsWith(running.args.last(), true) }
                else -> listOf(value.toString())
            }
            is Array<*> -> result.map { it.toString() }.filter { it.startsWith(running.args.last(), true) }
            is Iterable<*> -> result.map { it.toString() }.filter { it.startsWith(running.args.last(), true) }
            else -> error("Completer only accepts iterable return value, but got ${if (result == null) "null" else result::class.java.typeName}.")
        }
    }

    companion object {
        fun getAccessibleOrNull(handlers: List<CompleterHandler>, sender: CommandSender) = handlers.firstOrNull {
            CommandResult.getCommandResult(it, sender) == CommandResult.SUCCESS
        }
    }

}