package com.minehut.kingdomclient;

import com.minehut.commons.common.chat.C;
import com.minehut.commons.common.items.EnchantGlow;
import com.minehut.commons.common.items.ItemStackFactory;
import com.minehut.core.player.Rank;
import org.apache.commons.io.FileUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 4/11/15.
 */
public abstract class Plugin {
    private String displayName;
    private List<String> desc;
    private Material material;
    private String jarName;
    private String configName;
    private InstallState installed;
    private Rank requiredRank;

    private File jarSource;
    private File configSource;
    private File jarPath;
    private File configPath;

    public Plugin(String displayName, String jarName, String configName, Material material, List<String> desc) {
        this.displayName = displayName;
        this.jarName = jarName;
        this.configName = configName;
        this.desc = desc;
        this.material = material;

        /* Default to regular */
        this.requiredRank = Rank.regular;

        this.jarSource = new File("/home/plugin-repo/" + jarName);
        this.configSource = new File("/home/plugin-repo/" + configName);

        this.jarPath = new File("/home/kingdoms/kingdom" + Integer.toString(KingdomClient.getPlugin().getKingdomID()) + "/plugins/" + jarName);
        this.configPath = new File("/home/kingdoms/kingdom" + Integer.toString(KingdomClient.getPlugin().getKingdomID()) + "/plugins/" + configName);

        this.installed = InstallState.no;
    }

    public Plugin(String displayName, String jarName, String configName, Material material, List<String> desc, Rank requiredRank) {
        this.displayName = displayName;
        this.jarName = jarName;
        this.configName = configName;
        this.desc = desc;
        this.material = material;

        this.requiredRank = requiredRank;

        this.jarSource = new File("/home/plugin-repo/" + jarName);
        this.configSource = new File("/home/plugin-repo/" + configName);

        this.jarPath = new File("/home/kingdoms/kingdom" + Integer.toString(KingdomClient.getPlugin().getKingdomID()) + "/plugins/" + jarName);
        this.configPath = new File("/home/kingdoms/kingdom" + Integer.toString(KingdomClient.getPlugin().getKingdomID()) + "/plugins/" + configName);

        this.installed = InstallState.no;
    }

    public void install() {
        /* Check for already installed */
        if(this.installed == InstallState.yes) return;

        try {
            FileUtils.copyFile(jarSource, jarPath);
            if(configSource.exists()) {
                FileUtils.copyDirectory(configSource, configPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.installed = InstallState.onRestart;
    }

    public void delete() {
        /* Check for already deleted */
        if(this.installed == InstallState.no) return;

        try {
            FileUtils.forceDelete(jarPath);
            FileUtils.deleteDirectory(configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.installed = InstallState.no;
    }

    public ItemStack getItem() {

        ArrayList<String> meta = new ArrayList<>();
        if (this.installed == InstallState.yes) {
            meta.add(C.white + "Installed: " + C.green + C.bold + "YES");
        } else if (this.installed == InstallState.no){
            meta.add(C.white + "Installed: " + C.red + C.bold + "NO");
        } else if (this.installed == InstallState.onRestart){
            meta.add(C.white + "Installed: " + C.yellow + C.bold + "INSTALLING NEXT RESTART");
        }

        /* Line gap */
        meta.add("");

        /* Donor Info */
        if (this.requiredRank != Rank.regular) {
            meta.add(C.white + "Only available to rank: " + this.requiredRank.getTag());

            /* Line gap */
            meta.add("");
        }

        /* Plugin description */
        for (String s : this.desc) {
            meta.add(C.white + s);
        }

        ItemStack item = ItemStackFactory.createItem(material, this.displayName, meta);

        if (this.installed == InstallState.yes) {
            EnchantGlow.addGlow(item);
        }

        return item;
    }

    private String getJarName() { //TODO: Use this to make storing the string useful
    	return this.jarName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public List<String> getDesc() {
        return desc;
    }

    public InstallState getInstalled() {
        return installed;
    }

    public String getConfigName() {
        return configName;
    }

    public File getJarPath() {
        return jarPath;
    }

    public File getConfigPath() {
        return configPath;
    }

    public void setInstalled(InstallState installed) {
        this.installed = installed;
    }

    public Material getMaterial() {
        return material;
    }

    public Rank getRequiredRank() {
        return requiredRank;
    }
}
