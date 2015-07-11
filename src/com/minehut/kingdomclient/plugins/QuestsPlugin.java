package com.minehut.kingdomclient.plugins;

import com.minehut.core.util.common.chat.C;
import com.minehut.kingdomclient.Plugin;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by luke on 4/11/15.
 */
public class QuestsPlugin extends Plugin {

    public QuestsPlugin() {
        super("Quests", "Quests.jar", "Citizens", Material.PAPER, Arrays.asList("Quests is an ever-growing, completely open system", "allowing for intricate Quest design and customization."
            , C.red + "Requires Citizens Plugin"));
    }
}
