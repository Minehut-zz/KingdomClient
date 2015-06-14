package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class EssentialsPlugin extends Plugin {

    public EssentialsPlugin() {
        super("Essentials", "Essentials.jar", "Essentials", Material.PAPER, Arrays.asList("Core set of commands and features."));
    }
}
