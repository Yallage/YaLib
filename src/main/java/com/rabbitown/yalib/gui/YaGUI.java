package com.rabbitown.yalib.gui;

import com.rabbitown.yalib.locale.YLocale;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntPredicate;

/**
 * A GUI made by GUIFactory.
 *
 * @author APJifengc
 */
public class YaGUI {
    public String[] nameList;
    public String[][] nameArguments;
    public String[] loreList;
    public String[][] loreArguments;
    public String title;
    public String[] titleArguments;
    public Function<ClickType, Boolean>[] runList;
    public int line;
    public Inventory moduleInventory;

    public YaGUI(int line, String title, String... args) {
        this.line = line;
        this.title = title;
        this.titleArguments = args;
        this.nameList = new String[line*9];
        this.nameArguments = new String[line*9][];
        this.loreList = new String[line*9];
        this.loreArguments = new String[line*9][];
        this.runList = new Function[line*9];
    }

    /**
     * Get player's GUI inventory.
     *
     * @param player The player who opens it.
     * @return The inventory.
     */
    public Inventory getInventory(Player player) {
        String formatTitle = YLocale.getMessage(player, title, titleArguments);
        Inventory inventory = Bukkit.createInventory(new YaGUIHolder(this),line*9, formatTitle==null?title:formatTitle);
        ItemStack[] content = moduleInventory.getContents();
        for (int i = 0; i < content.length; i++) {
            ItemMeta itemMeta = content[i].getItemMeta();
            if (nameList[i] != null) {
                String formatName = YLocale.getMessage(player, nameList[i], nameArguments[i]);
                itemMeta.setDisplayName(formatName==null?nameList[i]:formatName);
            }
            if (loreList[i] != null) {
                String formatLore = YLocale.getMessage(player, loreList[i], loreArguments[i]);
                itemMeta.setLore(Arrays.asList((formatLore==null?loreList[i]:formatLore).split("\n")));
            }
            content[i].setItemMeta(itemMeta);
        }
        inventory.setContents(content);
        return inventory;
    }

    /**
     * Open the GUI for a player.
     *
     * @param player The player.
     */
    public void open(CommandSender player) {
        ((Player) player).openInventory(getInventory((Player) player));
    }

    @Override
    public String toString() {
        return "YaGUI{" +
                "title='" + title + '\'' +
                ", line=" + line +
                '}';
    }
}
