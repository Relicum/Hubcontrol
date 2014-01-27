/**
 * Name: createworld.java Edited: 26 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.commands;

import com.relicum.hubcontrol.utils.GAMERULE;
import com.relicum.simplesubs.MultiSub;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.io.IOException;

public class createworld extends MultiSub {
    @Override
    public int setMaxArgs() {
        return 2;
    }

    @Override
    public int setMinArgs() {
        return 1;
    }

    @Override
    public boolean onCommand(Player player, String[] strings) throws IOException, ClassNotFoundException {
        if(strings.length == 1){
            WorldCreator wc = new WorldCreator(strings[1]);
            wc.generator("CleanroomGenerator:.");
            wc.generateStructures(false);
            wc.environment(World.Environment.NORMAL);
            wc.type(WorldType.FLAT);
            World world = wc.createWorld();
            world.setSpawnLocation(0,32,0);
            world.setKeepSpawnInMemory(true);
            BlockState bs = world.getBlockAt(0, 31, 0).getState();
            bs.setType(Material.GOLD_BLOCK);
            bs.update(true);
            world.setPVP(false);
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setGameRuleValue(GAMERULE.doMobSpawning.name(), "false");
            world.setGameRuleValue(GAMERULE.doFireTick.name(),"false");
            world.setTime(5000l);

            world.setGameRuleValue(GAMERULE.doDaylightCycle.name(),"false");
            world.save();
            world.setAutoSave(true);
        }
        System.out.println("Successfully created a new world");
        return true;
    }

    @Override
    public String setDescription() {
        return "&bCreate a new world passing in a name for the World and option boolean for world type";
    }

    @Override
    public String setPermission() {
        return "hubcontrol.admin.createworld";
    }

    @Override
    public String setUsage() {
        return "/hubctl createworld [name] (type)";
    }

    @Override
    public String setLabel() {
        return "hubctl createworld";
    }

    @Override
    public String setCmdString() {
        return "hubctl createworld";
    }

    @Override
    public PermissionDefault setPermissionDefault() {
        return PermissionDefault.OP;
    }
}
