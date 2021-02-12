package com.rabbitown.yalib.common.command

import com.rabbitown.yalib.YaLib
import com.rabbitown.yalib.module.command.SimpleCommandRemote
import com.rabbitown.yalib.module.command.annotation.Access
import com.rabbitown.yalib.module.command.annotation.Action
import com.rabbitown.yalib.module.command.annotation.Path
import com.rabbitown.yalib.module.nms.base.block.NMSBlock
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import kotlin.system.measureNanoTime

/**
 * @author Yoooooory
 */
@Path("test")
@Access(["yalib.admin"])
class TestCommand : SimpleCommandRemote("yalib", YaLib.instance) {

    @Action("sound")
    fun sound(sender: CommandSender): String {
        val block = Bukkit.getWorld("world")!!.getBlockAt(0, 0, 0)
        val function = { NMSBlock.get(block).getSoundEffects().getStepSound(); Unit }
        sender.sendMessage("First 10 times: ")
        for (i in 1..10) sender.sendMessage("$i: ${measureNanoTime(function)}")
        sender.sendMessage("Then 100 times: ")
        var sum = 0L
        for (i in 1..100) {
            val time = measureNanoTime(function)
            sum += time
            sender.sendMessage("$i: $time")
        }
        return "100 times on average: " + (sum / 100.0)
    }

}