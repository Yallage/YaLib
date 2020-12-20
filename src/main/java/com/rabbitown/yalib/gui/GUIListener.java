package com.rabbitown.yalib.gui;

import com.rabbitown.yalib.YaLib;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {
    public GUIListener(YaLib plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof YaGUIHolder) {
            event.setCancelled(true);
            int slot = event.getSlot();
            YaGUI yaGUI = ((YaGUIHolder) event.getClickedInventory().getHolder()).yaGUI;
            if (yaGUI.runList[slot] != null) {
                if (yaGUI.runList[slot].apply(event.getClick())) {
                    event.getWhoClicked().closeInventory();
                }
            }
        }
    }
}
