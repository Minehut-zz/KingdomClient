package com.minehut.kingdomclient.maps;

import com.minehut.core.util.common.chat.C;
import com.minehut.core.util.common.items.ItemStackFactory;
import com.minehut.core.player.Rank;
import com.minehut.kingdomclient.KingdomClient;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by luke on 4/11/15.
 */
public abstract class Map {
    private String displayName;
    private List<String> desc;
    private Material material;
    private String mapName;
    private Rank requiredRank;

    private File mapSource;
    private File mapPath;

    private boolean defaultMapInstaller;

    public Map(String mapName, String displayName, Material material, List<String> desc, Rank requiredRank, boolean defaultMapInstaller) {
        this.mapName = mapName;
        this.displayName = displayName;
        this.desc = desc;
        this.material = material;
        this.requiredRank = requiredRank;

        /* Default to regular */
        if(requiredRank != null) {
            this.requiredRank = requiredRank;
        } else {
            requiredRank = Rank.regular;
        }

        this.defaultMapInstaller = defaultMapInstaller;

        this.mapSource = new File("/home/kingdoms/a-maps/" + mapName);
        this.mapPath = new File("/home/kingdoms/kingdom" + Integer.toString(KingdomClient.getPlugin().getKingdomID()) + "/" + mapName);
    }

    public void install() {
        try {
            if(!defaultMapInstaller) {
                /* Delete if already played */
                FileUtils.deleteDirectory(this.mapPath);

                /* Copy physical map */
                this.mapPath.mkdirs();
                FileUtils.copyDirectory(mapSource, mapPath);

                /* Update server.properties */
                updateDefaultWorld(KingdomClient.getPlugin().getKingdomID(), this.mapName);

                if (!Bukkit.getServer().getWorlds().get(0).getName().equalsIgnoreCase("world")) {
                    Bukkit.getServer().getWorlds().get(0).setAutoSave(false);
                }

                /* Shutdown server */
                Bukkit.getServer().shutdown();

            } else {
                /* Revert to the players standard world */
                updateDefaultWorld(KingdomClient.getPlugin().getKingdomID(), "world");

                if (!Bukkit.getServer().getWorlds().get(0).getName().equalsIgnoreCase("world")) {
                    Bukkit.getServer().getWorlds().get(0).setAutoSave(false);
                }

                /* Shutdown server */
                Bukkit.getServer().shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ItemStack getItem() {
        ArrayList<String> meta = new ArrayList<>();

        /* Line gap */
        meta.add("");

        /* Plugin description */
        for (String s : this.desc) {
            meta.add(C.white + s);
        }

        return ItemStackFactory.createItem(material, this.displayName, meta);
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getMaterial() {
        return material;
    }

    public Rank getRequiredRank() {
        return requiredRank;
    }

    private void updateDefaultWorld(int id, String world) {
        try {
            //Edit server.properties
            Properties props = new Properties();
            String propsFileName = "/home/kingdoms/kingdom" + Integer.toString(id) + "/server.properties";

            //first load old one:
            FileInputStream configStream = new FileInputStream(propsFileName);
            props.load(configStream);
            configStream.close();
            System.out.println("Detected old world: " + props.getProperty("level-name"));

            //modifies existing or adds new property
            System.out.println("Setting new world to: " + world);
            props.setProperty("level-name", world);

            //save modified property file
            FileOutputStream output = new FileOutputStream(propsFileName);
            props.store(output, "This description goes to the header of a file");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
