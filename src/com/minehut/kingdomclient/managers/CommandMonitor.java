package com.minehut.kingdomclient.managers;

import java.util.ArrayList;

import com.minehut.kingdomclient.KingdomClient;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by luke on 4/11/15.
 */
public class CommandMonitor implements Listener {

	private ArrayList<String> disabledCommands = new ArrayList<String>(), punishCommands = new ArrayList<String>();
	
    public CommandMonitor(KingdomClient plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler (priority = EventPriority.LOWEST)
    public void onPreCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String ownerName = KingdomClient.getPlugin().getOwnerName();
        String ownerUUID = KingdomClient.getPlugin().getOwnerUUID().toString();

        String message = event.getMessage();
        
        if (this.containsDisabledCommand(message.toLowerCase())) {
        	event.setCancelled(true);
        	player.sendMessage("That command is disabled.");
        	return;
        }

        /* Should check for all commands at once */
        else if (this.containsPunishCommand(message.toLowerCase()) && event.getMessage().toLowerCase().contains(ownerName.toLowerCase())) {
            event.setCancelled(true);
            player.sendMessage("You cannot punish the Kingdom Owner.");
        }
        else if (this.containsPunishCommand(message.toLowerCase()) && event.getMessage().toLowerCase().contains(ownerUUID.toLowerCase())) {
            event.setCancelled(true);
            player.sendMessage("You cannot punish the Kingdom Owner.");
        }
    }
    
    public void addDisabledCommand(String command) {
    	this.disabledCommands.add(command);
    }
    
    public ArrayList<String> getDisabledCommands() {
    	return this.disabledCommands;
    }
    
    public boolean containsDisabledCommand(String message) {
    	for (String command : this.getDisabledCommands()) {
    		if (message.contains(command.toLowerCase())) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public void addPunishCommand(String command) {
    	this.punishCommands.add(command);
    }
    
    public ArrayList<String> getPunishCommands() {
    	return this.punishCommands;
    }
    
    public boolean containsPunishCommand(String message) {
    	for (String command : this.getPunishCommands()) {
    		if (message.contains(command.toLowerCase())) {
    			return true;
    		}
    	}
    	return false;
    }
    
    {
		/* Ways to screw with Kingdom Owner */
    	addPunishCommand("/ban");
    	addPunishCommand("/minecraft:ban");
		addPunishCommand("/eban");
    	addPunishCommand("/kick");
		addPunishCommand("/ekick");
    	addPunishCommand("/minecraft:kick");
    	addPunishCommand("/deop");
    	addPunishCommand("/minecraft:deop");
    	addPunishCommand("/whitelist remove");
    	addPunishCommand("/minecraft:whitelist remove");
		addPunishCommand("/mute");
		addPunishCommand("/emute");
    }
    
    { //TODO: Load from file/config/mongo/something?
    	/* Reloading */
    	addDisabledCommand("/reload");
    	addDisabledCommand("/rl");
    	/* Used for bypassing commands */
    	addDisabledCommand("/bukkit:");
    	/* Host Initiation Commands */
    	addDisabledCommand("/set_owner");
    	addDisabledCommand("/set_id");
    	/* IP grabbing command */
    	addDisabledCommand("/whois");
		addDisabledCommand("/essentials:whois");
		addDisabledCommand("/ewhois");
		addDisabledCommand("seen");
		addDisabledCommand("/eseen");
		addDisabledCommand("/essentials:seen");
		addDisabledCommand("/banip");
		addDisabledCommand("/ebanip");
		addDisabledCommand("/essentials:banip");
		addDisabledCommand("/ipban");
		addDisabledCommand("/ip");

		/* Crashes People */
		addDisabledCommand("/setblock ~ ~ ~ minecraft:standing_sign 0 replace {Text1:\"{translate:/\"translation.text.invalid/\"}\"}");
    }
}
