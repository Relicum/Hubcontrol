/**
 * Name: HubControl.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol;

import com.relicum.hubcontrol.commands.createworld;
import com.relicum.hubcontrol.listeners.Damage;
import com.relicum.hubcontrol.listeners.Health;
import com.relicum.hubcontrol.listeners.LeafDecay;
import com.relicum.simplesubs.CommandManager;
import com.relicum.simplesubs.SimpleMessages;
import com.relicum.simplesubs.Simplesubs;
import com.relicum.simplesubs.registerCommand;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class HubControl extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        ConfigurationInterpolator.registerGlobalLookup("echo", new EchoLookup());

        saveResource("application.properties",false);



        //Register Sub commands using Simple Subs
        Simplesubs ss = (Simplesubs)getServer().getPluginManager().getPlugin("SimpleSubs");
        registerCommand rg = ss.getCommandRegister();
        SimpleMessages messages = ss.getSimpleMessages();
        rg.add(new createworld());
        CommandManager cm = ss.getCommandManager(this,rg,messages);

        //Register Main Command
        getCommand("hubctl").setExecutor(cm);
        getCommand("hubctl").setPermissionMessage(ChatColor.RED + "You do not have permission to use that command");

        //Load properties file
        PropertiesConfiguration config;
        try {
            config = new PropertiesConfiguration(getDataFolder().toString() + "/application.properties");
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
        System.out.println("First Pie color is " + firstPieColor);

        try {
            config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        System.out.println("properties file saved");

        //Set listeners as per config.yml
        PluginManager pm = getServer().getPluginManager();

        if(getConfig().getBoolean("listeners.noHunger")){
            pm.registerEvents(new Health(), this);
           getLogger().info("No Hunger loss is enabled");
        }

        if(getConfig().getBoolean("listeners.noDamage")){
            pm.registerEvents(new Damage(), this);
           getLogger().info("No Damage loss is enabled");

        }

        if(getConfig().getBoolean("listeners.noLeafDecay")){
           pm.registerEvents(new LeafDecay(), this);
           getLogger().info("No Leaf Decay is enabled");
        }

        //Set to always day time



    }

    @Override
    public void onDisable() {
        saveDefaultConfig();

    }
}
