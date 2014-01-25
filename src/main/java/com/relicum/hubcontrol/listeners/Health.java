/**
 * Name: Health.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Health implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFoodLevel(FoodLevelChangeEvent e){
        if(!(e.getEntity() instanceof Player)){
           return;
        }
        e.setCancelled(true);
        Player player = (Player)e.getEntity();
        player.setFoodLevel(20);
    }
}
