package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class LuckyBlocksPlugin extends Plugin {

    public LuckyBlocksPlugin() {
        super("Lucky Block", "LuckyBlock.jar", "LuckyBlock", Material.SPONGE, Arrays.asList("Spawn in Lucky Blocks!."));
    }
}
