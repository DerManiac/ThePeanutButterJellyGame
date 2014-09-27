package com.gamelabgraz.jam.tpbjg.map;

import com.gamelabgraz.jam.tpbjg.config.TPBJGConfig;

/**
 * Factory class for game map objects
 * 
 * @author vinzynth Sep 27, 2014 - 11:56:54 AM
 *
 */
public interface IGameMapFactory {
  
  /**
   * get {@link IGameMap} by map id loaded from
   * {@link TPBJGConfig#LEVEL_FILE_DIRECTORY}
   * 
   * @param mapId
   *          given mapId
   * @return {@link IGameMap} instance
   */
  IGameMap getGameMap(final int mapId) throws NoGameMapFoundException;

}