package com.gamelabgraz.jam.tpbjg.items;

import com.gamelabgraz.jam.tpbjg.map.IGameMap;

/**
 * @author vinzynth Sep 27, 2014 - 5:32:50 PM
 *
 */ 
public interface IItemGenerator {

  public Item generateRandomItem(final int x, final int y);

  public Item generateRandomTrap(final int x, final int y);

  public Item generateRandomItem(IGameMap game);

  public Item generateRandomTrap(IGameMap game);

  public Item generateRandomStartItem(IGameMap game);
}
