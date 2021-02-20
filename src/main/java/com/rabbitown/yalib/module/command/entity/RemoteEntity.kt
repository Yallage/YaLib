package com.rabbitown.yalib.module.command.entity

import com.rabbitown.yalib.module.command.CommandRemote
import com.rabbitown.yalib.module.command.MainHandler
import com.rabbitown.yalib.module.command.annotation.*
import com.rabbitown.yalib.module.command.annotation.Handlers.Companion.isDefault
import com.rabbitown.yalib.module.command.annotation.Handlers.Companion.isOwnedByRemote
import java.lang.reflect.Method

// TODO: should be organized.

/**
 * @author Yoooooory
 */
data class RemoteEntity(val remote: CommandRemote) : MainHandler {

    override val path: String
    override val ignoreCase: Boolean
    override val access = remote.access
    override val priority = remote.priority

    init {
        val annotation = Path.get(remote::class.java)
        path = annotation.path
        ignoreCase = annotation.ignoreCase
    }

    val actions: List<ActionHandler>
    val completers: List<CompleterHandler>
    val senderDeniedHandlers: List<AccessHandler>
    val permissionDeniedHandlers: List<AccessHandler>

    val defaultActions: List<ActionHandler>
    val defaultCompleters: List<CompleterHandler>
    val defaultSenderDeniedHandlers: List<AccessHandler>
    val defaultPermissionDeniedHandlers: List<AccessHandler>

    private val argRegex = Regex("\\{(\\w+)(: ?(.+))?}")

    init {
        val actions = mutableListOf<Method>()
        val tabMap = mutableMapOf<String, MutableList<Method>>() // aka completerMap
        val sdhMap = mutableMapOf<String, MutableList<Method>>() // aka senderDeniedHandlerMap
        val pdhMap = mutableMapOf<String, MutableList<Method>>() // aka permissionDeniedHandlerMap
        remote::class.java.methods.forEach { method ->
            method.declaredAnnotations.forEach {
                when (it) {
                    is Action -> actions += method
                    is Completer -> tabMap[it.id] = (tabMap[it.id] ?: mutableListOf()).apply { add(method) }
                    is SenderDeniedHandler -> sdhMap[it.id] = (sdhMap[it.id] ?: mutableListOf()).apply { add(method) }
                    is PermissionDeniedHandler -> pdhMap[it.id] =
                        (pdhMap[it.id] ?: mutableListOf()).apply { add(method) }
                }
            }
        }
        this.actions = actions.map { ActionHandler(it, tabMap[it.name], sdhMap[it.name], pdhMap[it.name]) }
            .sortedWith(CommandHandler.sortByPriority())
        this.completers =
            tabMap.values.asSequence().flatten().map { Pair(it, Completer.get(it)) }
                .filter { it.second.isOwnedByRemote() }
                .map { CompleterHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
        this.senderDeniedHandlers =
            sdhMap.values.asSequence().flatten().map { Pair(it, SenderDeniedHandler.get(it)) }
                .filter { it.second.isOwnedByRemote() }
                .map { AccessHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
        this.permissionDeniedHandlers =
            pdhMap.values.asSequence().flatten().map { Pair(it, PermissionDeniedHandler.get(it)) }
                .filter { it.second.isOwnedByRemote() }
                .map { AccessHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
        this.defaultActions = actions.filter { Action.get(it).isDefault() }
            .map { ActionHandler(it, tabMap[it.name], sdhMap[it.name], pdhMap[it.name]) }
            .sortedWith(CommandHandler.sortByPriority())
        this.defaultCompleters =
            tabMap.values.asSequence().flatten().map { Pair(it, Completer.get(it)) }
                .filter { it.second.isDefault() }
                .map { CompleterHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
        this.defaultSenderDeniedHandlers =
            sdhMap.values.asSequence().flatten().map { Pair(it, SenderDeniedHandler.get(it)) }
                .filter { it.second.isDefault() }
                .map { AccessHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
        this.defaultPermissionDeniedHandlers =
            pdhMap.values.asSequence().flatten().map { Pair(it, PermissionDeniedHandler.get(it)) }
                .filter { it.second.isDefault() }
                .map { AccessHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
    }

    fun runSenderDeniedHandler(running: CommandRunning) =
        AccessHandler.getAccessibleOrNull(senderDeniedHandlers + defaultSenderDeniedHandlers, running.sender)?.invoke(remote, remote, running)

    fun runPermissionDeniedHandler(running: CommandRunning) =
        AccessHandler.getAccessibleOrNull(permissionDeniedHandlers + defaultPermissionDeniedHandlers, running.sender)
            ?.invoke(remote, remote, running)

    fun runDefaultCompleter(index: Int, path: String, running: CommandRunning): List<String> {
        val split = path.replace(argRegex) { it.groups[1]?.value ?: error("") }.split(" ")
        return if (split.size > index) CompleterHandler.getAccessibleOrNull(defaultCompleters, running.sender)
            ?.invoke(split[index], remote, running) ?: emptyList()
        else CompleterHandler.getAccessibleOrNull(defaultCompleters, running.sender)
            ?.invoke("", remote, running) ?: emptyList()
    }

    fun runCompleter(index: Int, path: String, running: CommandRunning): List<String> {
        val split = path.replace(argRegex) { it.groups[1]?.value ?: error("") }.split(" ")
        return if (split.size > index) CompleterHandler.getAccessibleOrNull(completers + defaultCompleters, running.sender)
            ?.invoke(split[index], remote, running) ?: emptyList()
        else CompleterHandler.getAccessibleOrNull(completers, running.sender)
            ?.invoke("", remote, running) ?: emptyList()
    }

    fun runCompleter(running: CommandRunning): List<String> =
        CompleterHandler.getAccessibleOrNull(completers, running.sender)
            ?.invoke("first", remote, running) ?: emptyList()

}