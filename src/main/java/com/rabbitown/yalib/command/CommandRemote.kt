package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.Access
import com.rabbitown.yalib.command.annotation.Path
import com.rabbitown.yalib.command.annotation.Priority
import org.bukkit.command.PluginCommand

/**
 * Represent a command remote.
 *
 * @author Yoooooory
 */
abstract class CommandRemote(val command: PluginCommand) : MainHandler {

    final override val path: String
    final override val ignoreCase: Boolean
    final override val access = Access.get(this::class.java)
    final override val priority = Priority.get(this::class.java)

    init {
        val annotation = Path.get(this::class.java)
        path = annotation.path
        ignoreCase = annotation.ignoreCase
    }

}