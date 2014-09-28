package com.gamelabgraz.jam.tpbjg.map.xml.implementaion;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author snajder Sep 28, 2014
 *
 */
@XmlType(name = "spawn")
public class XMLSpawnDTO {
  /**
   * x coordinate
   */
  @XmlAttribute(name = "x")
  private int x;

  /**
   * y coordinate of glass
   */
  @XmlAttribute(name = "y")
  private int y;

  /**
   * X coordinate of glass
   */
  @XmlAttribute(name = "glassX")
  private int glassX;

  /**
   * y coordinate
   */
  @XmlAttribute(name = "glassY")
  private int glassY;

  /**
   * field type
   */
  @XmlAttribute(name = "player")
  private int player;

  /**
   * default constructor for JAXB
   */
  protected XMLSpawnDTO() {
  }

  /**
   * XMLFieldDTO constructor
   * 
   * @param x
   * @param y
   * @param fieldType
   */
  public XMLSpawnDTO(final int x, final int y, final int glassX, final int glassY, final int player) {
    this.x = x;
    this.y = y;
    this.glassX = glassX;
    this.glassY = glassY;
    this.player = player;
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
   * @return the glass x
   */
  public int getGlassX() {
    return glassX;
  }

  /**
   * @return the glass y
   */
  public int getGlassY() {
    return glassY;
  }

  /**
   * @return the player
   */
  public int getPLayer() {
    return player;
  }

}
