package com.minehut.kingdomclient.maps;

import com.minehut.core.player.Rank;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 5/15/15.
 */
public class DotaMap extends Map {

    public DotaMap() {
        super("Dota", "Dota", Material.GOLD_SWORD, Arrays.asList("Dota in Mineraft!", "Made by Hypixel"), Rank.regular, false);
    }
}
