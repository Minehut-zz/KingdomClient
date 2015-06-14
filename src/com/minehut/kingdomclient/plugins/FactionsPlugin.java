package com.minehut.kingdomclient.plugins;

import com.minehut.core.player.Rank;
import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class FactionsPlugin extends Plugin {

    public FactionsPlugin() {
        super("Factions", "Factions.jar", "Factions", Material.TNT, Arrays.asList("Claim land and wage war!"), Rank.Super);
    }
}
