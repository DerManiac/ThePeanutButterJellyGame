package com.gamelabgraz.jam.tpbjg.map;

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
   *        x coordinate of target field    
   * @param y
   *        y coordinte of target field
   * @param type
   *        new field type
   */
  void setField(final int x, final int y, final FieldType type);

  /**
   * Util method for easy iteration over all fields
   * @param proc
   *        {@link IFieldProcessor} instance, or lambda expression
   */
  void foreachField(final IFieldProcessor proc);
}
