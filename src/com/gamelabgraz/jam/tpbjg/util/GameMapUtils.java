package com.gamelabgraz.jam.tpbjg.util;

import java.util.stream.IntStream;

import com.gamelabgraz.jam.tpbjg.map.IGameMap;

/**
 * @author vinzynth Sep 27, 2014 - 4:11:41 PM
 *
 */
public final class GameMapUtils {
  private GameMapUtils() {
  }

  public static void printMap(final IGameMap map){
    IntStream.range(0, map.getHeight()).forEach(y -> {
      IntStream.range(0, map.getWidth()).forEach(x -> {
        System.out.print(map.getField(x, y).getSymbol());
      });
      System.out.println();
    });
  }
  
}
