package com.gamelabgraz.jam.tpbjg.map.renderer;

import org.newdawn.slick.Graphics;

import com.gamelabgraz.jam.tpbjg.map.IGameMap;

public class GameMapRenderer {

  public static final int FIELD_HEIGHT = 64;
  public static final int FIELD_WIDTH = 64;

  private Graphics graphics;
  private IGameMap map;

  public GameMapRenderer(Graphics graphics, IGameMap map) {
    this.graphics = graphics;
    this.map = map;
  }

  public void render() {
    // draw images on background
    map.foreachField((x, y, type) -> {
      graphics.drawImage(type.getBackground(), x * FIELD_WIDTH, y * FIELD_HEIGHT);
    });

  }

}
