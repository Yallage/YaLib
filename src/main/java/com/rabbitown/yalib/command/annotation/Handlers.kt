package com.rabbitown.yalib.command.annotation

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
    }
}

/**
 * Mark an action of a command.
 *
 * @param action The action of the command.
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Action(val action: String)

/**
 * Mark a completer of an action.
 *
 * @param id The action id (method name), also support [Handlers.DEFAULT].
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class Completer(val id: String)

/**
 * Mark a handler to deal with sender denying.
 *
 * @param id The action id (method name), also support [Handlers.DEFAULT].
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class SenderDeniedHandler(val id: String)

/**
 * Mark a handler to deal with permission denying.
 *
 * @param id The action id (method name), also support [Handlers.DEFAULT].
 * @author Yoooooory
 */
@Target(AnnotationTarget.FUNCTION)
annotation class PermissionDeniedHandler(val id: String)