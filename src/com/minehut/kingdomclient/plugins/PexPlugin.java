package com.minehut.kingdomclient.plugins;

import com.minehut.core.util.common.chat.C;
import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class PexPlugin extends Plugin {

    public PexPlugin() {
        super("Permissions Ex", "PermissionsEx.jar", "PermissionsEx", Material.DAYLIGHT_DETECTOR, Arrays.asList("Great various permission groups.", C.aqua + C.bold + "NOTE: " + C.aqua +  "Prefixes require Essentials and Essentials Chat.", C.red + C.bold + "WARNING: " + C.red + "Do not install this with Group Manager Plugin"));
    }
}
