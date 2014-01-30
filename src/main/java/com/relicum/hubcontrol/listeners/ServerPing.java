/**
 * Name: ServerPing.java Edited: 27 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Iterator;

public class ServerPing implements Listener {

    public void onPing(ServerListPingEvent e) {
        if (!e.getMotd().startsWith("blah")) {

            ServerListPingEvent event = new ServerListPingEvent(e.getAddress(), "blahblahbal", 762, 1000);
            Bukkit.getServer().getPluginManager().callEvent(event);
        } else
            e.setMotd("&e&ljnvckjdsnjkcnk cnsknjclksncksnlc klncsncklnsdlcnskldnc mdcsnclksncl");
        Iterator<Player> it = e.iterator();
        while (it.hasNext()) {
            if (it.hasNext()) {
                if (it.next().getName().equalsIgnoreCase("Relicum"))
                    it.remove();
            }
        }

        e.setMaxPlayers(e.getNumPlayers() + 1);
    }
}
