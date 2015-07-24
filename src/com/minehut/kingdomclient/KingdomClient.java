package com.minehut.kingdomclient;

import com.minehut.core.util.common.chat.C;
import com.minehut.core.util.common.chat.F;
import com.minehut.core.util.common.sound.S;
import com.minehut.kingdomclient.commands.*;
import com.minehut.kingdomclient.managers.CheatManager;
import com.minehut.kingdomclient.managers.CommandMonitor;
import com.minehut.kingdomclient.managers.PluginManager;

import com.minehut.kingdomclient.status.KingdomStatusUploader;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by luke on 4/9/15.
 */
public class KingdomClient extends JavaPlugin implements Listener {
    int kingdomID = 0;
    private UUID ownerUUID;
    private String ownerName;
    private String kingdomName;
    private static KingdomClient plugin;
    private PluginManager pluginManager;
    private CommandMonitor commandMonitor;
    private boolean featured;
    private String desc;
    private String motd;

    private KingdomStatusUploader kingdomStatusUploader;

    @Override
    public void onEnable() {
        plugin = this;
        this.getServer().getPluginManager().registerEvents(this, this);

        this.loadConfig();

        this.pluginManager = new PluginManager(this);
        this.commandMonitor = new CommandMonitor(this);
        new CheatManager(this); //Anti-Cheat for signs
        
        this.output();

        /* Commands */
        new ControlCommand(this);
        new CCommand(this);
        new ForceOPCommand(this);
        new MOTDCommand(this);
        new JoinCommand(this);
        new RenameCommand(this);
        new ResetCommand(this);

        /* Auto shutdown non-featured servers */
        if(!this.featured) {
        /* Catch if server is left open while empty */
            this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    if (getServer().getOnlinePlayers().size() == 0) {
                        getServer().shutdown();
                    }
                }
            }, 20 * 30);
        }

        /* Allows for Dynamic MOTD changes */
        this.motd = this.getServer().getMotd();

        /* Upload Status */
        this.kingdomStatusUploader = new KingdomStatusUploader(this);
    }

    @Override
    public void onDisable() {
        File source = new File("/home/kingdoms/sampleKingdom/kingdomclient.jar");
        File path = new File("/home/kingdoms/kingdom" + Integer.toString(this.kingdomID) + "/plugins/kingdomclient.jar");

        try {
            FileUtils.copyFile(source, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(this.motd);
    }

    void output() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

                /* Player Count */
                if (Bukkit.getOnlinePlayers().size() == 0) {
                    System.out.println("kingdom_PlayersOnline:0");
                } else {
                    System.out.println("kingdom_PlayersOnline:" + Integer.toString(Bukkit.getOnlinePlayers().size()));
                }

            }
        }, 0, 1 * 20);
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        this.ownerName = getConfig().getString("owner-name");
        this.ownerUUID = UUID.fromString(getConfig().getString("owner-uuid"));
        this.kingdomID = getConfig().getInt("kingdom-id");
        this.kingdomName = getConfig().getString("kingdom-name");
        this.featured = getConfig().getBoolean("featured");
        this.desc = getConfig().getString("desc");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getUniqueId().toString().equalsIgnoreCase(this.ownerUUID.toString())) {
            Player player = event.getPlayer();
            player.setOp(true);

            this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

                @Override
                public void run() {
                    if (event.getPlayer() != null) {
                        S.playSound(player, Sound.FIREWORK_TWINKLE);
                        event.getPlayer().sendMessage("");
                        F.message(event.getPlayer(), C.yellow + "Welcome to your kingdom server!");
                        F.message(event.getPlayer(), C.yellow + "For customization, type " + C.aqua + "/control");
                        F.message(event.getPlayer(), C.yellow + "Other players can join you from" + C.green + " /hub" + C.yellow + " by typing " + C.aqua + "/join " + kingdomName);
                        event.getPlayer().sendMessage("");
                    }
                }
            }, 20L);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (this.getServer().getOnlinePlayers().size() <= 1) {
            this.getServer().shutdown();
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (this.getServer().getOnlinePlayers().size() <= 1) {
            this.getServer().shutdown();
        }
    }

    public static KingdomClient getPlugin() {
        return plugin;
    }

    public int getKingdomID() {
        return kingdomID;
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public String getBungeeID() {
        return ("kingdom" + Integer.toString(this.getServer().getPort() - 60000));
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;

        this.getConfig().set("featured", featured);

        try {
            this.getConfig().save(getDataFolder() + File.separator + "config.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;

        this.getConfig().set("desc", desc);

        try {
            this.getConfig().save(getDataFolder() + File.separator + "config.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public String getMotd() {
        return motd;
    }
}
