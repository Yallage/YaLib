package com.rabbitown.yalib.common.command

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.command.CommandSenderType
import com.rabbitown.yalib.command.SimpleCommandRemote
import com.rabbitown.yalib.command.annotation.*
import com.rabbitown.yalib.world.Biome.Companion.moreInfo
import org.bukkit.OfflinePlayer
import org.bukkit.block.Biome
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.lang.Exception

/**
 * @author Yoooooory
 */
@Path("test")
@Access(["yalib.admin"])
class CommandTest : SimpleCommandRemote("yalib", YaLib.instance) {

    @Action("smartCast {player}")
    fun test(player: Player) {
        player.sendMessage("YEAH !!!!")
    }

    @Action("playerOnly")
    @Access(sender = [CommandSenderType.PLAYER])
    fun playerOnly() = "You are a player!"

    @Action("consoleOnly")
    @Access(sender = [CommandSenderType.CONSOLE])
    fun consoleOnly() = "You are a console!"

    @Action("speedTest")
    fun speedTest(start: Long) = "Processing time: ${System.nanoTime() - start}"

    @Action("speedTest {tab}")
    @Priority(1)
    fun tabSpeedTest(start: Long) = "Processing time: ${System.nanoTime() - start}"

    @Completer("tabSpeedTest")
    fun speedTestTab(start: Long, sender: CommandSender): String {
        sender.sendMessage("Processing time: ${System.nanoTime() - start}")
        return "succeed"
    }

    @Action("biomesTest")
    fun biomesTest(sender: CommandSender) {
        val errors = mutableListOf<String>()
        sender.sendMessage(arrayOf("", "=== Testing ==="))
        Biome.values().forEach {
            val sb = StringBuilder("$it -> ")
            try {
                val biome = it.moreInfo()
                if (it.key == biome.key) {
                    sb.append("§aKey matches! ${biome.type} ${biome.temperature}")
                } else errors += sb.append("§cKey doesn't matches. Expect: ${it.key}, but got: ${biome.key}.").toString()
            } catch (e: Exception) {
                errors += sb.append("§cError: $e").toString()
            }
            sender.sendMessage(sb.toString())
        }
        sender.sendMessage(arrayOf("", "=== Errors ==="))
        if (errors.size == 0) sender.sendMessage("§aNo errors!")
        else sender.sendMessage(errors.toTypedArray())
    }

    @Completer(Handlers.REMOTE)
    fun defaultTab() = arrayOf("playerOnly", "consoleOnly", "speedTest")

}