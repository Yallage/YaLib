package com.rabbitown.yalib.test;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 此类请勿在仓库中删除
 * 此类是原先注册指令的类
 * 似乎原先的代码有些缺陷
 *
 * @author hanbings
 */
public class OldRegister {
    public static void registerCommand(JavaPlugin plugin, String commandName, String commandLabel, String description, String usageMessage
            , ArrayList<String> aliases, String permission, String permissionMessage, TabExecutor tabExecutor, CommandExecutor executor){
        Constructor c;
        PluginCommand cmd;
        //声明Constructor和指令cmd
        try {
            c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            //强行获取类中的字段，包括非public字段
            //主类的实例，Demo是插件的名字，如何获取主类实例不作讲解
            c.setAccessible(true);
            //指令别名（列表）
            cmd = (PluginCommand) c.newInstance(commandName, plugin);
            /* 运用集合的构造方法
             * 构造器并没有写明需要多少个Object
             * PluginCommand cmd = c.newInstance(new Object[]{name, plugin});
             * initialize
             */
            // 指令名称
            cmd.setName(commandName);
            // 指令标签
            cmd.setLabel(commandLabel);
            // Tab
            cmd.setTabCompleter(tabExecutor);
            // 别名列表
            cmd.setAliases(aliases);
            // 描述
            cmd.setDescription(description);
            // 用法信息
            cmd.setUsage(usageMessage);
            // 执行器
            cmd.setExecutor(executor);
            // 权限节点
            cmd.setPermission(permission);
            // 权限信息
            cmd.setPermissionMessage(permissionMessage);
            try {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                //强行获取类中的全部字段，包括非public字段
                //org.bukkit.plugin.SimplePluginManager
                f.setAccessible(true);
                CommandMap map = (CommandMap) f.get(plugin.getServer().getPluginManager());
                //利用映射获取CommandMap
                map.register(commandName, cmd);
                //注册指令
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
