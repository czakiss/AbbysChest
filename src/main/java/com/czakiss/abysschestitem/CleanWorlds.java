package com.czakiss.abysschestitem;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CleanWorlds {
    private static List<ItemStack> items = new ArrayList<>();
    private static List<String> blacklist = new ArrayList<>();
    private static List<String> worldsList = new ArrayList<>();
    public CleanWorlds(Plugin plugin) {
        this.blacklist = plugin.getConfig().getStringList("blacklist").stream().map(String::toUpperCase).collect(Collectors.toList());
        this.worldsList = plugin.getConfig().getStringList("worldlist");
    }

    public static void clean() {
        List<World> worlds = Bukkit.getWorlds();
        worlds.stream().
                filter(world -> worldsList.contains(world.getName())).
                map(World::getEntities).
                forEach(e -> e.stream().filter(Item.class::isInstance).forEach(item -> {
                    ItemStack itemStack = ((Item) item).getItemStack();
                    if(!blacklist.contains(itemStack.getType().name())){
                        items.add(itemStack);
                    }
                    item.remove();
                }));
    }

    public static List<ItemStack> getItems() {
        return items;
    }

    public static void clearItems() {
        items.clear();
    }
}
