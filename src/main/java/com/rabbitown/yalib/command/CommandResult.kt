package com.rabbitown.yalib.command

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
         * @param sendMessage Whether send command failed message to the sender or not.
         */
        @JvmStatic
        fun getCommandResult(remote: CommandRemote, action: Action, sender: CommandSender, sendMessage: Boolean) =
            when {
                // Check permission.
                !checkSenderPerm(sender, remote.command.permission) -> {
                    if (sendMessage) sendPermFailed(sender, remote.command.permission, remote.defaultPermissionDeniedHandler())
                    FAILED_COMMAND_PERMISSION
                }
                !checkSenderPerm(sender, action.permission) -> {
                    if (sendMessage) sendPermFailed(sender, remote.command.permission, remote.defaultPermissionDeniedHandler())
                    FAILED_ACTION_PERMISSION
                }
                // Check sender
                !checkSenderType(sender, remote.sender) -> {
                    if (sendMessage) sendSenderFailed(sender, remote.sender, remote.defaultSenderDeniedHandler())
                    FAILED_COMMAND_SENDER
                }
                !checkSenderType(sender, action.sender) -> {
                    if (sendMessage) sendSenderFailed(sender, action.sender, action.senderMessage)
                    FAILED_ACTION_SENDER
                }
                else -> SUCCESS
            }

        private fun checkSenderType(sender: CommandSender, type: CommandSenderType) = when {
            type == CommandSenderType.ALL -> true
            sender is Player -> type == CommandSenderType.PLAYER
            sender is ConsoleCommandSender -> type == CommandSenderType.CONSOLE
            else -> type == CommandSenderType.OTHER
        }

        private fun sendSenderFailed(sender: CommandSender, type: CommandSenderType, message: String) =
            sender.sendMessage(message.replace("{sender}", type.toString()))

        private fun checkSenderPerm(sender: CommandSender, perm: String?) = perm == null || sender.hasPermission(perm)

        private fun sendPermFailed(sender: CommandSender, perm: String?, message: String?) =
            if (message != null) sender.sendMessage(message.replace("{permission}", perm!!)) else Unit

    }

}