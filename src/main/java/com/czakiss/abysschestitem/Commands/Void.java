package com.czakiss.abysschestitem.Commands;

import com.czakiss.abysschestitem.DataString;
import com.czakiss.abysschestitem.GuiVoid;
import com.czakiss.abysschestitem.Timer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Void implements CommandExecutor {
    FileConfiguration config;

    public Void(Plugin plugin) {
        this.config = plugin.getConfig();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (Timer.getTimeCounterCooldown() <= 0) {
                GuiVoid.openInventory((Player) sender);
            } else {
                sender.sendMessage(DataString.colorized(
                        Timer.getTimeCounterCooldown() > 120 ?
                                config.getString("message_chest_locked_minutes")
                                :
                                config.getString("message_chest_locked_seconds")
                ));
            }
        }
        return true;
    }
}
