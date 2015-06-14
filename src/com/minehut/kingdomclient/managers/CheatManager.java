package com.minehut.kingdomclient.managers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.minehut.kingdomclient.KingdomClient;

public class CheatManager implements Listener {

	public CheatManager(KingdomClient plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onSignChange(SignChangeEvent event) {
		boolean safe = true;
		for (String line : event.getLines()) {
			if (line.length()>200) {
				event.setCancelled(true);
				safe=false;
				break;
			}
		}
		if (!safe) {
			event.setLine(0, "");
			event.setLine(1, "");
			event.setLine(2, "");
			event.setLine(3, "");
			System.out.println("!!Sign exploit from " + event.getPlayer().getName());
		}
	}
	
}
