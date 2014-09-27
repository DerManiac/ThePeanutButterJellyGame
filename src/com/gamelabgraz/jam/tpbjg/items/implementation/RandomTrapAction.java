package com.gamelabgraz.jam.tpbjg.items.implementation;

import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.IItemAction;
import com.gamelabgraz.jam.tpbjg.items.ItemType;

/**
 * @author vinzynth
 * Sep 27, 2014 - 6:46:59 PM
 *
 */
public class RandomTrapAction implements IItemAction {

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#process(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public void process(ThePeanutButterJellyGame game) {
    ItemType.getRandomTrap().process(game);
  }
}
