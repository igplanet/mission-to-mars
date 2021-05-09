/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.util;

/**
 *
 * @author oghomwen.aigbedion
 */
public enum UnitSearchProperty {
    TITLE("title"), REGION("region");

    private final String name;

    private UnitSearchProperty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
