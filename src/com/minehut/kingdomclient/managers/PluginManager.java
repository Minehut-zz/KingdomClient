package com.minehut.kingdomclient.managers;

import com.minehut.core.util.common.chat.C;
import com.minehut.core.util.common.chat.F;
import com.minehut.core.util.common.items.ItemStackFactory;
import com.minehut.core.util.common.sound.S;
import com.minehut.core.Core;
import com.minehut.core.player.PlayerInfo;
import com.minehut.kingdomclient.InstallState;
import com.minehut.kingdomclient.KingdomClient;
import com.minehut.kingdomclient.maps.Map;
import com.minehut.kingdomclient.Plugin;
import com.minehut.kingdomclient.maps.DefaultMap;
import com.minehut.kingdomclient.maps.DotaMap;
import com.minehut.kingdomclient.plugins.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by luke on 4/11/15.
 */
public class PluginManager implements Listener {
    private ArrayList<Plugin> plugins;
    private ArrayList<Map> maps;

    /* Pages */
    private Inventory homePage;
    private Inventory pluginPage;
    private Inventory mapPage;
    private Inventory restartConfirmation;
    private Inventory serverProperties;
    private Inventory resetConfirmation;
    
    public PluginManager (KingdomClient plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.plugins = new ArrayList<>();
        this.maps = new ArrayList<>();

        /* Plugins */
        this.plugins.add(new GroupManagerPlugin());
        this.plugins.add(new PexPlugin());
        this.plugins.add(new TrailsGUIPlugin());
        this.plugins.add(new CrackShotPlugin());
        this.plugins.add(new EssentialsPlugin());
        this.plugins.add(new EssentialsSpawnPlugin());
        this.plugins.add(new EssentialsChatPlugin());
        this.plugins.add(new WorldEditPlugin());
        this.plugins.add(new WorldGuardPlugin());
        this.plugins.add(new SpecifiedBreakPlugin());
        this.plugins.add(new McMMOPlugin());
        this.plugins.add(new ChestShopPlugin());
        this.plugins.add(new KitAdderPlugin());
        this.plugins.add(new FactionsPlugin());
        this.plugins.add(new PrisonMinesPlugin());
        this.plugins.add(new GriefPreventionPlugin());
        this.plugins.add(new TogglePvpPlugin());
        this.plugins.add(new NoCheatPlusPlugin());
        this.plugins.add(new MagicPlugin());
        this.plugins.add(new CorpsesPlugin());
        this.plugins.add(new CombatTagPlugin());
        this.plugins.add(new CitizensPlugin());
        this.plugins.add(new LWCPlugin());
        this.plugins.add(new ClearLagPlugin());
        this.plugins.add(new ChatFilterPlugin());
        this.plugins.add(new TimPlugin());

        /* Maps */
        this.maps.add(new DefaultMap());
        this.maps.add(new DotaMap());

        /* Initiate Pages */
        this.createHomePage();
        this.createPluginPage();
        this.createMapPage();
        this.createRestartConfirmationPage();
        this.createPropertiesPage();
        this.createResetConfirmationPage();
        
        this.findInstalled();
        this.updatePluginPage();
    }

    /* ------ PAGE CONTROL ------ */

