/**
 * Name: HubControl.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol;

import com.relicum.hubcontrol.commands.createworld;
import com.relicum.hubcontrol.commands.setspawn;
import com.relicum.hubcontrol.listeners.Damage;
import com.relicum.hubcontrol.listeners.DayLight;
import com.relicum.hubcontrol.listeners.Health;
import com.relicum.hubcontrol.listeners.LeafDecay;
import com.relicum.hubcontrol.utils.SerializedLocation;
import com.relicum.hubcontrol.utils.WORLDTIME;
import com.relicum.simplesubs.CommandManager;
import com.relicum.simplesubs.SimpleMessages;
import com.relicum.simplesubs.Simplesubs;
import com.relicum.simplesubs.registerCommand;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HubControl extends JavaPlugin {

    private PropertiesConfiguration config;
    List<String> worlds = new ArrayList<>();

    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(SerializedLocation.class);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        ConfigurationInterpolator.registerGlobalLookup("echo", new EchoLookup());

        File file = new File(getDataFolder().toString() + "/config.properties");
        if (!file.exists()) {
            file = null;
            saveResource("config.properties", false);
            file = new File(getDataFolder().toString() + "/config.properties");
            if (!file.exists()) {
                System.out.println("New have a file load problem");
            }
        }

        // Register Sub commands using Simple Subs
        Simplesubs ss = (Simplesubs) getServer().getPluginManager().getPlugin("SimpleSubs");
        registerCommand rg = ss.getCommandRegister();
        SimpleMessages messages = ss.getSimpleMessages();
        rg.add(new createworld());
        rg.add(new setspawn());
        CommandManager cm = ss.getCommandManager(this, rg, messages);

        // Register Main Command
        getCommand("hubctl").setExecutor(cm);
        getCommand("hubctl").setPermissionMessage(ChatColor.RED + "You do not have permission to use that command");

        // Load properties file

        try {
            config = new PropertiesConfiguration(getDataFolder().toString() + "/config.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
            return;
        }

        config.setListDelimiter('/');


        // Set listeners as per config.properties
        PluginManager pm = getServer().getPluginManager();

        if (config.getBoolean("toggle.resetTime")) {
            System.out.println("Time is set to be reset");
        }
        if (config.getBoolean("toggle.fixedTime")) {
            System.out.println("Time is set to be fixed");
        }


        if (getProps().getBoolean("toggle.noHunger")) {
            pm.registerEvents(new Health(), this);
            getLogger().info("No Hunger loss is enabled");
        }

        if (getProps().getBoolean("toggle.noDamage")) {
            pm.registerEvents(new Damage(), this);
            getLogger().info("No Damage loss is enabled");

        }

        if (getProps().getBoolean("toggle.noLeafDecay")) {
            pm.registerEvents(new LeafDecay(), this);
            getLogger().info("No Leaf Decay is enabled");
        }

        if (getProps().getBoolean("toggle.fixedTime")) {

            DayLight.setFixedTime(getProps().getString("world.main.name"), WORLDTIME.valueOf(getProps().getString("world.main.fixedTime")).asLong());
            getLogger().info("Time has been fixed for the main world at");
        }

    }

    @Override
    public void onDisable() {
        saveDefaultConfig();

        try {
            config.save();
            System.out.println("Config properties file successfully saved");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }

    public PropertiesConfiguration getProps() {
        return this.config;
    }
}
