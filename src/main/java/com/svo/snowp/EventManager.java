package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EventManager {

    private final Plugin plugin;
    private final int EVENT_COOLDOWN = 6000; // Кулдаун в тиках (5 минут)
    private final int EVENT_DURATION = 6000; // Длительность ивента в тиках (5 минут)

    public EventManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void startEvent() {
        Bukkit.broadcastMessage("Ивент начался!");

        // Логика ивента
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("Ивент завершился!");
                scheduleNextEvent();  // Планируем запуск следующего ивента после кулдауна
            }
        }.runTaskLater(plugin, EVENT_DURATION);
    }

    private void scheduleNextEvent() {
        new BukkitRunnable() {
            @Override
            public void run() {
                startEvent();  // Запускаем следующий ивент после кулдауна
            }
        }.runTaskLater(plugin, EVENT_COOLDOWN);
    }
}
