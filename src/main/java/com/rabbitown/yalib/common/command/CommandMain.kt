package com.rabbitown.yalib.common.command

import com.rabbitown.yalib.command.CommandRemote
import com.rabbitown.yalib.command.annotation.Action
import com.rabbitown.yalib.command.annotation.Completer
import com.rabbitown.yalib.command.annotation.Handlers

/**
 * @author Yoooooory
 */
class CommandMain : CommandRemote("yalib") {

    @Action("help {b}")
    fun help() = "test!"

    @Completer("help")
    fun helpTab() = mapOf("b" to arrayOf("acdfh", "acgas"))

    @Completer(Handlers.DEFAULT)
    fun defaultTab() = listOf("help")

}