/**
 * Name: EchoLookup.java Edited: 26 January 2014
 *
 * @version 1.0.0
 */
package com.relicum.hubcontrol;

import org.apache.commons.lang.text.StrLookup;

public class EchoLookup extends StrLookup {

    @Override
    public String lookup(String s) {
        return "Value of variable " + s;
    }
}
