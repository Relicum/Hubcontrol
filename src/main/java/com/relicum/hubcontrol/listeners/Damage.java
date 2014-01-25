/**
 * Name: Damage.java Edited: 25 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent e){
        if(e.getCause() == EntityDamageEvent.DamageCause.VOID)
            return;
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);
            Player player = (Player)e.getEntity();
            player.setHealth(player.getMaxHealth());

        }
    }
}
