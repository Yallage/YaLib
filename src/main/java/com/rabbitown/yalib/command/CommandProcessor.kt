package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.entity.CommandRunning
import com.rabbitown.yalib.command.entity.RemoteEntity
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

/**
 * @author Yoooooory
 */
internal class CommandProcessor() : TabExecutor {

    private val remotes = mutableListOf<RemoteEntity>()

    constructor(remote: CommandRemote) : this() {
        addRemote(remote)
    }

    fun addRemote(remote: CommandRemote) {
        remotes += RemoteEntity(remote)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val running = CommandRunning(sender, command, label, args)
        val sb = StringBuilder(if (args.isEmpty()) "" else args[0])
        for (i in 1 until args.size) sb.append(" ${args[i]}")
        // Looking for an effective remote. (detect paths)
        remotes.forEach { remote ->
            val path = remote.remote.path
            
        }
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): List<String> {
        TODO("Not yet implemented")
    }

}