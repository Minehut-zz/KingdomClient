package com.minehut.kingdomclient.commands;

import com.minehut.core.util.common.chat.C;
import com.minehut.core.util.common.chat.F;
import com.minehut.core.util.common.sound.S;
import com.minehut.core.command.Command;
import com.minehut.core.player.Rank;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by luke on 4/11/15.
 */
public class RenameCommand extends Command {

    public RenameCommand(JavaPlugin plugin) {
        super(plugin, "rename", Rank.regular);
    }

    @Override
    public boolean call(Player player, ArrayList<String> args) {

        S.click(player);
        player.sendMessage("");
        F.message(player,  C.yellow + "This command is only usable in the " + C.green + "/hub");
        player.sendMessage("");

        return true;
    }
}
