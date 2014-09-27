package com.gamelabgraz.jam.tpbjg.map.implementation;

import java.util.stream.IntStream;

import com.gamelabgraz.jam.tpbjg.map.FieldType;
import com.gamelabgraz.jam.tpbjg.map.IFieldProcessor;
import com.gamelabgraz.jam.tpbjg.map.IGameMap;

/**
 * {@link IGameMap} implementation
 * 
 * @author vinzynth Sep 27, 2014 - 1:47:05 PM
 *
 */
public final class GameMap implements IGameMap {

  final FieldType[][] map;

  /**
   * game Map constructor
   */
  public GameMap(final int id, final int width, final int height) {
    map = new FieldType[width][height];
    // init empty map
    //foreachField((x, y) -> {map[x][y] = Fi});
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getField(int, int)
   */
  @Override
  public FieldType getField(int x, int y) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getHeight()
   */
  @Override
  public int getHeight() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getWidth()
   */
  @Override
  public int getWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#setField(int, int,
   *      com.gamelabgraz.jam.tpbjg.map.FieldType)
   */
  @Override
  public void setField(int x, int y, FieldType type) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getMapId()
   */
  @Override
  public int getMapId() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#foreachField(com.gamelabgraz.jam.tpbjg.map.IFieldProcessor)
   */
  @Override
  public void foreachField(IFieldProcessor proc) {
    IntStream.range(0, getHeight()).forEach(y -> {
      IntStream.range(0, getWidth()).forEach(x -> {
        proc.process(x, y);
      });
    });
  }

}
