package com.rabbitown.yalib.command.annotation

import com.rabbitown.yalib.command.CommandRemote
import com.rabbitown.yalib.command.CommandSenderType
import java.lang.reflect.Method

/**
 * Util class for limiters of command handlers.
 * Limiters including [Access], [Path], [Priority].
 *
 * @author Yoooooory
 */
class Limiters private constructor()

/**
 * Mark the ability to access the command.
 * Not work if no parameters were provided.
 *
 * @param sender The sender should be a type of them.
 * @param permission The sender should have all the following permissions.
 * @author Yoooooory
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Access(val sender: Array<CommandSenderType> = [], val permission: Array<String> = []) {
    companion object {
        fun get(remote: Class<out CommandRemote>) = getOrDefault(remote.getDeclaredAnnotation(Access::class.java))
        fun get(handler: Method) = getOrDefault(handler.getDeclaredAnnotation(Access::class.java))
        fun getOrDefault(access: Access?) = access ?: Accessor::class.java.getAnnotation(Access::class.java)!!
    }

    @Access
    class Accessor private constructor()
}

/**
 * Mark the path of the command, which nearly equivalent to a string before an action.
 *
 * @param path The path of the action.
 * @author Yoooooory
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Path(val path: String) {
    companion object {
        fun get(remote: Class<out CommandRemote>) = getOrDefault(remote.getDeclaredAnnotation(Path::class.java))
        fun get(handler: Method) = getOrDefault(handler.getDeclaredAnnotation(Path::class.java))
        fun getOrDefault(path: Path?) = path?.path ?: ""
    }
}

/**
 * Mark the priority of the command, which the higher, the more likely it to be selected.
 *
 * @param priority The priority of the action to be selected.
 * @author Yoooooory
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Priority(val priority: Int) {
    companion object {
        fun get(remote: Class<out CommandRemote>) = getOrDefault(remote.getDeclaredAnnotation(Priority::class.java))
        fun get(handler: Method) = getOrDefault(handler.getDeclaredAnnotation(Priority::class.java))
        fun getOrDefault(priority: Priority?) = priority?.priority ?: 0
    }
}