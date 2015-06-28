package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class TogglePvpPlugin extends Plugin {

    public TogglePvpPlugin() {
        super("Toggle PvP", "TogglePVP.jar", "TogglePVP", Material.GOLD_SWORD, Arrays.asList("Toggle pvp with /pvp on and /pvp off."));
    }
}
