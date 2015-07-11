package com.minehut.kingdomclient.plugins;

import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class ChatFilterPlugin extends Plugin {

    public ChatFilterPlugin() {
        super("Chat Filter", "ChatFilter.jar", "ChatFilter", Material.BRICK, Arrays.asList("Prevent players from using bad language.", "Made by DBAwesome01!"));
    }
}
