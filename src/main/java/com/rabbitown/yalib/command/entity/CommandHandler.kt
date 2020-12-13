package com.rabbitown.yalib.command.entity

import com.rabbitown.yalib.command.annotation.*
import java.lang.reflect.Method
import java.util.Comparator

/**
 * Represents a command handler.
 *
 * @author Yoooooory
 */
abstract class CommandHandler(val handler: Method) {

    val access = handler.getDeclaredAnnotation(Access::class.java)
    val path = handler.getDeclaredAnnotation(Path::class.java)
    val priority = handler.getDeclaredAnnotation(Priority::class.java)

    fun getPriority() = Priority.get(handler)

    companion object {
        fun sortByPriority() = Comparator.comparingInt(CommandHandler::getPriority)
    }

}

class ActionHandler(
    method: Method, completers: List<Method>? = emptyList(),
    senderDeniedHandlers: List<Method>? = emptyList(),
    permissionDeniedHandlers: List<Method>? = emptyList()
) : CommandHandler(method) {

    val action = method.getDeclaredAnnotation(Action::class.java)

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