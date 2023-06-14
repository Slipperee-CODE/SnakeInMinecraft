package me.slime.snakeinminecraft.commands;

import me.slime.snakeinminecraft.SnakeInMinecraft;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

public class StartSnakeCommand implements CommandExecutor {

    SnakeInMinecraft snakeInMinecraft = new SnakeInMinecraft();
    HashMap<String, List<Block>> snakes = snakeInMinecraft.snakes;
    HashMap<String, String> snakeDirection = snakeInMinecraft.snakeDirection;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length != 3) {
                p.sendMessage(ChatColor.RED + "Wrong amount of arguments");
            }
            else {
                int sizeX = Integer.parseInt(args[0]);
                int sizeZ = Integer.parseInt(args[1]);
                int playerHeight = Integer.parseInt(args[2]);
                StartSnake(p, sizeX, sizeZ, playerHeight);
            }
        }
        return true;
    }

    public void StartSnake(Player player, int sizeX, int sizeZ, int playerHeight)
    {
        String sizeXString = Integer.toString(sizeX);
        String sizeZString = Integer.toString(sizeZ);
        Location playerLocation = player.getLocation();
        Location newPlayerLocation = new Location(playerLocation.getWorld(), playerLocation.getX()+(sizeX/2), playerLocation.getY()+playerHeight,playerLocation.getZ()+(sizeZ/2));

        player.sendMessage(ChatColor.RED + "Length: " + sizeXString);
        player.sendMessage(ChatColor.RED + "Width: " + sizeZString);
        player.sendMessage(ChatColor.RED + "PlayerHeight: " + playerHeight);

        BuildPlatforms(playerLocation, sizeX, sizeZ, playerHeight);

        player.teleport(newPlayerLocation);

        //Initialize the player's snake + direction
        InitPlayerData(player);
        //Start Movement of Snake | Schedule a bukkit repeating thing
        //Start Detecting Direction to Move | Schedule a bukkit repeating thing + make a listener for right clicking items
    }

    public void BuildPlatforms(Location playerLocation, int sizeX, int sizeZ, int playerHeight)
    {
        Material backgroundMaterial = Material.WHITE_CONCRETE;
        Material edgeMaterial = Material.BLACK_CONCRETE;
        Material platformMaterial = Material.GLASS;

        double x = playerLocation.getX();
        double y = playerLocation.getY();
        double z = playerLocation.getZ();

        for (double i = x; i < sizeX+x; i++){ //Main Background
            for (double j = z; j < sizeZ+z; j++){
                Location location = new Location(playerLocation.getWorld(),i,y,j);
                location.getBlock().setType(backgroundMaterial);
            }
        }


        for (double i = x-1; i < sizeX+x+1; i++){ //Bottom row
            Location location = new Location(playerLocation.getWorld(),i,y+1,z-1);
            location.getBlock().setType(edgeMaterial);
        }

        for (double i = x-1; i < sizeX+x+1; i++){ //Top row
            Location location = new Location(playerLocation.getWorld(),i,y+1,sizeZ+z);
            location.getBlock().setType(edgeMaterial);
        }

        for (double i = z; i < sizeZ+z; i++){ //Left Column
            Location location = new Location(playerLocation.getWorld(),x-1,y+1,i);
            location.getBlock().setType(edgeMaterial);
        }

        for (double i = z; i < sizeZ+z; i++){ //Right Column
            Location location = new Location(playerLocation.getWorld(),sizeX+x,y+1,i);
            location.getBlock().setType(edgeMaterial);
        }


        if (sizeX % 2 == 0 && sizeZ % 2 == 0){ //Player Platform for even and odd sizes
            for (double i = x+(sizeX/2); i > x+(sizeX/2)-2; i--){
                for (double j = z+(sizeZ/2); j > z+(sizeZ/2)-2; j--){
                    Location evenLocation = new Location(playerLocation.getWorld(), i, y+playerHeight-1,j);
                    evenLocation.getBlock().setType(platformMaterial);
                }
            }
        }
        else if (sizeX % 2 == 0){
            Location evenLocation = new Location(playerLocation.getWorld(), x+(sizeX/2)-1, y+playerHeight-1,z+(sizeZ/2));
            evenLocation.getBlock().setType(platformMaterial);
        }
        else if (sizeZ % 2 == 0){
            Location evenLocation = new Location(playerLocation.getWorld(), x+(sizeX/2), y+playerHeight-1,z+(sizeZ/2)-1);
            evenLocation.getBlock().setType(platformMaterial);
        }

        Location location = new Location(playerLocation.getWorld(), x+(sizeX/2), y+playerHeight-1,z+(sizeZ/2));
        location.getBlock().setType(platformMaterial);
    }

    public void InitPlayerData(Player player)
    {
        String username = player.getName();
        snakes.put(username, new ArrayList<Block>() );
        snakeDirection.put(username, "up");
    }
}
