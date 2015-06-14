package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class WorldGuardPlugin extends Plugin {

    public WorldGuardPlugin() {
        super("World Guard", "WorldGuard.jar", "WorldGuard", Material.REDSTONE_TORCH_ON, Arrays.asList("Region protection.", "Note: Requires World Edit"));
    }
}
