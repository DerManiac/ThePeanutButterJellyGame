package com.gamelabgraz.jam.tpbjg.map.xml.implementaion;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.gamelabgraz.jam.tpbjg.items.ItemType;

/**
 * @author vinzynth
 * Sep 27, 2014 - 7:58:52 PM
 *
 */
@XmlType(name = "item")
public class XMLItemDTO {
  /**
   * x coordinate
   */
  @XmlAttribute(name = "x")
  private int x;
  
  /**
   * y coordinate
   */
  @XmlAttribute(name = "y")
  private int y;
  
  /**
   * field type
   */
  @XmlAttribute(name = "type")
  private ItemType itemtype;
  
  /**
   * default constructor for JAXB
   */
  protected XMLItemDTO(){}
  
  /**
   * XMLFieldDTO constructor
   * @param x
   * @param y
   * @param fieldType
   */
  public XMLItemDTO(final int x, final int y, final ItemType itemtype){
    this.x = x;
    this.y = y;
    this.itemtype = itemtype;
  }


  /**
   * @return the x
   */
  public int getX() {
    return x;
  }


  /**
   * @return the y
   */
  public int getY() {
    return y;
  }


  /**
   * @return the itemType
   */
  public ItemType getItemType() {
    return itemtype;
  }
  
}
