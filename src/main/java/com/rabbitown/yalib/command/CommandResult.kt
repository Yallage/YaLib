package com.rabbitown.yalib.command

import com.rabbitown.yalib.command.annotation.Access
import com.rabbitown.yalib.command.annotation.Action
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

/**
 * Enum of a command running result.
 *
 * @author Yoooooory
 */
enum class CommandResult {

    /** Succeed to run the command. */
    SUCCESS,

    /** Failed to use the command because the sender type mismatch. */
    FAILED_SENDER_MISMATCH,

    /** Failed to use the command because the sender hasn't permission. */
    FAILED_PERMISSION_REQUIRED;

    companion object {

        /**
         * Get a command running result.
         *
         * @param remote The handler in the handling.
         * @param action The action in the handling.
         * @param sender The sender of the command.
         */
        fun getCommandResult(remote: CommandRemote, action: CommandHandler.ActionHandler, sender: CommandSender) =
            when {
                !checkSenderType(sender, Access.get(remote::class.java).sender) ||
                        !checkSenderType(sender, action.access.sender) -> FAILED_SENDER_MISMATCH
                !checkSenderPerm(sender, Access.get(remote::class.java).permission) ||
                        !checkSenderPerm(sender, action.access.permission) -> FAILED_PERMISSION_REQUIRED
                else -> SUCCESS
            }

        private fun checkSenderType(sender: CommandSender, type: Array<CommandSenderType>) =
            if (type.isEmpty()) true else type.any { CommandSenderType.checkSender(sender, it) }

        private fun checkSenderPerm(sender: CommandSender, perm: Array<String>) = perm.all(sender::hasPermission)

    }

}