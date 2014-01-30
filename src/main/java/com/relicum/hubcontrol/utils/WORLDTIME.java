/**
 * Name: WORLDTIME.java Edited: 30 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol.utils;

/**
 * The enum WORLDTIME.
 */
public enum WORLDTIME {
    /**
     * The DAY.
     */DAY(6000l),
    /**
     * The NIGHT.
     */NIGHT(18000l);


    private final Long fixedTime;


    private WORLDTIME(Long tm) {
        this.fixedTime = tm;
    }


    /**
     * Returns the Time as a Long value
     *
     * @return the long the time as a long value
     */
    public Long asLong() {
        return fixedTime;
    }


}
