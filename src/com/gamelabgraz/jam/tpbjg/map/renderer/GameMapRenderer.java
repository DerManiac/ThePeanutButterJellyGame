package com.gamelabgraz.jam.tpbjg.map.renderer;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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

    try {
      int[] spawn = map.getPlayerSpawns().get(0);
      new Image("assets/graphics/toast_peanutbutter.png").draw(spawn[0] * FIELD_WIDTH, spawn[1] * FIELD_HEIGHT);
      spawn = map.getPlayerSpawns().get(1);
      new Image("assets/graphics/toast_jelly.png").draw(spawn[0] * FIELD_WIDTH, spawn[1] * FIELD_HEIGHT);
    } catch (SlickException e) {
      System.err.println("Cannot render base image");
      e.printStackTrace();
    }

  }
}
