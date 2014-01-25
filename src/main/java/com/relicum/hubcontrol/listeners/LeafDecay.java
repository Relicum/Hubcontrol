/**
 * Name: LeafDecay.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeafDecay implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void noLeafDecay(LeavesDecayEvent e){
        e.setCancelled(true);
    }
}
