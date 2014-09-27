package com.gamelabgraz.jam.tpbjg.map.implementation;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.map.FieldType;
import com.gamelabgraz.jam.tpbjg.map.IGameMap;
import com.gamelabgraz.jam.tpbjg.map.IGameMapFactory;
import com.gamelabgraz.jam.tpbjg.map.NoGameMapFoundException;

/**
 * @author vinzynth
 * Sep 27, 2014 - 3:19:50 PM
 *
 */
public class SampleGameMapFactory implements IGameMapFactory {

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMapFactory#getGameMap(int)
   */
  @Override
  public IGameMap getGameMap(int mapId) throws NoGameMapFoundException {
    IGameMap gm = new GameMap(mapId, 20, 30);
    gm.foreachField((x,y,type) -> gm.setField(x, y, FieldType.values()[Rnd.nextInt(FieldType.values().length)]));
    return gm;
  }

}
