package com.nanokylin.mc.yaapi.event.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * 修复在传送的时候也能保持窗口的错误
 */
public class PlayerTeleportListener implements Listener {
    @EventHandler
    public void PlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        player.closeInventory();
    }
}
