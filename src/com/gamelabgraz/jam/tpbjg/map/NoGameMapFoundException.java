package com.gamelabgraz.jam.tpbjg.map;

/**
 * @author vinzynth Sep 27, 2014 - 3:14:06 PM
 *
 */
public class NoGameMapFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1012756526687942088L;
  
  public NoGameMapFoundException(int mapId) {
    super("No Game Map for ID:" + mapId + " found!");
  }
}
