package com.gamelabgraz.jam.tpbjg.items;

import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.implementation.CometAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.EarthAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.MagnetAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.TrapAction;

/**
 * @author vinzynth
 * Sep 27, 2014 - 5:23:52 PM
 *
 */
public enum ItemType {
  COMET(new CometAction()),
  EARTH(new EarthAction()),
  MAGNET(new MagnetAction()),
  TRAP(new TrapAction());
  
  private IItemAction action;

  private ItemType(IItemAction action) {
    this.action = action;
  }
  
  public void process(ThePeanutButterJellyGame game){
    action.process(game);
  }
}
