package com.rabbitown.yalib.annotation.command

import com.rabbitown.yalib.command.CommandSenderType

/**
 * Used to indicate the command action.
 *
 * @param action The action, solve as a regex.
 * @param priority The priority of the action, default for 0. Higher priority will make the action run earlier.
 *
 * @author Yoooooory
 */
@Repeatable
@Target(AnnotationTarget.FUNCTION)
annotation class Action(
    val permission: String = "",
    val priority: Int = 0,
    val permissionMessage: String = "§cYou don't have permission to use this command.",
    val sender: CommandSenderType = CommandSenderType.ALL,
    val senderMessage: String = "§cOnly {sender} can use the command.",
    vararg val action: String
)