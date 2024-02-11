package com.cucuruchi.skyblockutil;

import com.cucuruchi.skyblockutil.Command.IslandPotLimitCommand;
import com.cucuruchi.skyblockutil.FarmProtect.FarmProtectListener;
import com.cucuruchi.skyblockutil.IslandPotLimit.DataRegisterListener;
import com.cucuruchi.skyblockutil.IslandPotLimit.DisbandListener;
import com.cucuruchi.skyblockutil.IslandPotLimit.PotPlaceListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class skyblockutil extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PotPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new DataRegisterListener(), this);
        getServer().getPluginManager().registerEvents(new DisbandListener(), this);
        getServer().getPluginManager().registerEvents(new FarmProtectListener(), this);

        getCommand("농경지제한").setExecutor(new IslandPotLimitCommand());
    }

    @Override
    public void onDisable() {
    }
}
