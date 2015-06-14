package com.minehut.kingdomclient.commands;

import com.minehut.commons.common.chat.C;
import com.minehut.core.command.Command;
import com.minehut.core.player.Rank;
import com.minehut.kingdomclient.KingdomClient;
import com.minehut.kingdomclient.plugins.StatusPlugin;
import com.minehut.status.Status;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by luke on 4/11/15.
 */
public class FeatureCommand extends Command {
    StatusPlugin statusPlugin;

    public FeatureCommand(JavaPlugin plugin) {
        super(plugin, "feature", Rank.Admin);
        this.statusPlugin = new StatusPlugin();
    }

    @Override
    public boolean call(Player player, ArrayList<String> args) {

        if (args.size() == 1 && args.get(0).equalsIgnoreCase("remove")) {
            KingdomClient.getPlugin().setFeatured(false);

            player.sendMessage("");
            player.sendMessage("Set feature to " + C.red + "false");
            player.sendMessage("Restart to take effect");
            player.sendMessage("");
            return true;
        }

        if(args.size() >= 1) {

            /* Select Entered Message */
            String desc = "";
            for (String s : args) {
                desc += (s + " ");
            }

            KingdomClient.getPlugin().setDesc(desc);

            if (KingdomClient.getPlugin().isFeatured()) {
                Status.getStatus().setServerDesc(desc);
            } else {
                KingdomClient.getPlugin().setFeatured(true);
                Status.getStatus().startStatusUpload("kingdom", KingdomClient.getPlugin().getKingdomName(), KingdomClient.getPlugin().getBungeeID(), KingdomClient.getPlugin().getDesc());
            }

            player.sendMessage("");
            player.sendMessage("Updated Server Description.");
            player.sendMessage("");
        } else {
            player.sendMessage("Please use the format " + C.aqua + "/feature (desc)");
        }

        return true;
    }

}
