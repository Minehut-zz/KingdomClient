package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class StatusPlugin extends Plugin {

    public StatusPlugin() {
        super("Status", "Status.jar", "Status", Material.BAKED_POTATO, Arrays.asList("Adds a server to the featured list."));
    }
}
