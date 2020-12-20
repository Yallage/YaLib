package com.rabbitown.yalib.gui;


import com.rabbitown.yalib.locale.I18NPlugin;
import com.rabbitown.yalib.locale.Locale;
import com.rabbitown.yalib.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

/**
 * This class will create an GUI factory to create an GUI inventory.
 *
 * @author APJifengc
 */
public class GUIFactory {
    private Inventory inventory;
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private int line;
    private YaGUI yaGUI;
    private final YaGUIHolder yaGUIHolder;
    private final Locale locale;
    private String name;
    private String[] nameArguments;
    private String lore;
    private String[] loreArguments;
    private Function<ClickType, Boolean> function;


    public GUIFactory(I18NPlugin plugin) {
        this.yaGUIHolder = new YaGUIHolder(null);
        this.locale = plugin.getLocale();
    }

    /**
     * Create a new GUI.
     *
     * @param line  Line count of the GUI.
     * @param title The title of the GUI.
     *              (Use {@link Locale Locale} string)
     * @param args  {@link Locale Locale} arguments.
     */
    public GUIFactory create(int line, String title, String... args) {
        this.yaGUI = new YaGUI(line, title, args);
        inventory = Bukkit.createInventory(yaGUIHolder,9*line, title);
        this.line = line;
        return this;
    }

    /**
     * Create a new item.
     *
     * @param material The item material.
     */
    public GUIFactory createItem(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
        name = null;
        nameArguments = null;
        lore = null;
        loreArguments = null;
        function = null;
        return this;
    }

    /**
     * Set the item's name.
     *
     * @param name The name of the item.
     *             (Use {@link Locale Locale} string)
     * @param args {@link Locale Locale} arguments.
     * @throws NullPointerException Throws when the item is not created.
     */
    public GUIFactory setName(String name, String... args) throws NullPointerException {
        if (itemStack == null) {
            throw new NullPointerException("Item is not created!");
        }
        itemMeta.setDisplayName(name);
        this.name = name;
        this.nameArguments = args;
        return this;
    }

    /**
     * Set the item's lore.
     *
     * @param lore The lore of the item.
     *             (Use {@link Locale Locale} string, the raw string splits by "\n")
     * @param args {@link Locale Locale} arguments.
     * @throws NullPointerException Throws when the item is not created.
     */
    public GUIFactory setLore(String lore, String... args) throws NullPointerException {
        if (itemStack == null) {
            throw new NullPointerException("Item is not created!");
        }
        itemMeta.setLore(Collections.singletonList(lore));
        this.lore = lore;
        this.loreArguments = args;
        return this;
    }

