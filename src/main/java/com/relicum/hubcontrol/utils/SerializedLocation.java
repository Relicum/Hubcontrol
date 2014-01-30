/**
 * Name: SerializedLocation.java Edited: 26 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.utils;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("SerializedLocation")
public class SerializedLocation implements ConfigurationSerializable {

    private int x;
    private int y;
    private int z;
    private float yaw;
    private float pitch;
    private String world;
    private LocationType type;

    @SuppressWarnings("ConstantConditions")
    public SerializedLocation(String loc) {
        String[] s1;
        s1 = loc.split(":");
        for (int i = 1; i < s1.length; i++) {
            String[] s2 = (String[]) s1[i].split("=");
            if (i == 1) {
                if (!s2[1].equalsIgnoreCase("SerializedLocation")) {
                    System.out.println("Incorrect location string passed, class names do not match");
                }
            }
            switch (i) {
                case 2:
                    this.world = s2[i];
                    break;
                case 3:
                    this.x = Integer.parseInt(s2[2]);
                    break;
                case 4:
                    this.y = Integer.parseInt(s2[2]);
                    break;
                case 5:
                    this.z = Integer.parseInt(s2[2]);
                    break;
                case 6:
                    this.yaw = (Float.parseFloat(s2[2]) + 180.0F + 360.0F) % 360.0F;
                    break;
                case 7:
                    this.pitch = (Float.parseFloat(s2[2]));
                    break;
                case 8:
                    this.type = LocationType.valueOf(s2[2]);
                    break;
            }

        }

    }

    public LocationType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public String getWorld() {
        return world;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("world", this.world);
        map.put("x", this.x);
        map.put("y", this.y);
        map.put("z", this.z);
        map.put("yaw", this.yaw);
        map.put("pitch", this.pitch);
        map.put("type", this.type.name());
        return map;
    }

    public SerializedLocation deserialize(Map<String, Object> map) {
        // int l = map.size();
        // Object ox
        // =map.get("x"),oy=map.get("y"),oz=map.get("z"),otype=map.get("type");
        return null;
    }

    @Override
    public String toString() {
        return "SerializedLocation:world=" + getWorld() + ":x=" + getX() + ":y=" + getY() + ":z=" + getZ() + ":yaw=" + getYaw() + ":pitch=" + getPitch();
    }
}
