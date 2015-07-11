package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class TimPlugin extends Plugin {

    public TimPlugin() {
        super("Tim the Enchanter", "Tim.jar", "Tim", Material.NETHER_STAR, Arrays.asList("A simple plugin which lets you", "enchant your items."));
    }
}
