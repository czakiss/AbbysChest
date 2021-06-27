package com.czakiss.abysschestitem;

import com.czakiss.abysschestitem.Commands.Void;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class AbyssChestItem extends JavaPlugin {


    FileConfiguration config;
    Timer timer;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = this.getConfig();
        new GuiVoid(this);
        new CleanWorlds(this);

        this.getCommand("void").setExecutor(new Void(this));

        timer = new Timer(this);


        timer.start();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
