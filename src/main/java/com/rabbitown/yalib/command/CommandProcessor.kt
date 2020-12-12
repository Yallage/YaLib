package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.*
import com.rabbitown.yalib.command.annotation.Handlers.Companion.isDefault
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
        val sdhMap = mutableMapOf<String, MutableList<Method>>()
        val pdhMap = mutableMapOf<String, MutableList<Method>>()
        remote::class.java.declaredMethods.forEach { method ->
            method.declaredAnnotations.forEach {
                when (it) {
                    is Action -> actions += method
                    is Completer -> completers[it.id] = (completers[it.id] ?: mutableListOf()).apply { add(method) }
                    is SenderDeniedHandler -> sdhMap[it.id] = (sdhMap[it.id] ?: mutableListOf()).apply { add(method) }
                    is PermissionDeniedHandler -> pdhMap[it.id] =
                        (pdhMap[it.id] ?: mutableListOf()).apply { add(method) }
                }
            }
        }
        this.actions += actions.map {
            val name = it.name
            CommandHandler.ActionHandler(it, completers[name], sdhMap[name], pdhMap[name])
        }
        completers.forEach { (_, v) ->
            v.forEach {
                val completer = Completer.get(it)
                if (completer.isDefault()) defaults += CommandHandler.DependentHandler(completer.id, it)
            }
        }
        sdhMap.forEach { (_, v) ->
            v.forEach {
                val sdh = SenderDeniedHandler.get(it)
                if (sdh.isDefault()) defaults += CommandHandler.DependentHandler(sdh.id, it)
            }
        }
        pdhMap.forEach { (_, v) ->
            v.forEach {
                val pdh = PermissionDeniedHandler.get(it)
                if (pdh.isDefault()) defaults += CommandHandler.DependentHandler(pdh.id, it)
            }
        }
        this.actions.sortedWith(Comparator.comparingInt(CommandHandler::getPriority))
        this.defaults.sortedWith(Comparator.comparingInt(CommandHandler::getPriority))
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