package com.cucuruchi.skyblockutil.IslandPotLimit;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.persistence.PersistentDataContainer;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DisbandListener implements Listener {

    @EventHandler
    public void resetPotAmount(IslandDisbandEvent e) {
        SuperiorPlayer player = SuperiorSkyblockAPI.getPlayer(e.getPlayer().asPlayer());
        Island island = player.getIsland();
        PersistentDataContainer dataContainer = island.getPersistentDataContainer();
        String key = "AmountPot";
        String limitKey = "AmountPotLimit";
        player.asPlayer().sendMessage("초기화완료");
        dataContainer.remove(key);
        dataContainer.remove(limitKey);
    }
}
