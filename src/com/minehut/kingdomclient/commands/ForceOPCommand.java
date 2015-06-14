package com.minehut.kingdomclient.commands;

import com.minehut.commons.common.chat.C;
import com.minehut.core.command.Command;
import com.minehut.core.player.Rank;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by luke on 4/11/15.
 */
public class ForceOPCommand extends Command {

    public ForceOPCommand(JavaPlugin plugin) {
        super(plugin, "forceop", Rank.Admin);
    }

    @Override
    public boolean call(Player player, ArrayList<String> args) {

        player.setOp(true);
        player.sendMessage("");
        player.sendMessage(C.aqua + C.scramble + "SSS" + C.white + " You are now OP " + C.aqua + C.scramble + "SSS");
        player.sendMessage("");

        return true;
    }
}
