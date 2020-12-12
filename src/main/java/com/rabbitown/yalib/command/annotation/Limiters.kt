package com.rabbitown.yalib.command.annotation

import com.rabbitown.yalib.command.CommandSenderType

/**
 * Util class for limiters of command handlers.
 * Limiters including [Access], [Path], [Priority].
 *
 * @author Yoooooory
 */
class Limiters private constructor() {
    companion object {

    }
}

/**
 * Mark the ability to access the command.
 * Not work if no parameters were provided.
 *
 * @param sender The sender should be a type of them.
 * @param permission The sender should have all the following permissions.
 * @author Yoooooory
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Access(val sender: Array<CommandSenderType> = [], val permission: Array<String> = [])

/**
 * Mark the path of the command, which nearly equivalent to a string before an action.
 * Not work if no parameters were provided.
 *
 * @param path The path of the action.
 * @author Yoooooory
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Path(val path: String = "")

/**
 * Mark the priority of the command, which the higher, the more likely it to be selected.
 * Not work if no parameters were provided.
 *
 * @param priority The priority of the action to be selected.
 * @author Yoooooory
 */
annotation class Priority(val priority: Int = 0)