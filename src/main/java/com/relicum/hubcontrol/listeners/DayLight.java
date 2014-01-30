/**
 * Name: DayLight.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.listeners;

import com.relicum.hubcontrol.HubControl;
import com.relicum.hubcontrol.utils.GAMERULE;
import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 * Sets the worlds to day or night and helps it at that
 */
public class DayLight {

    /**
     * Set day time only.
     *
     * @param wo
     *         the world
     */
    public static void setDayTimeOnly(String wo) {

        World world = HubControl.getPlugin(HubControl.class).getServer().getWorld(wo);
        world.setTime(6000l);
        world.setGameRuleValue(GAMERULE.doDaylightCycle.name(), "false");
    }

    /**
     * Set night time only.
     *
     * @param wo
     *         the world
     */
    public static void setNightTimeOnly(String wo) {
        World world = HubControl.getPlugin(HubControl.class).getServer().getWorld(wo);
        world.setTime(16000l);
        world.setGameRuleValue(GAMERULE.doDaylightCycle.name(), "false");
    }

    /**
     * Reset Do day night cycle
     *
     * @param wo
     *         the world
     */
    public static void reset(String wo) {
        World world = HubControl.getPlugin(HubControl.class).getServer().getWorld(wo);
        world.setTime(4000l);
        world.setGameRuleValue(GAMERULE.doDaylightCycle.name(), "true");

    }

    /**
     * Set fixed time for the specified world
     *
     * @param wo
     *         the world to fix the time in
     * @param fix
     *         the fixed time as a Long value to set
     */
    public static void setFixedTime(String wo, Long fix) {

        World world = null;
        try {
            world = Bukkit.getWorld(wo);
        } catch (Exception e) {
            System.out.println("The world " + wo + " is not a valid Minecraft world");
            e.printStackTrace();
            return;
        }

        world.setTime(fix);
        world.setGameRuleValue(GAMERULE.doDaylightCycle.name(), "false");


    }
}
