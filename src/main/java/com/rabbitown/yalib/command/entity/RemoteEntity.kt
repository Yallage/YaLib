package com.rabbitown.yalib.command.entity

import com.rabbitown.yalib.command.CommandRemote
import com.rabbitown.yalib.command.annotation.*
import com.rabbitown.yalib.command.annotation.Handlers.Companion.isDefault
import java.lang.reflect.Method

/**
 * @author Yoooooory
 */
data class RemoteEntity(val remote: CommandRemote) {

    val actions: List<ActionHandler>
    val defaultCompleters: List<DependentHandler>
    val defaultSenderDeniedHandler: List<DependentHandler>
    val defaultPermissionDeniedHandler: List<DependentHandler>

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
            tabMap.values.asSequence().flatten().map { Pair(it, Completer.get(it)) }.filter { it.second.isDefault() }
                .map { DependentHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
        this.defaultSenderDeniedHandler =
            sdhMap.values.asSequence().flatten().map { Pair(it, Completer.get(it)) }.filter { it.second.isDefault() }
                .map { DependentHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
        this.defaultPermissionDeniedHandler =
            pdhMap.values.asSequence().flatten().map { Pair(it, Completer.get(it)) }.filter { it.second.isDefault() }
                .map { DependentHandler(it.second.id, it.first) }.sortedWith(CommandHandler.sortByPriority()).toList()
    }
}