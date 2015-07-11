package com.minehut.kingdomclient.plugins;

import com.minehut.core.util.common.chat.C;
import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class LWCPlugin extends Plugin {

    public LWCPlugin() {
        super("LWC Chest Lock", "LWC.jar", "LWC", Material.CHEST, Arrays.asList("Protection for private Chests, Furnaces,", "Doors, Dispensers, and more."));
    }
}
