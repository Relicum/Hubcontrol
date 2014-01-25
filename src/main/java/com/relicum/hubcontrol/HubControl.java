/**
 * Name: HubControl.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol;

import com.relicum.hubcontrol.listeners.Damage;
import com.relicum.hubcontrol.listeners.Health;
import com.relicum.hubcontrol.listeners.LeafDecay;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HubControl extends JavaPlugin {

    private PluginManager pm;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        this.pm = getServer().getPluginManager();

        if(getConfig().getBoolean("listeners.noHunger")){
            pm.registerEvents(new Health(),this);
            getLogger().info("No Hunger loss is enabled");
        }

        if(getConfig().getBoolean("listeners.noDamage")){
            pm.registerEvents(new Damage(),this);
            getLogger().info("No Damage loss is enabled");

        }

        if(getConfig().getBoolean("listeners.noLeafDecay")){
           pm.registerEvents(new LeafDecay(),this);
            getLogger().info("No Leaf Decay is enabled");
        }



    }

    @Override
    public void onDisable() {

    }
}
