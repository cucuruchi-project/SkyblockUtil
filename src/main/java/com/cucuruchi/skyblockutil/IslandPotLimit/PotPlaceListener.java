package com.cucuruchi.skyblockutil.IslandPotLimit;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.persistence.PersistentDataContainer;
import com.bgsoftware.superiorskyblock.api.persistence.PersistentDataType;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import net.momirealms.customcrops.api.event.PotBreakEvent;
import net.momirealms.customcrops.api.event.PotPlaceEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

import static com.cucuruchi.skyblockutil.Util.translateColor.transChatColor;

public class PotPlaceListener implements Listener {

    @EventHandler
    public void onPotPlace(PotPlaceEvent e) {
        Player player = e.getPlayer();
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(player);
        Island playerIsland = superiorPlayer.getIsland();
        Island locIsland = SuperiorSkyblockAPI.getIslandAt(e.getLocation());
        PersistentDataContainer dataContainer = locIsland.getPersistentDataContainer();
        String key = "AmountPot";
        String limitkey = "AmountPotLimit";
        if (playerIsland != locIsland && !locIsland.isCoop(superiorPlayer)) {
            e.setCancelled(true);
            return;
        }
        if (dataContainer.get(key, PersistentDataType.INTEGER) < dataContainer.get(limitkey, PersistentDataType.INTEGER)) {
            dataContainer.put(key, PersistentDataType.INTEGER, dataContainer.get(key, PersistentDataType.INTEGER) + 1);
            player.sendMessage(dataContainer.get(key, PersistentDataType.INTEGER).toString());
        } else {
            e.setCancelled(true);
            player.sendMessage(transChatColor("&c&lError | &7커스텀 농경지는 " + dataContainer.get(limitkey, PersistentDataType.INTEGER) + " 개까지만 설치할 수 있습니다."));
        }
    }


    @EventHandler
    public void onPotBreak(PotBreakEvent e) {
        Player player = (Player) e.getEntity();
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(Objects.requireNonNull(player));
        Island playerIsland = superiorPlayer.getIsland();
        Island locIsland = SuperiorSkyblockAPI.getIslandAt(e.getLocation());
        PersistentDataContainer dataContainer = locIsland.getPersistentDataContainer();
        String key = "AmountPot";
        String limitkey = "AmountPotLimit";
        if (playerIsland != locIsland && !locIsland.isCoop(superiorPlayer)) {
            e.setCancelled(true);
            return;
        }
        dataContainer.put(key, PersistentDataType.INTEGER, dataContainer.get(key, PersistentDataType.INTEGER) - 1);
        player.sendMessage(dataContainer.get(key, PersistentDataType.INTEGER).toString());
    }
}
