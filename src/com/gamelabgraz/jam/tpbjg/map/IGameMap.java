package com.gamelabgraz.jam.tpbjg.map;

import java.util.Collection;
import java.util.List;

import com.gamelabgraz.jam.tpbjg.items.Item;

/**
 * @author vinzynth Sep 27, 2014 - 11:54:05 AM
 *
 */
public interface IGameMap {

  /**
   * returns map identifier
   */
  int getMapId();

  /**
   * Returns field type of field for given coordinates
   * 
   * @param x
   *          x coordinate (width)
   * @param y
   *          y coordinate (height)
   * @return field type of field for given coordinates
   * 
   * @throws ArrayIndexOutOfBoundsException
   *           if coordinates are less than 0 or equal or greater map size (
   *           {@link #getHeight()} | {@link #getWidth()}).
   */
  FieldType getField(final int x, final int y);

  /**
   * returns map height (y coordinate)
   * 
   * @return map height
   */
  int getHeight();

  /**
   * return map width (x coodinate)
   * 
   * @return map width
   */
  int getWidth();

  /**
   * Sets the field at given coordinates to given type.
   * 
   * @param x
   *          x coordinate of target field
   * @param y
   *          y coordinte of target field
   * @param type
   *          new field type
   */
  void setField(final int x, final int y, final FieldType type);

  /**
   * returns if given field is revealed or not
   * 
   * @param x
   *          given field x coordinate
   * @param y
   *          given field y coordinate
   * @return if field is revealed(true), or not(false)
   */
  boolean isRevealed(final int x, final int y);

  /**
   * Set reveal state of given field
   * 
   * @param x
   *          given field x coordinate
   * @param y
   *          given field y coordinate
   * @param revealed
   *          revealed state to set
   */
  void setRevealed(final int x, final int y, final boolean revealed);

  /**
   * Unreveal everything
   */
  void unrevealMap();

  /**
   * Util method for easy iteration over all fields
   * 
   * @param proc
   *          {@link IFieldProcessor} instance, or lambda expression
   */
  void foreachField(final IFieldProcessor proc);

  /**
   * Returns items available at Startup
   * 
   * @return all items available at game Start
   */
  Collection<Item> getStartItems();

  /**
   * Returns player spawn point coordinates
   * 
   * @return all player spawn points
   */
  List<int[]> getPlayerSpawns();

  /**
   * Returns player glass spawn point coordinates
   * 
   * @return all player glass spawn points
   */
  List<int[]> getPlayerGlassSpawns();

  /**
   * Return all items currently on map
   * 
   * @return all items on map
   */
  Collection<Item> getItemsOnMap();
}
