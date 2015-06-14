package com.minehut.kingdomclient.commands;

import com.minehut.commons.common.chat.C;
import com.minehut.commons.common.chat.F;
import com.minehut.commons.common.sound.S;
import com.minehut.core.Core;
import com.minehut.core.command.Command;
import com.minehut.core.player.Rank;
import com.minehut.kingdomclient.KingdomClient;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by luke on 4/11/15.
 */
public class ResetCommand extends Command {

    public ResetCommand(JavaPlugin plugin) {
        super(plugin, "reset", Rank.regular);
    }

    @Override
    public boolean call(Player player, ArrayList<String> args) {

        S.click(player);
        player.sendMessage("");
        F.message(player, "Kingdoms", C.yellow + "This command is only usable in the " + C.green + "/hub");
        player.sendMessage("");

        return true;
    }
}
