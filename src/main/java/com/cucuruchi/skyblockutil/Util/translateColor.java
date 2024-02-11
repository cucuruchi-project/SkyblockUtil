package com.cucuruchi.skyblockutil.Util;

import org.bukkit.ChatColor;

public class translateColor {
    public static String transChatColor(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
