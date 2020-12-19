package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.Handlers
import com.rabbitown.yalib.command.entity.CommandHandler
import com.rabbitown.yalib.command.entity.CommandRunning
import com.rabbitown.yalib.command.entity.RemoteEntity
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import java.util.regex.Pattern

/**
 * @author Yoooooory
 */
internal class CommandProcessor() : TabExecutor {

    private val argRegex = Regex("\\{(\\w+)(: ?(.+))?}")

    private val remotes = mutableListOf<RemoteEntity>()

    constructor(remote: CommandRemote) : this() {
        addRemote(remote)
    }

    fun addRemote(remote: CommandRemote) {
        remotes += RemoteEntity(remote)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val running = CommandRunning(sender, command, label, args)
        val sb = StringBuilder(if (args.isEmpty()) "" else args[0])
        for (i in 1 until args.size) sb.append(" ${args[i]}")
        // Looking for an effective remote. (detect paths)
        val remote = remotes.firstOrNull { remote ->
            val path = remote.remote.path
            if (path.isEmpty()) return@firstOrNull true
            val matcher = Pattern.compile(path.replace(argRegex) { it.groups[3]?.value ?: ".+" }).matcher(sb.toString())
            if (matcher.find() && matcher.start() == 1) {
                // For an effective remote, check whether the sender is accessible to it.
                when (CommandResult.getCommandResult(remote.remote, sender)) {
                    CommandResult.SUCCESS -> {
                        running.putArguments(path, sb.toString())
                        sb.replace(0, sb.length, sb.substring(matcher.end()))
                        return@firstOrNull true
                    }
                    CommandResult.FAILED_SENDER_MISMATCH ->
                        remote.runSenderDeniedHandler(Handlers.DEFAULT, running)
                    CommandResult.FAILED_PERMISSION_REQUIRED ->
                        remote.runPermissionDeniedHandler(Handlers.DEFAULT, running)
                }
                return true
            } else false
        } ?: return false
        // Looking for an effective remote. (detect paths)
        val action = remote.actions.firstOrNull out@{ handler ->
            // Magic code, DO NOT edit it!!
            (handler.action as Array<*>).firstOrNull { action ->
                val path = "${if (handler.path.isEmpty()) "" else "${handler.path} "}$action"
                if (path.isEmpty()) return@firstOrNull true
                if (sb.toString().matches(Regex(path.replace(argRegex) { it.groups[3]?.value ?: ".+" }))) {
                    // For an effective action, check whether the sender is accessible to it.
                    when (CommandResult.getCommandResult(handler, sender)) {
                        CommandResult.SUCCESS -> {
                            running.putArguments(path, sb.toString())
                            return@firstOrNull true
                        }
                        CommandResult.FAILED_SENDER_MISMATCH ->
                            remote.runSenderDeniedHandler(handler.id, running)
                        CommandResult.FAILED_PERMISSION_REQUIRED ->
                            remote.runPermissionDeniedHandler(handler.id, running)
                    }
                    return true
                } else false
            } != null // means @action found a matched action, then the handler is what we need.
        } ?: return false
        // Running command.
        invokeHandler(remote.remote, action, running)
        return true
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): List<String> {
        // TODO
        return emptyList()
    }

    private fun invokeHandler(remote: CommandRemote, handler: CommandHandler, running: CommandRunning) {
        val method = handler.handler
        val result = method.invoke(remote, *method.parameters.map { running.getArgument(it.name) }.toTypedArray())
        if (result != null) running.sender.sendMessage(result.toString())
    }

}