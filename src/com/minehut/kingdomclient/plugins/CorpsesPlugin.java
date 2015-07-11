package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class CorpsesPlugin extends Plugin {

    public CorpsesPlugin() {
        super("Corpses", "Corpses.jar", "Corpses", Material.DROPPER, Arrays.asList("Corpses is a plugin to enable dead bodies", "on your server when you die!"));
    }
}
