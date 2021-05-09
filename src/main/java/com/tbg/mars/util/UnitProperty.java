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
public enum UnitProperty {
    TITLE("title"), REGION("region"), PRICE("price"), SCORE("score");

    private final String name;

    private UnitProperty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
