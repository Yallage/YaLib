package com.rabbitown.yalib.module.command.annotation

import com.rabbitown.yalib.module.command.CommandRemote
import com.rabbitown.yalib.module.command.CommandSenderType
import java.lang.reflect.Method

/**
 * Mark the ability to access the command.
 * Not work if no parameters were provided.
 *
 * @param sender The sender should be a type of them.
 * @param permission The sender should have all the following permissions.
 * @author Milkory
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Access(val permission: Array<String> = [], val sender: Array<CommandSenderType> = []) {
    companion object {
        fun get(remote: Class<out CommandRemote>) = getOrDefault(remote.getDeclaredAnnotation(Access::class.java))
        fun get(handler: Method) = getOrDefault(handler.getDeclaredAnnotation(Access::class.java))
        fun getOrDefault(access: Access?) =
            access ?: DefaultAccess::class.java.getDeclaredAnnotation(Access::class.java)!!
    }

    @Access
    class DefaultAccess private constructor()
}

/**
 * Mark the path of the command, which nearly equivalent to a string before an action.
 *
 * @param path The path of the action.
 * @author Milkory
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Path(val path: String, val ignoreCase: Boolean = true) {
    companion object {
        fun get(remote: Class<out CommandRemote>) = getOrDefault(remote.getDeclaredAnnotation(Path::class.java))
        fun get(handler: Method) = getOrDefault(handler.getDeclaredAnnotation(Path::class.java))
        fun getOrDefault(path: Path?) =
            path ?: DefaultPath::class.java.getDeclaredAnnotation(Path::class.java)!!
    }

    @Path("")
    class DefaultPath private constructor()
}

/**
 * Mark the priority of the command, which the higher, the more likely it to be selected.
 *
 * @param priority The priority of the action to be selected.
 * @author Milkory
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Priority(val priority: Int) {
    companion object {
        fun get(remote: Class<out CommandRemote>) = getOrDefault(remote.getDeclaredAnnotation(Priority::class.java))
        fun get(handler: Method) = getOrDefault(handler.getDeclaredAnnotation(Priority::class.java))
        fun getOrDefault(priority: Priority?) = priority?.priority ?: 0
    }
}