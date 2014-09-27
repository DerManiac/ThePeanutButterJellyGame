package com.gamelabgraz.jam.tpbjg.map.implementation;

import static java.util.Objects.isNull;

import java.io.IOException;
import java.util.HashMap;

import com.gamelabgraz.jam.tpbjg.config.TPBJGConfig;
import com.gamelabgraz.jam.tpbjg.map.IGameMap;
import com.gamelabgraz.jam.tpbjg.map.IGameMapFactory;
import com.gamelabgraz.jam.tpbjg.map.NoGameMapFoundException;
import com.gamelabgraz.jam.tpbjg.map.xml.IXMLGameMapProvider;
import com.gamelabgraz.jam.tpbjg.map.xml.implementaion.XMLGameMapProvider;

/**
 * @author vinzynth Sep 27, 2014 - 3:02:34 PM
 *
 */
public class GameMapFactory implements IGameMapFactory {

  private final HashMap<Integer, IGameMap> cachedMapsById;
  
  private final IXMLGameMapProvider xmlProvider;
  
  private void loadMaps(){
    try {
      xmlProvider.provide(TPBJGConfig.LEVEL_FILE_DIRECTORY).forEach(g -> {cachedMapsById.put(g.getMapId(), g);  System.out.println(g.getMapId());});
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMapFactory#getGameMap(int)
   */
  @Override
  public IGameMap getGameMap(int mapId) throws NoGameMapFoundException{
    IGameMap returnMe = cachedMapsById.get(mapId);
    if(isNull(returnMe))
      throw new NoGameMapFoundException(mapId);
    return returnMe;
  }

  private GameMapFactory() {
    cachedMapsById = new HashMap<>();
    xmlProvider = new XMLGameMapProvider();
    loadMaps();
  }

  private static class SingletonHolder {
    private static final GameMapFactory instance = new GameMapFactory();
  }

  public static final IGameMapFactory getInstance() {
    return SingletonHolder.instance;
  }
}
