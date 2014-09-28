package com.gamelabgraz.jam.tpbjg.map.implementation;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.items.implementation.ItemGenerator;
import com.gamelabgraz.jam.tpbjg.map.FieldType;
import com.gamelabgraz.jam.tpbjg.map.IGameMap;
import com.gamelabgraz.jam.tpbjg.map.IGameMapFactory;
import com.gamelabgraz.jam.tpbjg.map.NoGameMapFoundException;

/**
 * @author vinzynth Sep 27, 2014 - 3:19:50 PM
 *
 */
public class SampleGameMapFactory implements IGameMapFactory {

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.IGameMapFactory#getGameMap(int)
   */
  @Override
  public IGameMap getGameMap(int mapId) throws NoGameMapFoundException {
    GameMap gm = new GameMap(mapId, 20, 30, Collections.emptyList(), Arrays.asList(new int[][] { { 0, 0 }, { 19, 29 } }));
    gm.foreachField((x, y, type) -> gm.setField(x, y, FieldType.values()[Rnd.nextInt(FieldType.values().length)]));
    gm.setStartItems(IntStream.range(0, 10).mapToObj(i -> ItemGenerator.getInstance().generateRandomItem(gm)).collect(Collectors.toList()));
    return gm;
  }

}
