package com.czakiss.abysschestitem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuiVoid implements Listener {
    private static FileConfiguration config;
    private static List<HumanEntity> humanEntities = new ArrayList<>();
    private static final int SIZE = 54;
    private static Inventory inv;
    private static String chestName;
    private static Plugin plugin;

    public GuiVoid(Plugin plugin){
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.chestName =DataString.colorized(Objects.requireNonNull(config.getString("chest_title")));
        inv = Bukkit.createInventory(null, SIZE, chestName);
    }

    public static void initializeItems(List<ItemStack> items) {
        for (int i = 0; i < SIZE && i < items.size() ; i++){
            inv.addItem(items.get(i));
        }
    }

    public static void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
        humanEntities.add(ent);
    }

    public static void clearInventory() {
        humanEntities.stream().forEach(human -> {
            if(human.getOpenInventory().getTitle().equals(chestName)){
                Bukkit.getScheduler().runTask(plugin, () -> human.closeInventory());
            }
        });
        inv.clear();
    }

}
