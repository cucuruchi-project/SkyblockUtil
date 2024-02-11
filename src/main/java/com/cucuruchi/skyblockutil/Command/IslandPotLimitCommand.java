package com.cucuruchi.skyblockutil.Command;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.persistence.PersistentDataContainer;
import com.bgsoftware.superiorskyblock.api.persistence.PersistentDataType;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.cucuruchi.skyblockutil.Util.translateColor.transChatColor;

public final class IslandPotLimitCommand implements CommandExecutor {

    static Player targetPlayer;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = ((Player) sender);
        String key = "AmountPot";
        String limitkey = "AmountPotLimit";
        SuperiorPlayer superiorPlayer;
        PersistentDataContainer dataContainer;
        Island island;
        if (args.length == 0) {
            player.sendMessage(transChatColor("Usage : /섬화분제한 확인"));
            player.sendMessage(transChatColor("Usage : /섬화분제한 제한 [player] [int]"));
            player.sendMessage(transChatColor("Usage : /섬화분제한 갯수 [player] [int]"));
            return false;
        }
        switch (args[0]){
            case "갯수":
                if (args.length < 3) {
                    player.sendMessage(transChatColor("Usage : /섬화분제한 갯수 [player] [int]"));
                    break;
                }
                targetPlayer = Bukkit.getPlayer(args[1]);
                superiorPlayer = SuperiorSkyblockAPI.getPlayer(Objects.requireNonNull(targetPlayer));
                island = superiorPlayer.getIsland();
                dataContainer = island.getPersistentDataContainer();
                dataContainer.put(key, PersistentDataType.INTEGER, Integer.parseInt(args[2]));
                player.sendMessage(transChatColor("Player :" + targetPlayer.getName() + " Island Pot Limit : " + args[2]));
                targetPlayer.sendMessage(transChatColor("&c&lSuccess | &7섬 커스텀 농경지 갯수가 [ " + args[2] + " ]로 변경되었습니다."));
                break;
            case "제한":
                if (args.length < 3) {
                    player.sendMessage(transChatColor("Usage : /섬화분제한 제한 [player] [int]"));
                    break;
                }
                targetPlayer = Bukkit.getPlayer(args[1]);
                superiorPlayer = SuperiorSkyblockAPI.getPlayer(Objects.requireNonNull(targetPlayer));
                island = superiorPlayer.getIsland();
                dataContainer = island.getPersistentDataContainer();
                dataContainer.put(limitkey, PersistentDataType.INTEGER, Integer.parseInt(args[2]));
                player.sendMessage(transChatColor("Player :" + targetPlayer.getName() + " Island Pot Limit : " + args[2]));
                targetPlayer.sendMessage(transChatColor("&c&lSuccess | &7섬 커스텀 농경지의 제한이 [ " + args[2] + " ]로 변경되었습니다."));
                break;
            case "확인":
                if (args.length < 2) {
                    player.sendMessage(transChatColor("Usage : /섬화분제한 확인 [player]"));
                    break;
                }
                targetPlayer = Bukkit.getPlayer(args[1]);
                superiorPlayer = SuperiorSkyblockAPI.getPlayer(targetPlayer);
                island = superiorPlayer.getIsland();
                dataContainer = island.getPersistentDataContainer();
                player.sendMessage(transChatColor("&a[POT_LIMIT] &f" + dataContainer.get(key, PersistentDataType.INTEGER) + " / " + dataContainer.get(limitkey, PersistentDataType.INTEGER)));
                break;
        }
        return false;
    }
}
