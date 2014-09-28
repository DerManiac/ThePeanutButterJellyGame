package com.gamelabgraz.jam.tpbjg.map.xml.implementaion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
   * map items
   */
  @XmlElementWrapper(name = "items")
  @XmlElement(name = "item")
  private List<XMLItemDTO> items;

  /**
   * map playerSpawns
   */
  @XmlElementWrapper(name = "spawns")
  @XmlElement(name = "spawn")
  private List<XMLSpawnDTO> spawns;

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
    map.foreachField((x, y, type) -> this.fields.add(new XMLFieldDTO(x, y, type)));

    this.items = map.getStartItems().stream().map(i -> new XMLItemDTO(i.getX(), i.getY(), i.getType())).collect(Collectors.toList());

    // set spawn points
    for (int i = 0; i < map.getPlayerSpawns().size(); i++) {
      this.spawns.add(new XMLSpawnDTO(map.getPlayerSpawns().get(i)[0], map.getPlayerSpawns().get(i)[1],
          map.getPlayerGlassSpawns().get(i)[0], map.getPlayerGlassSpawns().get(i)[1], i));
    }

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
   * @return the items
   */
  public List<XMLItemDTO> getItems() {
    return items;
  }

  /**
   * @return the player spawns
   */
  public List<XMLSpawnDTO> getPlayerSpawns() {
    return spawns;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }
}
