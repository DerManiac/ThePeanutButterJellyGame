package com.gamelabgraz.jam.tpbjg.items;

import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;

/**
 * @author vinzynth
 * Sep 27, 2014 - 5:36:59 PM
 *
 */
public class Item {
  
  private final ItemType type;

  public Item(final ItemType type) {
    this.type = type;
  }
  
  public void processEffect(ThePeanutButterJellyGame game){
    this.type.process(game);
  } 
}
