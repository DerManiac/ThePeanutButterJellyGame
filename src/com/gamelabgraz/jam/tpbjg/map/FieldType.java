package com.gamelabgraz.jam.tpbjg.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * @author vinzynth Sep 27, 2014 - 12:06:14 PM
 *
 */
public enum FieldType {
  EMPTY("assets/graphics/ground.png", 64, 64, 0, 0), WALL("assets/graphics/walls.png", 64, 64, 9, 0);

  private Image background;

  private FieldType(String spritePath, int width, int height, int x, int y) {
    SpriteSheet backgroundsprites;
    try {
      backgroundsprites = new SpriteSheet(spritePath, width, height);
      background = backgroundsprites.getSprite(x, y);
    } catch (SlickException e) {
      System.err.println("Error loading background sprite.");
      e.printStackTrace();
    }
  }

  public Image getBackground() {
    return background;
  }
}
