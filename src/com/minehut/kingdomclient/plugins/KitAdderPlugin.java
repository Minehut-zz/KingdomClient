package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class KitAdderPlugin extends Plugin {

    public KitAdderPlugin() {
        super("Kit Adder", "KitAdder.jar", "KitAdder", Material.STONE_SWORD, Arrays.asList("Create kits with in-game commands.", "Note: Requires the Essentials Plugin."));
    }
}
