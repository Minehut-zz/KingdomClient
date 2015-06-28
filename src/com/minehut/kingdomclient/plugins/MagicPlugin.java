package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class MagicPlugin extends Plugin {

    public MagicPlugin() {
        super("Magic", "Magic.jar", "Magic", Material.BLAZE_ROD, Arrays.asList("Magic is a plugin that adds powerful magic wands and items to your server."));
    }
}
