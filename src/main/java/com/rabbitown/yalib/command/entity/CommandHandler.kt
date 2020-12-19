package com.rabbitown.yalib.command.entity

import com.rabbitown.yalib.command.Limitable
import com.rabbitown.yalib.command.annotation.*
import java.lang.reflect.Method
import java.util.Comparator

/**
 * Represents a command handler.
 *
 * @author Yoooooory
 */
abstract class CommandHandler(val handler: Method): Limitable {

    override val access = Access.get(handler)
    override val path = Path.get(handler)
    override val priority = Priority.get(handler)

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
            DependentHandler(it.getDeclaredAnnotation(Completer::class.java).id, it)
        }
    val senderDeniedHandlers = (senderDeniedHandlers ?: emptyList())
        .sortedWith(Comparator.comparingInt(Priority.Companion::get)).map {
            DependentHandler(it.getDeclaredAnnotation(SenderDeniedHandler::class.java).id, it)
        }
    val permissionDeniedHandlers = (permissionDeniedHandlers ?: emptyList())
        .sortedWith(Comparator.comparingInt(Priority.Companion::get)).map {
            DependentHandler(it.getDeclaredAnnotation(PermissionDeniedHandler::class.java).id, it)
        }

}

class DependentHandler(val id: String, method: Method) : CommandHandler(method)