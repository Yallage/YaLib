package com.rabbitown.yalib.common.command

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.command.CommandSenderType
import com.rabbitown.yalib.command.SimpleCommandRemote
import com.rabbitown.yalib.command.annotation.*
import org.bukkit.command.CommandSender

/**
 * @author Yoooooory
 */
@Path("test")
class CommandTest : SimpleCommandRemote("yalib", YaLib.instance) {

    @Action("playerOnly")
    @Access(sender = [CommandSenderType.PLAYER])
    fun playerOnly() = "You are a player!"

    @Action("consoleOnly")
    @Access(sender = [CommandSenderType.CONSOLE])
    fun consoleOnly() = "You are a console!"

    @Action("speedTest")
    @Priority(-1)
    fun speedTest(start: Long) = "Processing time: ${System.nanoTime() - start}"

    @Action("speedTest {tab}")
    fun tabSpeedTest(start: Long) = "Processing time: ${System.nanoTime() - start}"

    @Completer("tabSpeedTest")
    fun speedTestTab(start: Long, sender: CommandSender): String {
        sender.sendMessage("Processing time: ${System.nanoTime() - start}")
        return "succeed"
    }

    @Completer(Handlers.REMOTE)
    fun defaultTab() = arrayOf("playerOnly", "consoleOnly", "speedTest")

}