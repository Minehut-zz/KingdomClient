package com.minehut.kingdomclient.maps;

import com.minehut.core.util.common.chat.C;
import com.minehut.core.player.Rank;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 5/15/15.
 */
public class DefaultMap extends Map {

    public DefaultMap() {
        super("world", C.green + C.bold + "Your Default World", Material.GRASS, Arrays.asList("The world that originally came with your kingdom."), Rank.regular, true);
    }
}
