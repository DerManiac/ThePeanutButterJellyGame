package com.gamelabgraz.jam.tpbjg.map.renderer;

import com.gamelabgraz.jam.tpbjg.map.IGameMap;

public class GameMapRenderer {

  public static final int FIELD_HEIGHT = 64;
  public static final int FIELD_WIDTH = 64;

  private IGameMap map;

  public GameMapRenderer(IGameMap map) {
    this.map = map;
  }

  public void render() {
    // draw images on background
    map.foreachField((x, y, type) -> {
      type.getBackground().draw(x * FIELD_WIDTH, y * FIELD_HEIGHT);
    });

  }

}
