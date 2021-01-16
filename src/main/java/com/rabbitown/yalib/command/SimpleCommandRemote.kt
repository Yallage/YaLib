package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.Action
import com.rabbitown.yalib.command.annotation.Handlers
import com.rabbitown.yalib.command.annotation.Priority
import com.rabbitown.yalib.command.annotation.SenderDeniedHandler
import com.rabbitown.yalib.util.Commands
import org.bukkit.command.PluginCommand
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Yoooooory
 */
open class SimpleCommandRemote(command: PluginCommand) : CommandRemote(command) {

    constructor(name: String, plugin: JavaPlugin) : this(name, plugin, emptyList())

    constructor(
        name: String, plugin: JavaPlugin,
        aliases: List<String> = emptyList(), description: String = "No default action or description provided.",
        usage: String = "No default action or usage provided."
    ) : this(
        Commands.getCommandOrThis(
            CommandBuilder(name, plugin).aliases(aliases).description(description).usage(usage).command
        )
    )

    /**
     * Register the remote to [CommandManager].
     *
     * @return Whether the remote was registered successfully.
     */
    fun register() = CommandManager.register(this).isNotEmpty()

    /** The default action of command handler, which has the lowest priority.
     *  You can override it by define a new function `alternate(params..)`
     *  with annotation `@Action(Handlers.DEFAULT)`. */
    @Action(Handlers.DEFAULT)
    @Priority(Int.MIN_VALUE)
    open fun defaultAction() = "Pong!"

    /** The default sender denied handler of command handler, which has the lowest priority.
     *  You can override it by define a new function `defaultSDH(params..)`
     *  with annotation `@SenderDeniedHandler(Handlers.DEFAULT)`. */
    @SenderDeniedHandler(Handlers.DEFAULT)
    @Priority(Int.MIN_VALUE)
    open fun defaultSenderDeniedHandler(senderType: Array<CommandSenderType>) =
        "§cOnly ${senderType.contentToString()} can use the command."

    /** The default permission denied handler of command handler, which has the lowest priority.
     *  You can override it by define a new function `defaultPDH(params..)`
     *  with annotation `@PermissionDeniedHandler(Handlers.DEFAULT)`. */
    @SenderDeniedHandler(Handlers.DEFAULT)
    @Priority(Int.MIN_VALUE)
    open fun defaultPermissionDeniedHandler(perm: Array<String>) =
        command.permissionMessage ?: "Permission required: ${perm.contentToString()}"

}