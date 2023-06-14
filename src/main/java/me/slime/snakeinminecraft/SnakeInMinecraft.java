package me.slime.snakeinminecraft;

import jdk.javadoc.internal.tool.Start;
import me.slime.snakeinminecraft.commands.EndSnakeCommand;
import me.slime.snakeinminecraft.commands.StartSnakeCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public final class SnakeInMinecraft extends JavaPlugin {

    public HashMap<String, List<Block>> snakes = new HashMap<>();
    public HashMap<String, String> snakeDirection = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("SnakeInMinecraft Enabled");
        getCommand("StartSnake").setExecutor(new StartSnakeCommand());
        getCommand("EndSnake").setExecutor(new EndSnakeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("SnakeInMinecraft Disabled");
    }
}
