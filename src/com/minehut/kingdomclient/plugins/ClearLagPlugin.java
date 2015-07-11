package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class ClearLagPlugin extends Plugin {

    public ClearLagPlugin() {
        super("Clear Lag", "ClearLag.jar", "ClearLag", Material.DIRT, Arrays.asList("Clearlag was designed to reduce lag", "on Bukkit/Spigot servers by removing entities", "and preventing it through it's many", "optional features."));
    }
}
