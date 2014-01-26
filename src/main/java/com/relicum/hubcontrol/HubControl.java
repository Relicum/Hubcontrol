/**
 * Name: HubControl.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol;

import com.relicum.hubcontrol.listeners.Damage;
import com.relicum.hubcontrol.listeners.Health;
import com.relicum.hubcontrol.listeners.LeafDecay;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class HubControl extends JavaPlugin {

    private PluginManager pm;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        saveResource("application.properties",false);
/*        Simplesubs ss = (Simplesubs)getServer().getPluginManager().getPlugin("SimpleSubs");
        registerCommand rg = ss.getCommandRegister();
        SimpleMessages messages = ss.getSimpleMessages();*/
        PropertiesConfiguration config;
        try {
            config = new PropertiesConfiguration(getDataFolder() + "/application.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
            return;
        }

        config.setListDelimiter('/');
        config.addProperty("greetings", "Hello, how are you?");
        config.addProperty("colors.pie",
                new String[] {"#FF0000","#00FF00","#0000FF"});
        config.addProperty("colors.graph","#808080/#00FFCC/#6422FF");

        //Access data
        String salut = config.getString("greetings");
        System.out.println("greetings " + salut);
        List<Object> colPie =  config.getList("colors.pie");
        for(Object obj: colPie){
            System.out.println((String)obj);

        }
        String[] colGraph = config.getStringArray("colors.graph");
        for(String sc: colGraph){
            System.out.println(sc);
        }

        String firstPieColor = config.getString("colors.pie");
        try {
            config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        System.out.println("properties file saved");


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
