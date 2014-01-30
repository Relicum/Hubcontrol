/**
 * Name: setspawn.java Edited: 27 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.commands;

import com.relicum.simplesubs.FixedSub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.io.IOException;
import java.util.List;

public class setspawn extends FixedSub implements TabCompleter {

    @Override
    public boolean onCommand(Player player, String[] strings) throws IOException, ClassNotFoundException {

        return false;
    }

    @Override
    public String setDescription() {
        return "&bSet the default spawn as well as an option to set first time spawn,supports multi worlds. Set world then either default or first";
    }

    @Override
    public String setPermission() {
        return "hubcontrol.admin.setspawn";
    }

    @Override
    public String setUsage() {
        return "/hubctl setspawn [world] [default|first]";
    }

    @Override
    public String setLabel() {
        return "hubctl setspawn";
    }

    @Override
    public String setCmdString() {
        return "hubctl setspawn";
    }

    @Override
    public PermissionDefault setPermissionDefault() {
        return PermissionDefault.OP;
    }

    @Override
    public int setNumArgs() {
        return 2;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] strings) {
        return null;
    }
}
