package com.minehut.kingdomclient.plugins;

import com.minehut.commons.common.items.ItemStackFactory;
import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class TrailsGUIPlugin extends Plugin {

    public TrailsGUIPlugin() {
        super("Trails", "TrailGUI.jar", "TrailGUI", Material.FIREWORK, Arrays.asList("Huge range of custom trails."));
    }
}
