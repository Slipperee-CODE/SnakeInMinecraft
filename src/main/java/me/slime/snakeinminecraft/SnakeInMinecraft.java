package me.slime.snakeinminecraft;

import org.bukkit.plugin.java.JavaPlugin;

public final class SnakeInMinecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("The plugin is probably working");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("The plugin is definitely working");
    }
}
