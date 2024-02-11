package com.cucuruchi.skyblockutil.FarmProtect;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FarmProtectListener implements Listener {

    @EventHandler
    public void EntityInteractEvent(EntityInteractEvent e){
        Block block = e.getBlock();
        if (block != null && block.getType() == Material.FARMLAND){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e){
        if (e.getAction() == Action.PHYSICAL){
            Block block = e.getClickedBlock();
            if (block.getType() == Material.FARMLAND){
                e.setCancelled(true);
            }
        }
    }
}
