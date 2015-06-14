package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class EssentialsSpawnPlugin extends Plugin {

    public EssentialsSpawnPlugin() {
            super("Essentials Spawn", "EssentialsSpawn.jar", "EssentialsSpawn", Material.EGG, Arrays.asList("Define the spawnpoint of your server.", "Note: Requires the Essentials Plugin."));
    }
}
