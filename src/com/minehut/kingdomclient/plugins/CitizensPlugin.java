package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class CitizensPlugin extends Plugin {

    public CitizensPlugin() {
        super("Citizens", "Citizens.jar", "Citizens", Material.SKULL_ITEM, Arrays.asList("Citizens contains a variety of", "toggleable characters and unlimited possibilities."));
    }
}
