package com.rabbitown.yalib.command;

import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * 设置无需写YAML的注册指令
 * No YAML Register Command
 *
 * @author hanbings
 */
public class Register {
    /**
     * 设置无需写YAML的注册指令
     * No YAML Register Command
     *
     * @param plugin JavaPlugin
     * @param commandName 指令名称
     * @param commandLabel 指令标签
     * @param description 描述
     * @param usageMessage 用法信息
     * @param aliases 指令别名
     * @param permission 权限节点
     * @param permissionMessage 权限信息
     * @author hanbings
     */
    public static void registerCommand(JavaPlugin plugin, String commandName, String commandLabel, String description, String usageMessage
    , ArrayList<String> aliases, String permission, String permissionMessage){
        Constructor constructor;
        Command Command;
        try {
            constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            /* 运用集合的构造方法
             * 构造器并没有写明需要多少个Object
             * PluginCommand cmd = c.newInstance(new Object[]{name, plugin});
             * initialize
             */
            try {
                Command = (Command) constructor.newInstance(commandName, plugin);
                ////////////////////////////////////////////////////////
                // 插件名字
                Command.setName(commandName);
                // 设置标签
                Command.setLabel(commandLabel);
                // 设置描述
                Command.setDescription(description);
                // 设置用法信息
                Command.setUsage(usageMessage);
                // 设置插件别名
                Command.setAliases(aliases);
                // 设置权限
                Command.setPermission(permission);
                // 设置权限信息
                Command.setPermissionMessage(permissionMessage);
                ////////////////////////////////////////////////////////
                try {
                    Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                    //强行获取类中的全部字段，包括非public字段
                    //org.bukkit.plugin.SimplePluginManager
                    f.setAccessible(true);
                    CommandMap map = (CommandMap) f.get(plugin.getServer().getPluginManager());
                    //利用映射获取CommandMap
                    map.register(commandName, Command);
                    //注册指令
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
