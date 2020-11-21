package com.rabbitown.yalib.command

import com.rabbitown.yalib.annotation.command.Action
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

/**
 * Represents a command running result.
 *
 * @author Yoooooory
 */
enum class CommandResult {

    /** Succeed to run the command. */
    SUCCESS,

    /** Failed to use the command because the sender type mismatch (the command required). */
    FAILED_COMMAND_SENDER,

    /** Failed to use the command because the sender hasn't permission (the command required). */
    FAILED_COMMAND_PERMISSION,

    /** Failed to use the command because the sender type mismatch (the action required). */
    FAILED_ACTION_SENDER,

    /** Failed to use the command because the sender hasn't permission (the action required). */
    FAILED_ACTION_PERMISSION;

    companion object {

        /**
         * Get a command running result.
         *
         * @param handler The handler in the handling.
         * @param action The action in the handling.
         * @param sender The sender of the command.
         * @param sendMessage Whether send command failed message to the sender or not.
         */
        @JvmStatic
        fun getCommandResult(handler: CommandHandler, action: Action, sender: CommandSender, sendMessage: Boolean = false) =
            when {
                // Check permission.
                !checkSenderPerm(sender, handler.command.permission) -> {
                    if (sendMessage) sendPermFailed(sender, handler.command.permission, handler.getPermissionMessage())
                    FAILED_COMMAND_PERMISSION
                }
                !checkSenderPerm(sender, action.permission) -> {
                    if (sendMessage) sendPermFailed(sender, handler.command.permission, handler.getPermissionMessage())
                    FAILED_ACTION_PERMISSION
                }
                // Check sender
                !checkSenderType(sender, handler.sender) -> {
                    if (sendMessage) sendSenderFailed(sender, handler.sender, handler.getSenderMessage())
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

        private fun sendSenderFailed(sender: CommandSender, type: CommandSenderType, message: String) = sender.sendMessage(
            message.replace("{sender}", type.toString())
        )

        private fun checkSenderPerm(sender: CommandSender, perm: String?) = perm == null || sender.hasPermission(perm)

        private fun sendPermFailed(sender: CommandSender, perm: String?, message: String?) =
            if (message != null) sender.sendMessage(
                message.replace("{permission}", perm!!)
            ) else Unit

    }

}