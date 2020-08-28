package com.rabbitown.yalib.command;

import com.rabbitown.yalib.annotation.command.Action;
import com.rabbitown.yalib.annotation.command.ExtendHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.omg.CORBA.portable.UnknownException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Used to manage some orders to register command.
 *
 * @author Yoooooory
 */
public class CommandFactory {

    protected static boolean registerCommand(Command command, JavaPlugin plugin) {
        try {
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);
            return ((CommandMap) field.get(Bukkit.getPluginManager())).register(plugin.getDescription().getName(), command);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        return false;
    }

    public static PluginCommand newPluginCommand(String name, JavaPlugin plugin) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            return constructor.newInstance(name, plugin);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new UnknownException(e);
        }
    }

    public static List<PluginCommand> getCommands(CommandHandler handler, JavaPlugin plugin) {
        List<PluginCommand> commands = new ArrayList<>();
        commands.add(handler.getCommand());
        for (ExtendHandler extHandler : handler.getClass().getAnnotationsByType(ExtendHandler.class)) {
            PluginCommand command = newPluginCommand(extHandler.name(), plugin);
            command.setDescription(extHandler.description())
                    .setAliases(Arrays.asList(extHandler.aliases()))
                    .setPermissionMessage(extHandler.permissionMessage())
                    .setUsage(extHandler.usage())
                    .setPermission(extHandler.permission());
            commands.add(command);
        }
        return commands;
    }

    public static CommandResult getCommandResult(CommandSenderType handlerSender, PluginCommand command, Action action, CommandSender sender) {
        if (sender instanceof Player) {
            if (handlerSender == CommandSenderType.CONSOLE) return CommandResult.FAILED_COMMAND_SENDER;
            if (command.getPermission() != null && !sender.hasPermission(command.getPermission()))
                return CommandResult.FAILED_COMMAND_PERMISSION;
            if (action.sender() == CommandSenderType.CONSOLE) return CommandResult.FAILED_ACTION_SENDER;
            if (!action.permission().isEmpty() && !sender.hasPermission(action.permission()))
                return CommandResult.FAILED_ACTION_PERMISSION;
        } else {
            if (handlerSender == CommandSenderType.PLAYER) return CommandResult.FAILED_COMMAND_SENDER;
            if (action.sender() == CommandSenderType.PLAYER) return CommandResult.FAILED_ACTION_SENDER;
        }
        return CommandResult.SUCCESS;
    }

}