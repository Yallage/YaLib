package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import java.lang.reflect.Method
import java.util.Comparator

/**
 * @author Yoooooory
 */
internal class CommandProcessor(val remote: CommandRemote) : TabExecutor {

    val actions = mutableListOf<CommandHandler.ActionHandler>()
    val defaults = mutableListOf<CommandHandler.DependentHandler>()

    init {
        addRemote(remote)
    }

    fun addRemote(remote: CommandRemote) {
        val actions = mutableListOf<Method>()
        val completers = mutableMapOf<String, MutableList<Method>>()
        val sdh = mutableMapOf<String, MutableList<Method>>()
        val pdh = mutableMapOf<String, MutableList<Method>>()
        remote::class.java.declaredMethods.forEach { method ->
            method.declaredAnnotations.forEach {
                when (it) {
                    is Action -> actions += method
                    is Completer -> completers[it.id] = (completers[it.id] ?: mutableListOf()).apply { add(method) }
                    is SenderDeniedHandler -> sdh[it.id] = (sdh[it.id] ?: mutableListOf()).apply { add(method) }
                    is PermissionDeniedHandler -> pdh[it.id] = (pdh[it.id] ?: mutableListOf()).apply { add(method) }
                }
            }
        }
        this.actions += actions.map {
            val name = it.name
            CommandHandler.ActionHandler(it, completers[name], sdh[name], pdh[name])
        }
        completers.forEach { _, v -> v.forEach { if (it) } }
        this.actions.sortedWith(Comparator.comparingInt(CommandHandler.ActionHandler::getPriority))
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): List<String> {
        TODO("Not yet implemented")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        TODO("Not yet implemented")
    }

}