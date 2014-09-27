package com.gamelabgraz.jam.tpbjg.items.implementation;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.items.IItemGenerator;
import com.gamelabgraz.jam.tpbjg.items.Item;
import com.gamelabgraz.jam.tpbjg.items.ItemType;

/**
 * @author vinzynth Sep 27, 2014 - 5:41:03 PM
 *
 */
public class ItemGenerator implements IItemGenerator {
  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemGenerator#generateRandomItem(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public Item generateRandomItem(final int x, final int y) {
    return new Item(x, y, ItemType.values()[Rnd.nextInt(ItemType.values().length)]);
  }

  private static class SingletonHolder {
    private static ItemGenerator instance = new ItemGenerator();
  }

  public static ItemGenerator getInstance() {
    return SingletonHolder.instance;
  }

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.items.IItemGenerator#generateRandomTrap(int, int)
   */
  @Override
  public Item generateRandomTrap(int x, int y) {
    return new Item(x, y, ItemType.getRandomTrap());
  }
}
