package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class PrisonMinesPlugin extends Plugin {

    public PrisonMinesPlugin() {
        super("Prison Mines", "PrisonMines.jar", "PrisonMines", Material.IRON_BARDING, Arrays.asList("PrisonMines is the most professional way to manage your mines."));
    }
}
