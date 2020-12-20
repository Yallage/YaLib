package com.rabbitown.yalib.common.command

import com.rabbitown.yalib.command.CommandRemote
import com.rabbitown.yalib.command.CommandSenderType
import com.rabbitown.yalib.command.annotation.*

/**
 * @author Yoooooory
 */
@Path("test")
class CommandTest : CommandRemote("yalib") {

    //FIXME!!!!!!!
    @Action("playerOnly")
    @Access(sender = [CommandSenderType.PLAYER])
    fun playerOnly() = "You are a player!"

    @Action("consoleOnly")
    @Access(sender = [CommandSenderType.CONSOLE])
    fun consoleOnly() = "You are a console!"

    @Action("speedTest")
    fun speedTest(start: Long) = "Processing time: ${System.nanoTime() - start}"

    @Action("speedTest {tab}")
    fun tabSpeedTest(start: Long) = "Processing time: ${System.nanoTime() - start}"

    @Completer("tabSpeedTest")
    fun speedTestTab(start: Long) = "Processing time: ${System.nanoTime() - start}"

    @Completer(Handlers.DEFAULT)
    fun defaultTab() = arrayOf("playerOnly", "consoleOnly", "speedTest")

}