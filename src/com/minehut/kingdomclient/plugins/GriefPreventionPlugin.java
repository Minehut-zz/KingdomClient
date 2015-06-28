package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class GriefPreventionPlugin extends Plugin {

    public GriefPreventionPlugin() {
        super("Grief Prevention", "GriefPrevention.jar", "GriefPrevention", Material.BARRIER, Arrays.asList("Protect your server from griefers!"));
    }
}
