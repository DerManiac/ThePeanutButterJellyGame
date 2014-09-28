package com.gamelabgraz.jam.tpbjg;

/**
 * @author christoph Sep 27, 2014 - 8:02:27 PM
 *
 */
public enum FoodType {

  JELLY("Jelly"), PEANUT("Peanut butter");

  private String name;

  /**
   * 
   */
  private FoodType(String name) {
    this.name = name;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
}