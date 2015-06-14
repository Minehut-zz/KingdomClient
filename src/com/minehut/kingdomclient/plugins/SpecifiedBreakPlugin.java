package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class SpecifiedBreakPlugin extends Plugin {

    public SpecifiedBreakPlugin() {
        super("Specified Break", "SpecifiedBreak.jar", "SpecifiedBreak", Material.WOOD, Arrays.asList("Deny the breaking of a certain block with a certain item."));
    }
}
