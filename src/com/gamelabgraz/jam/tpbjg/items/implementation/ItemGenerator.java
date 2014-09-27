package com.gamelabgraz.jam.tpbjg.items.implementation;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.IItemGenerator;
import com.gamelabgraz.jam.tpbjg.items.Item;
import com.gamelabgraz.jam.tpbjg.items.ItemType;

/**
 * @author vinzynth
 * Sep 27, 2014 - 5:41:03 PM
 *
 */
public class ItemGenerator implements IItemGenerator {
  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.items.IItemGenerator#generateRandomItem(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public void generateRandomItem(ThePeanutButterJellyGame game) {
    Item item = new Item(ItemType.values()[Rnd.nextInt(ItemType.values().length)]);
    
    //FIXME add items to peanutbuttergameclass
  }
}
