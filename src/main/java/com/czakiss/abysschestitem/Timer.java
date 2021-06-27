package com.czakiss.abysschestitem;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import java.util.Objects;


public class Timer {

    private static BukkitTask runnable;
    private static Plugin plugin;
    private static FileConfiguration config;

    private final long timeCooldown;
    private final long timeChestActive;

    private static long timeCounterCooldown;
    private static long timeCounterChestActive;

    private static final long TICKS = 20;
    private static final long MINUTE = 60;



    public Timer(Plugin plugin) {
        Timer.plugin = plugin;
        this.config = plugin.getConfig();
        this.timeCooldown = Timer.plugin.getConfig().getInt("time_cooldown");
        this.timeChestActive = Timer.plugin.getConfig().getInt("time_active_chest");
    }

    public void start() {

        timeCounterCooldown = timeCooldown;
        timeCounterChestActive = timeChestActive;

        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (timeCounterCooldown <= 0) {
                    if (timeCounterChestActive == timeChestActive) {
                        CleanWorlds.clean();
                        GuiVoid.initializeItems(CleanWorlds.getItems());
                        CleanWorlds.clearItems();
                        Bukkit.broadcastMessage(DataString.colorized(
                                Objects.requireNonNull(config.getString("message_clear"))
                        ));
                    }

                    timeCounterChestActive--;

                    if (timeCounterChestActive <= 0) {
                        GuiVoid.clearInventory();
                        timeCounterCooldown = timeCooldown;
                        timeCounterChestActive = timeChestActive;
                        Bukkit.broadcastMessage(DataString.colorized(
                                Objects.requireNonNull(config.getString("message_chest_locked"))
                        ));

                    }
                } else {
                    timeCounterCooldown--;
                }
                if ( timeCounterCooldown == 60 || timeCounterCooldown == 15 ) {
                    Bukkit.broadcastMessage(DataString.colorized(
                            Objects.requireNonNull(config.getString("message_seconds"))
                    ));
                }
            }
        }.runTaskTimerAsynchronously(plugin, TICKS, TICKS);
    }

    public void stop() {
        runnable.cancel();
    }

    public static long getTimeCounterCooldown() {
        return timeCounterCooldown;
    }

    public static long timeCounterChestActive() {
        return timeCounterChestActive;
    }
}
