package me.slime.snakeinminecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EndSnakeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length != 0) {
                p.sendMessage(ChatColor.RED + "Wrong amount of arguments");
            }
            else {
                //Any ending code can go here
            }
        }
        return true;
    }
}
