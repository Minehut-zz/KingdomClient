package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class McMMOPlugin extends Plugin {

    public McMMOPlugin() {
        super("McMMO", "mcMMO.jar", "mcMMO", Material.BLAZE_POWDER, Arrays.asList("Adds an extensive amount RPG elements to Minecraft."));
    }
}
