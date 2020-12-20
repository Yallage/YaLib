package com.rabbitown.yalib.common.command;

import com.rabbitown.yalib.YaLib;
import com.rabbitown.yalib.gui.GUIFactory;
import com.rabbitown.yalib.locale.YLocale;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGUI implements CommandExecutor {
    public CommandGUI(YaLib plugin) {
        plugin.getCommand("ydebug").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            GUIFactory guiFactory = new GUIFactory(YaLib.getInstance());
            guiFactory.create(3, "gui.title")
                    .createItem(Material.BLUE_STAINED_GLASS_PANE)
                    .setName("gui.border")
                    .fillItem(1, 1, 9, 3)
                    .createItem(Material.APPLE)
                    .setName("gui.button.title", commandSender.getName())
                    .setLore("gui.button.lore", commandSender.getName(), commandSender.getName())
                    .shine()
                    .setAction((type) -> {
                        YLocale.send(commandSender,"gui.button.hi");
                        return type.isLeftClick();
                    })
                    .putItem(5, 2)
                    .build()
                    .open(commandSender);
            return true;
        } else return false;
    }
}
