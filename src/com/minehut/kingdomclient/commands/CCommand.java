package com.minehut.kingdomclient.commands;

import com.minehut.core.util.common.chat.C;
import com.minehut.core.util.common.sound.S;
import com.minehut.core.Core;
import com.minehut.core.command.Command;
import com.minehut.core.player.Rank;
import com.minehut.kingdomclient.KingdomClient;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by luke on 4/11/15.
 */
public class CCommand extends Command {

    public CCommand(JavaPlugin plugin) {
        super(plugin, "c", Rank.regular);
    }

    @Override
    public boolean call(Player player, ArrayList<String> args) {

        if (player.getUniqueId().equals(KingdomClient.getPlugin().getOwnerUUID()) || Core.getInstance().getPlayerInfo(player).getRank().has(null, Rank.Admin, false)) {
            S.playSound(player, Sound.ENDERDRAGON_WINGS);
            KingdomClient.getPlugin().getPluginManager().openMenu(player);
        } else {
            player.sendMessage(C.red + "This command is only available to the Server Owner.");
        }

        return true;
    }
}
