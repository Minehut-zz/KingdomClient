package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class WorldEditPlugin extends Plugin {

    public WorldEditPlugin() {
        super("World Edit", "WorldEdit.jar", "WorldEdit", Material.WOOD_AXE, Arrays.asList("Core set of building commands."));
    }
}
