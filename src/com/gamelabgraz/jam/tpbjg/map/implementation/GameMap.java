package com.gamelabgraz.jam.tpbjg.map.implementation;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.stream.IntStream;

import com.gamelabgraz.jam.tpbjg.items.Item;
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
  
  final Collection<Item> itemsOnMap;
  Collection<Item> itemsAtStart;
  final BitSet revealed;
  final FieldType[][] map;
  private int width;
  private int height;
  private int id;

  /**
   * game Map constructor
   */
  public GameMap(final int id, final int width, final int height, final Collection<Item> items) {
    this.id = id;
    this.width = width;
    this.height = height;
    this.itemsAtStart = items;
    this.itemsOnMap = new ArrayList<Item>(items);
    map = new FieldType[width][height];
    // init empty map
    this.foreachField((x, y, type) -> {
      map[x][y] = FieldType.EMPTY;
    });
    this.revealed = new BitSet(this.width * this.height);
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getField(int, int)
   */
  @Override
  public FieldType getField(int x, int y) {
    return map[x][y];
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getHeight()
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getWidth()
   */
  @Override
  public int getWidth() {
     return width;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#setField(int, int,
   *      com.gamelabgraz.jam.tpbjg.map.FieldType)
   */
  @Override
  public void setField(int x, int y, FieldType type) {
     map[x][y] = type;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getMapId()
   */
  @Override
  public int getMapId() {
    return id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#foreachField(com.gamelabgraz.jam.tpbjg.map.IFieldProcessor)
   */
  @Override
  public void foreachField(IFieldProcessor proc) {
    IntStream.range(0, getHeight()).forEach(y -> {
      IntStream.range(0, getWidth()).forEach(x -> {
        proc.process(x, y, getField(x, y));
      });
    });
  }
  
  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#isRevealed(int, int)
   */
  @Override
  public boolean isRevealed(int x, int y) {
    return this.revealed.get(y*width + x);
  }

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#setRevealed(int, int, boolean)
   */
  @Override
  public void setRevealed(int x, int y, boolean revealed) {
    this.revealed.set(y*width + x, revealed);
  }

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#unrevealMap()
   */
  @Override
  public void unrevealMap() {
    this.revealed.clear();
  }

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getStartItems()
   */
  @Override
  public Collection<Item> getStartItems() {
    return itemsAtStart;
  }

  /**
   * package private for {@link SampleGameMapFactory}
   * @param startItems
   */
  void setStartItems(Collection<Item> startItems){
    this.itemsAtStart = startItems;
  }
  
  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMap#getItemsOnMap()
   */
  @Override
  public Collection<Item> getItemsOnMap() {
    return itemsOnMap;
  }
}
