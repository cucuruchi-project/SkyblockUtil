package com.cucuruchi.skyblockutil.IslandPotLimit;

import com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.persistence.PersistentDataContainer;
import com.bgsoftware.superiorskyblock.api.persistence.PersistentDataType;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DataRegisterListener implements Listener {

    @EventHandler
    public void IslandCreateEvent(IslandCreateEvent e) {
        SuperiorPlayer superiorPlayer = e.getPlayer();
        Island island = superiorPlayer.getIsland();
        PersistentDataContainer dataContainer = island.getPersistentDataContainer();
        String key = "AmountPot";
        String limitkey = "AmountPotLimit";
        if (dataContainer.get(key) == null) {
            dataContainer.put(key, PersistentDataType.INTEGER, 0);
        }
        if (dataContainer.get(limitkey) == null) {
            dataContainer.put(limitkey, PersistentDataType.INTEGER, 64);
        }
    }
}