    @EventHandler
    public void onPageClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (KingdomClient.getPlugin().getOwnerUUID().toString().equalsIgnoreCase(player.getUniqueId().toString())) {

            if (event.getInventory().getName().equalsIgnoreCase("Home")) {
                S.click(player);
                event.setCancelled(true);

             /* Empty Slot Click */
                if (event.getCurrentItem().getType() == null) return;

            /* Plugins Button */
                if (event.getCurrentItem().getType() == Material.MINECART) {
                    player.openInventory(this.pluginPage);
                }

             /* Maps Button */
                if (event.getCurrentItem().getType() == Material.PAPER) {
                    player.openInventory(this.mapPage);
                }
            
            /* Server Properties Button */
                if (event.getCurrentItem().getType() == Material.COMMAND) {
                    player.openInventory(this.serverProperties);
                }
            
            /* Shutdown Button */
                if (event.getCurrentItem().getType() == Material.LAVA_BUCKET) {
                    player.openInventory(this.restartConfirmation);
                }
            } else if (event.getInventory().getName().equalsIgnoreCase("Server Properties")) {
                S.click(player);
                event.setCancelled(true);
            /* Empty Slot Click */
                if (event.getCurrentItem().getType() == null) return;

                if (event.getCurrentItem().getType() == Material.STONE) {
                    //TODO: Set world type to default
                    this.updateServerPropertiesString(KingdomClient.getPlugin().getKingdomID(), "level-type", "DEFAULT");
                    player.closeInventory();
                    player.sendMessage("");
                    player.sendMessage(C.green + "Successfully set world type to " + C.aqua + "DEFAULT");
                    player.sendMessage(C.white + "Reset your world for this to take effect.");
                    player.sendMessage("");
                } else if (event.getCurrentItem().getType() == Material.GRASS) {
                    //TODO: Set world type to flatland
                    this.updateServerPropertiesString(KingdomClient.getPlugin().getKingdomID(), "level-type", "FLAT");
                    player.closeInventory();
                    player.sendMessage("");
                    player.sendMessage(C.green + "Successfully set world type to " + C.aqua + "FLAT");
                    player.sendMessage(C.white + "Reset your world for this to take effect.");
                    player.sendMessage("");
                } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM) {
                    //TODO: Enable hardcore mode
                    player.sendMessage(C.red + "That feature is coming soon!");
                    player.closeInventory();
                } else if (event.getCurrentItem().getType() == Material.TNT) {
                    //TODO: Create a reset action file
                    player.openInventory(this.resetConfirmation);
                } else if (event.getCurrentItem().getType() == Material.ARROW) {
                    player.openInventory(this.homePage);
                }

            } else if (event.getInventory().getName().equalsIgnoreCase("Shutdown Confirmation")) {
                S.click(player);
                event.setCancelled(true);

            /* Empty Slot Click */
                if (event.getCurrentItem().getType() == null) return;

            /* Plugins Button */
                if (event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                    Bukkit.getServer().shutdown();
                } else if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                    player.closeInventory();
                }

            } else if (event.getInventory().getName().equalsIgnoreCase("Reset Confirmation")) {
                S.click(player);
                event.setCancelled(true);
            /* Empty Slot Click */
                if (event.getCurrentItem().getType() == null) return;
                if (event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                    File actionFolder = new File("/home/kingdoms/kingdom" + KingdomClient.getPlugin().getKingdomID() + "/actions");
                    if (!actionFolder.exists()) {
                        actionFolder.mkdir();
                    }
                    File actionFile = new File(actionFolder.getAbsolutePath() + "/resetmap.action");
                    if (!actionFile.exists()) {
                        try {
                            actionFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    S.playSound(Sound.FIREWORK_TWINKLE);
                    Bukkit.getServer().broadcastMessage("");
                    F.broadcast(C.red + C.bold + "WARNING: " + C.aqua + player.getName() + C.white + " has scheduled a " + C.red + "World Reset");
                    F.broadcast("Complete this process by typing " + C.aqua + "/stop");
                    Bukkit.getServer().broadcastMessage("");
                    //Bukkit.getServer().shutdown();
                } else if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                    player.closeInventory();
                }

            } else if (event.getInventory().getName().equalsIgnoreCase("Maps")) {
                S.click(player);
                event.setCancelled(true);

            /* Empty Slot Click */
                if (event.getCurrentItem() == null) return;
                if (event.getCurrentItem().getItemMeta() == null) return;
                if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            /* Home Page Icon */
                if (event.getCurrentItem().getType() == Material.ARROW) {
                    player.openInventory(this.homePage);
                    return;
                }

            /* Find clicked map */
                for (Map map : this.maps) {
                    if (map.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                        PlayerInfo playerInfo = Core.getInstance().getPlayerInfo(player);

                    /* Check permissions */
                        if (!playerInfo.getRank().has(player, map.getRequiredRank(), false)) {
                            player.sendMessage("");
                            player.sendMessage("This map requires rank " + map.getRequiredRank().getTag());
                            player.sendMessage("");
                            S.playSound(player, Sound.GLASS);
                            return;
                        }

                    /* Warn that map is being installed */
                        Bukkit.getServer().broadcastMessage("");
                        Bukkit.getServer().broadcastMessage(C.red + C.bold + "WARNING: " + C.white + "An adventure map is being installed by " + C.aqua + player.getName());
                        Bukkit.getServer().broadcastMessage("");

                    /* All is good, install the map */
                        map.install();
                    }
                }
            } else if (event.getInventory().getName().equalsIgnoreCase("Plugins")) {
                S.click(player);
                event.setCancelled(true);

             /* Empty Slot Click */
                if (event.getCurrentItem() == null) return;
                if (event.getCurrentItem().getItemMeta() == null) return;
                if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            /* Home Page Icon */
                if (event.getCurrentItem().getType() == Material.ARROW) {
                    player.openInventory(this.homePage);
                    return;
                }

            /* Find clicked plugin */
                for (Plugin plugin : this.plugins) {


                    if (plugin.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                        PlayerInfo playerInfo = Core.getInstance().getPlayerInfo(player);

                    /* Check permissions */
                        if (!playerInfo.getRank().has(player, plugin.getRequiredRank(), false)) {
                            player.sendMessage("");
                            player.sendMessage("This plugin requires rank " + plugin.getRequiredRank().getTag());
                            player.sendMessage("");
                            S.playSound(player, Sound.GLASS);
                            return;
                        }

                        if (plugin.getInstalled() == InstallState.yes) {
                            plugin.delete();

                            S.playSound(Sound.FIREWORK_TWINKLE);
                            Bukkit.getServer().broadcastMessage("");
                            F.broadcast(C.red + C.bold + player.getName() + C.yellow + " has" + C.red + " removed " + C.aqua + plugin.getDisplayName());
                            F.broadcast("This will take effect after restarting with " + C.aqua + "/stop");
                            Bukkit.getServer().broadcastMessage("");

                            updatePluginPage();
                            return;
                        } else if (plugin.getInstalled() == InstallState.onRestart) {
                            plugin.delete();

                            S.playSound(Sound.FIREWORK_TWINKLE);
                            Bukkit.getServer().broadcastMessage("");
                            F.broadcast(C.red + C.bold + player.getName() + C.yellow + " has" + C.red + " removed " + C.aqua + plugin.getDisplayName());
                            F.broadcast("This will take effect after restarting with " + C.aqua + "/stop");
                            Bukkit.getServer().broadcastMessage("");

                            updatePluginPage();
                            return;
                        } else {
                            plugin.install();

                            S.playSound(Sound.FIREWORK_TWINKLE);
                            Bukkit.getServer().broadcastMessage("");
                            F.broadcast(C.red + C.bold + player.getName() + C.yellow + " has" + C.green + " installed " + C.aqua + plugin.getDisplayName());
                            F.broadcast("This will take effect after restarting with " + C.aqua + "/stop");
                            Bukkit.getServer().broadcastMessage("");

                            updatePluginPage();
                            return;
                        }
                    }
                }
            }
        }
    }

    private void createHomePage() {
        this.homePage = Bukkit.getServer().createInventory(null, 9, "Home");

        /* Plugins Button */
        this.homePage.setItem(1, ItemStackFactory.createItem(Material.MINECART, C.yellow + "Plugins Menu"));

        /* Maps Button */
        this.homePage.setItem(3, ItemStackFactory.createItem(Material.PAPER, C.yellow + "Maps Menu"));
        
        /* Maps Button */
        this.homePage.setItem(5, ItemStackFactory.createItem(Material.COMMAND, C.yellow + "Server Properties"));

        
        /* Restart Button */
        this.homePage.setItem(8, ItemStackFactory.createItem(Material.LAVA_BUCKET, C.red + C.bold + "SHUTDOWN SERVER"));
    }

    private void createRestartConfirmationPage() {
        this.restartConfirmation = Bukkit.getServer().createInventory(null, 9, "Shutdown Confirmation");

        /* Yes */
        this.restartConfirmation.setItem(2, ItemStackFactory.createItem(Material.EMERALD_BLOCK, C.green + "Yes, shutdown my server!"));

        /* No */
        this.restartConfirmation.setItem(6, ItemStackFactory.createItem(Material.REDSTONE_BLOCK, C.red + "No, don't shutdown my server!"));
    }

    private void createPluginPage() {
        this.pluginPage = Bukkit.getServer().createInventory(null, 36, "Plugins");

        this.updatePluginPage();
    }
    
    private void createResetConfirmationPage() {
        this.resetConfirmation = Bukkit.getServer().createInventory(null, 9, "Reset Confirmation");

        /* Yes */
        this.resetConfirmation.setItem(2, ItemStackFactory.createItem(Material.EMERALD_BLOCK, C.green + "Yes, reset my server!"));

        /* No */
        this.resetConfirmation.setItem(6, ItemStackFactory.createItem(Material.REDSTONE_BLOCK, C.red + "No, don't reset my server!"));
    }

    private void createPropertiesPage() {
    	this.serverProperties = Bukkit.getServer().createInventory(null, 36, "Server Properties");
    	
    	this.serverProperties.setItem(1, ItemStackFactory.createItem(Material.STONE, C.yellow + "Default World"));
    	
    	this.serverProperties.setItem(3, ItemStackFactory.createItem(Material.GRASS, C.yellow + "Flat World"));
    	
    	this.serverProperties.setItem(5, ItemStackFactory.createItem(Material.SKULL_ITEM, C.yellow + "Hardcore Mode"));
    	
    	this.serverProperties.setItem(7, ItemStackFactory.createItem(Material.TNT, C.yellow + "Reset World"));
    	
    	this.serverProperties.setItem(31, ItemStackFactory.createItem(Material.ARROW, C.yellow + "Home Page"));
    }
    
    public void updatePluginPage() {
        this.pluginPage.clear();

        for (Plugin plugin : this.plugins) {
            this.pluginPage.addItem(plugin.getItem());
        }

        /* Homepage Button */
        this.pluginPage.setItem(31, ItemStackFactory.createItem(Material.ARROW, C.yellow + "Home Page"));
    }

    public void findInstalled() {
        for (Plugin plugin : this.plugins) {
            if (plugin.getJarPath().exists()) {
                plugin.setInstalled(InstallState.yes);
            }
        }
    }

    public Plugin getPlugin(String name) {
        Plugin plugin = null;
        for (Plugin p : this.plugins) {
            if (p.getDisplayName().equalsIgnoreCase(name)) {
                plugin = p;
            }
        }
        return plugin;
    }

    private void createMapPage() {
        this.mapPage = Bukkit.getServer().createInventory(null, 36, "Maps");

        for (Map map : this.maps) {
            this.mapPage.addItem(map.getItem());
        }

        /* Homepage Button */
        this.mapPage.setItem(31, ItemStackFactory.createItem(Material.ARROW, C.yellow + "Home Page"));
    }

    public void openMenu(Player player) {
        player.openInventory(this.homePage);
    }

    public ArrayList<Plugin> getPlugins() {
        return plugins;
    }
    
    
    
    private void updateServerPropertiesString(int id, String prop, String data) {
        try {
            //Edit server.properties
            Properties props = new Properties();
            String propsFileName = "/home/kingdoms/kingdom" + Integer.toString(id) + "/server.properties";

            //first load old one:
            FileInputStream configStream = new FileInputStream(propsFileName);
            props.load(configStream);
            configStream.close();
            System.out.println("Detected old data: " + props.getProperty(prop));

            //modifies existing or adds new property
            System.out.println("Setting new data to: " + data);
            props.setProperty(prop, data);

            //save modified property file
            FileOutputStream output = new FileOutputStream(propsFileName);
            props.store(output, "This description goes to the header of a file");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
