package com.gamelabgraz.jam.tpbjg.items;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;

/**
 * @author vinzynth Sep 27, 2014 - 5:36:59 PM
 *
 */
public class Item {

  private final ItemType type;
  private int x;
  private int y;

  public Item(final int x, final int y, final ItemType type) {
    this.x = x;
    this.y = y;
    this.type = type;
  }

  public void processEffect(ThePeanutButterJellyGame game, Player player) {
    this.type.process(game, player);
  }

  /**
   * @return the type
   */
  public ItemType getType() {
    return type;
  }

  /**
   * @return the x
   */
  public int getX() {
    return x;
  }

  /**
   * @return the y
   */
  public int getY() {
    return y;
  }
}
