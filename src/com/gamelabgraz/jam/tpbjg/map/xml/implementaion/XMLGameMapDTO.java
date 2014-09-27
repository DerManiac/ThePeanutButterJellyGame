package com.gamelabgraz.jam.tpbjg.map.xml.implementaion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.gamelabgraz.jam.tpbjg.map.IGameMap;

/**
 * @author vinzynth Sep 27, 2014 - 12:34:12 PM
 *
 */
@XmlRootElement(name = "gameMap")
@XmlType(name = "gameMap")
@XmlAccessorType(XmlAccessType.NONE)
public class XMLGameMapDTO {

  /**
   * map id
   */
  @XmlAttribute(name = "id")
  private int id;

  /**
   * map height
   */
  @XmlAttribute(name = "height")
  private int height;

  /**
   * map width
   */
  @XmlAttribute(name = "width")
  private int width;

  /**
   * map fields
   */
  @XmlElementWrapper(name = "fields")
  @XmlElement(name = "field")
  private List<XMLFieldDTO> fields;

  /**
   * protected default constructor for JAXB
   */
  protected XMLGameMapDTO() {
  }

  /**
   * constructor for DTO class for a {@link XMLGameMapProvider}
   * 
   * @param map
   *          given {@link IGameMap}
   */
  public XMLGameMapDTO(final IGameMap map) {
    // Build up fields
    this.fields = new ArrayList<XMLFieldDTO>(map.getHeight() * map.getWidth());
    IntStream.range(0, map.getHeight()).forEach(y -> {
      IntStream.range(0, getWidth()).forEach(x -> {
        this.fields.add(new XMLFieldDTO(x, y, map.getField(x, y)));
      });
    });

    // set width
    this.width = map.getWidth();

    // set height
    this.height = map.getHeight();

    // set id
    this.id = map.getMapId();
  }

  /**
   * @return the heigth
   */
  public int getHeight() {
    return height;
  }

  /**
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return the fields
   */
  public List<XMLFieldDTO> getFields() {
    return fields;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }
}
