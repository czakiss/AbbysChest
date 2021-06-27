package com.czakiss.abysschestitem;

import org.bukkit.ChatColor;

public class DataString {
    private static final String TIME_COOLDOWN_MINUTES = "[MINUTES]";
    private static final String TIME_COOLDOWN_SECONDS = "[SECONDS]";
    private static final String TIME_CHEST = "[TIMECHEST]";
    private static final long MINUTE = 60;

    public static String colorized(String text) {


        long cooldown = Timer.getTimeCounterCooldown();
        long chectCooldown = Timer.getTimeCounterCooldown();
        String out = text;
        out = out.replace(TIME_COOLDOWN_MINUTES, "" + Math.round(cooldown / MINUTE));
        out = out.replace(TIME_COOLDOWN_SECONDS, "" + cooldown);
        out = out.replace(TIME_CHEST, "" + chectCooldown);

        return ChatColor.translateAlternateColorCodes('&', out);
    }
}
