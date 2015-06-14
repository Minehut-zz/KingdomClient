package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class EssentialsChatPlugin extends Plugin {

    public EssentialsChatPlugin() {
        super("Essentials Chat", "EssentialsChat.jar", "EssentialsChat", Material.ITEM_FRAME, Arrays.asList("Chat formatting manager."));
    }
}
