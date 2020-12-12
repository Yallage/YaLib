package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.*
import java.lang.reflect.Method

/**
 * Represents a command handler.
 *
 * @author Yoooooory
 */
open class CommandHandler(val method: Method) {

    val access = method.getDeclaredAnnotation(Access::class.java)
    val path = method.getDeclaredAnnotation(Path::class.java)
    val priority = method.getDeclaredAnnotation(Priority::class.java)

    class ActionHandler(method: Method) : CommandHandler(method) {
        val action = method.getDeclaredAnnotation(Action::class.java)
    }

    class DependentHandler(val id: String, method: Method) : CommandHandler(method)

}