    /**
     * Make the item shine. (Add an enchantment)
     *
     * @throws NullPointerException Throws when the item is not created.
     */
    public GUIFactory shine() throws NullPointerException {
        if (itemStack == null) {
            throw new NullPointerException("Item is not created!");
        }
        itemMeta.addEnchant(Enchantment.DIG_SPEED,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    /**
     * Set the skull's owner.
     *
     * @param player The owner of the skull.
     * @throws NullPointerException Throws when the item is not created or the type is not skull.
     */
    public GUIFactory setOwner(OfflinePlayer player) throws NullPointerException {
        if (itemStack == null) {
            throw new NullPointerException("Item is not created!");
        }
        if (itemStack.getType() != Material.PLAYER_HEAD) {
            throw new NullPointerException("Item type is not player head!");
        }
        SkullMeta skullMeta = (SkullMeta) itemMeta;
        skullMeta.setOwningPlayer(player);
        itemMeta = skullMeta;
        return this;
    }

    /**
     * Set the item's click action.
     *
     * @param function The function to execute when the item is clicked.
     *                 There is a argument for {@link ClickType ClickType}.
     *                 And return {@code true} if you want to close the GUI after the function is executed.
     * @throws NullPointerException Throws when the item is not created.
     */
    public GUIFactory setAction(Function<ClickType, Boolean> function) {
        if (itemStack == null) {
            throw new NullPointerException("Item is not created!");
        }
        this.function = function;
        return this;
    }

    /**
     * Put the item in the GUI.
     *
     * @param x The row count.(Starts from 1, from left to right)
     * @param y The column count.(Starts from 1, from up to down)
     * @throws NullPointerException Throws when the item is not created.
     */
    public GUIFactory putItem(int x, int y) throws NullPointerException {
        if (itemStack == null || inventory == null) {
            throw new NullPointerException("Item or inventory is not created!");
        }
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(Util.getSlot(x,y),itemStack);
        if (name != null) {
            yaGUI.nameList[Util.getSlot(x,y)] = name;
            yaGUI.nameArguments[Util.getSlot(x,y)] = nameArguments;
        }
        if (lore != null) {
            yaGUI.loreList[Util.getSlot(x,y)] = lore;
            yaGUI.loreArguments[Util.getSlot(x,y)] = loreArguments;
        }
        if (function != null) {
            yaGUI.runList[Util.getSlot(x,y)] = function;
        }
        return this;
    }

    /**
     * Fill a row with the item.
     *
     * @param row The row count.(Starts from 1, from left to right)
     * @throws NullPointerException Throws when the item is not created.
     */
    public GUIFactory fillRowItem(int row) throws NullPointerException {
        if (itemStack == null || inventory == null) {
            throw new NullPointerException("Item or inventory is not created!");
        }
        itemStack.setItemMeta(itemMeta);
        for (int i=1; i<=9; i++) {
            inventory.setItem(Util.getSlot(i,row),itemStack);
            if (name != null) {
                yaGUI.nameList[Util.getSlot(i,row)] = name;
                yaGUI.nameArguments[Util.getSlot(i,row)] = nameArguments;
            }
            if (lore != null) {
                yaGUI.loreList[Util.getSlot(i,row)] = lore;
                yaGUI.loreArguments[Util.getSlot(i,row)] = loreArguments;
            }
            if (function != null) {
                yaGUI.runList[Util.getSlot(i,row)] = function;
            }
        }
        return this;
    }

    /**
     * Fill a column with the item.
     *
     * @param column The column count.(Starts from 1, from up to down)
     * @throws NullPointerException Throws when the item is not created.
     */
    public GUIFactory fillColumnItem(int column) throws NullPointerException {
        if (itemStack == null || inventory == null) {
            throw new NullPointerException("Item or inventory is not created!");
        }
        itemStack.setItemMeta(itemMeta);
        for (int i=1; i<=line; i++) {
            inventory.setItem(Util.getSlot(column,i),itemStack);
            if (name != null) {
                yaGUI.nameList[Util.getSlot(column,i)] = name;
                yaGUI.nameArguments[Util.getSlot(column,i)] = nameArguments;
            }
            if (lore != null) {
                yaGUI.loreList[Util.getSlot(column,i)] = lore;
                yaGUI.loreArguments[Util.getSlot(column,i)] = loreArguments;
            }
            if (function != null) {
                yaGUI.runList[Util.getSlot(column,i)] = function;
            }
        }
        return this;
    }

    /**
     * Fill a part with the item.(<code>x1<x2,y1<y2</code>)
     *
     * @param x1 The start row.
     * @param y1 The start column.
     * @param x2 The end row.
     * @param y2 The end column.
     * @throws NullPointerException Throws when the item is not created.
     */
    public GUIFactory fillItem(int x1, int y1, int x2, int y2) throws NullPointerException {
        if (itemStack == null || inventory == null) {
            throw new NullPointerException("Item or inventory is not created!");
        }
        itemStack.setItemMeta(itemMeta);
        for (int i=x1; i<=x2; i++) {
            for (int j=y1; j<=y2; j++) {
                inventory.setItem(Util.getSlot(i,j),itemStack);
                if (name != null) {
                    yaGUI.nameList[Util.getSlot(i,j)] = name;
                    yaGUI.nameArguments[Util.getSlot(i,j)] = nameArguments;
                }
                if (lore != null) {
                    yaGUI.loreList[Util.getSlot(i,j)] = lore;
                    yaGUI.loreArguments[Util.getSlot(i,j)] = loreArguments;
                }
                if (function != null) {
                    yaGUI.runList[Util.getSlot(i,j)] = function;
                }
            }
        }
        return this;
    }

    /**
     * Build the GUI.
     *
     * @return The GUI inventory.
     * @throws NullPointerException Throws when the inventory is not created.
     */
    public YaGUI build() throws NullPointerException {
        if (inventory == null) {
            throw new NullPointerException("Inventory is not created!");
        }
        yaGUI.moduleInventory = inventory;
        return yaGUI;
    }
}
