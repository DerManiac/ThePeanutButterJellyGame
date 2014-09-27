package com.gamelabgraz.jam.tpbjg.items;

import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;

/**
 * @author vinzynth Sep 27, 2014 - 5:32:50 PM
 *
 */
public interface IItemGenerator {

  public Item generateRandomItem(final int x, final int y);

  public Item generateRandomTrap(final int x, final int y);

  public Item generateRandomItem(ThePeanutButterJellyGame game);

  public Item generateRandomTrap(ThePeanutButterJellyGame game);

}
