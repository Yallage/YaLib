package com.rabbitown.yalib.command.annotation

import java.lang.NullPointerException
import java.lang.reflect.Method
import java.util.*

/**
 * Util class for command handlers,
 * which including [Action], [Completer], [SenderDeniedHandler], [PermissionDeniedHandler].
 *
 * @author Yoooooory
 */
class Handlers private constructor() {
    companion object {

        /** Used to mark the default handler. */
        const val DEFAULT = "f52de14f729f1567" // MD5 of "YaLib", probably won't repeat.

        val handlers = arrayOf(
            Action::class.java,
            Completer::class.java,
            SenderDeniedHandler::class.java,
            PermissionDeniedHandler::class.java
        )

        fun Completer.isDefault() = this.id == DEFAULT
        fun SenderDeniedHandler.isDefault() = this.id == DEFAULT
        fun PermissionDeniedHandler.isDefault() = this.id == DEFAULT

    }
}

/**
 * Define an action of a command.
 *
 * @param action The action of the command.
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Action(vararg val action: String) {
    companion object {
        fun get(method: Method) = method.getAnnotation(Action::class.java)!!.action
    }
}

/**
 * Define a completer of an action.
 *
 * @param id The action id (method name), also support [Handlers.DEFAULT].
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Completer(val id: String) {
    companion object {
        fun get(method: Method) = method.getAnnotation(Completer::class.java)!!
    }
}

/**
 * Define a handler to deal with sender denying.
 *
 * @param id The action id (method name), also support [Handlers.DEFAULT].
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class SenderDeniedHandler(val id: String) {
    companion object {
        fun get(method: Method) = method.getAnnotation(SenderDeniedHandler::class.java)!!
    }
}

/**
 * Define a handler to deal with permission denying.
 *
 * @param id The action id (method name), also support [Handlers.DEFAULT].
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class PermissionDeniedHandler(val id: String) {
    companion object {
        fun get(method: Method) = method.getAnnotation(PermissionDeniedHandler::class.java)!!
    }
}