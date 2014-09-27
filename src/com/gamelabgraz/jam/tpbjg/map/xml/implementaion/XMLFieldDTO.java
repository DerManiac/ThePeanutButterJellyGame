package com.gamelabgraz.jam.tpbjg.map.xml.implementaion;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.gamelabgraz.jam.tpbjg.map.FieldType;

/**
 * @author vinzynth
 * Sep 27, 2014 - 12:35:47 PM
 *
 */
@XmlType(name = "field")
public class XMLFieldDTO {
  
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
  private FieldType fieldType;
  
  /**
   * default constructor for JAXB
   */
  protected XMLFieldDTO(){}
  
  /**
   * XMLFieldDTO constructor
   * @param x
   * @param y
   * @param fieldType
   */
  public XMLFieldDTO(final int x, final int y, final FieldType fieldType){
    this.x = x;
    this.y = y;
    this.fieldType = fieldType;
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
   * @return the fieldType
   */
  public FieldType getFieldType() {
    return fieldType;
  }
  
  
}
