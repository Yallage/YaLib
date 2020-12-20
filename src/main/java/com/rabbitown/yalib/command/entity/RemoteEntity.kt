package com.rabbitown.yalib.command.entity

import com.rabbitown.yalib.command.CommandRemote
import com.rabbitown.yalib.command.Limitable
import com.rabbitown.yalib.command.annotation.*
import com.rabbitown.yalib.command.annotation.Handlers.Companion.isDefault
import java.lang.reflect.Method

/**
 * @author Yoooooory
 */
data class RemoteEntity(val remote: CommandRemote) : Limitable {

    override val access = remote.access
    override val path = remote.path
    override val priority = remote.priority

    val actions: List<ActionHandler>
    val defaultCompleters: List<CompleterHandler>
    val defaultSenderDeniedHandlers: List<AccessHandler>
    val defaultPermissionDeniedHandlers: List<AccessHandler>

    init {
        val actions = mutableListOf<Method>()
        val tabMap = mutableMapOf<String, MutableList<Method>>() // aka completerMap
        val sdhMap = mutableMapOf<String, MutableList<Method>>() // aka senderDeniedHandlerMap
        val pdhMap = mutableMapOf<String, MutableList<Method>>() // aka permissionDeniedHandlerMap
        remote::class.java.declaredMethods.forEach { method ->
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

    fun runDefaultSenderDeniedHandler(running: CommandRunning) =
        AccessHandler.getAccessibleOrNull(defaultSenderDeniedHandlers, running.sender)?.invoke(remote, running)

    fun runDefaultPermissionDeniedHandler(running: CommandRunning) =
        AccessHandler.getAccessibleOrNull(defaultPermissionDeniedHandlers, running.sender)?.invoke(remote, running)

    fun runDefaultCompleter(index: Int, path: String, running: CommandRunning) =
        CompleterHandler.getAccessibleOrNull(defaultCompleters, running.sender)?.invoke(index, path, remote, running)
            ?: emptyList()

}