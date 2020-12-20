package com.rabbitown.yalib.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class YaGUIHolder implements InventoryHolder {
    YaGUI yaGUI;
    public YaGUIHolder(YaGUI yaGUI) {
        this.yaGUI = yaGUI;
    }
    @Override
    public Inventory getInventory() {
        return yaGUI.moduleInventory;
    }
}
