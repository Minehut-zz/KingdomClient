package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class CombatTagPlugin extends Plugin {

    public CombatTagPlugin() {
        super("Combat Tag", "CombatTag.jar", "CombatTag", Material.NAME_TAG, Arrays.asList("The essential PvP plugin that prevents hit-n-quit players."));
    }
}
