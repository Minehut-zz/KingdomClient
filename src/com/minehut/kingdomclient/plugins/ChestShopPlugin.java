package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class ChestShopPlugin extends Plugin {

    public ChestShopPlugin() {
        super("ChestShop", "ChestShop.jar", "ChestShop", Material.CHEST, Arrays.asList("Create automated chest-shops."));
    }
}
