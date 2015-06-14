package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class CrackShotPlugin extends Plugin {

    public CrackShotPlugin() {
        super("CrackShot", "CrackShot.jar", "CrackShot", Material.IRON_BARDING, Arrays.asList("Guns in Minecraft!"));
    }
}
