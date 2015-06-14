package com.minehut.kingdomclient.commands;

import com.minehut.commons.common.chat.C;
import com.minehut.commons.common.chat.F;
import com.minehut.core.Core;
import com.minehut.core.command.Command;
import com.minehut.core.player.PlayerInfo;
import com.minehut.core.player.Rank;
import com.minehut.kingdomclient.KingdomClient;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by luke on 4/11/15.
 */
public class MOTDCommand extends Command {

    public MOTDCommand(JavaPlugin plugin) {
        super(plugin, "setmotd", Rank.regular);
    }

    @Override
    public boolean call(Player player, ArrayList<String> args) {
        PlayerInfo playerInfo = Core.getInstance().getPlayerInfo(player);

        /* Owner Check */
        if (!player.getUniqueId().equals(KingdomClient.getPlugin().getOwnerUUID()) && !playerInfo.getRank().has(null, Rank.Admin, false)) {
            player.sendMessage("That command is only avaliable to the " + C.aqua + "Server Owner");
            return true;
        }

        /* Rank Check */
        if (!playerInfo.getRank().has(player, Rank.Mega, true)) {
            return true;
        }

        /* Select Entered Message */
        String motd = "";
        for (String s : args) {
            motd += (s + " ");
        }

        /* Update Live MOTD */
        KingdomClient.getPlugin().setMotd(motd);

        /* Update MOTD in server.properties */
        try {
            //Edit server.properties
            Properties props = new Properties();
            String propsFileName = "/home/kingdoms/kingdom" + Integer.toString(KingdomClient.getPlugin().getKingdomID()) + "/server.properties";

            //first load old one:
            FileInputStream configStream = new FileInputStream(propsFileName);
            props.load(configStream);
            configStream.close();

            //modifies existing or adds new property
            F.log(KingdomClient.getPlugin().getKingdomName(), "Setting MOTD to: " + motd);
            props.setProperty("motd", motd);

            //save modified property file
            FileOutputStream output = new FileOutputStream(propsFileName);
            props.store(output, "This description goes to the header of a file");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();

            player.sendMessage(C.white + "An Error has occured while " + C.red + "updating server.properties" + C.white
                + ". Please stay patient and notify a staff member.");
        }

        /* Confirmation Message */
        player.sendMessage("");
        player.sendMessage(C.aqua + "Updated MOTD: " + C.white + motd);
        player.sendMessage("");

        return true;
    }
}
