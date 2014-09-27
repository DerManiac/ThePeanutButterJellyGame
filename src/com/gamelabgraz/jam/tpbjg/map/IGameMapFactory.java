package com.gamelabgraz.jam.tpbjg.map;

import java.io.File;


/**
 * Factory class for game map objects
 * 
 * @author vinzynth Sep 27, 2014 - 11:56:54 AM
 *
 */
public interface IGameMapFactory {

  IGameMap getGameMap(final File targetFile);
  IGameMap getGameMap(final int mapId);
  
}