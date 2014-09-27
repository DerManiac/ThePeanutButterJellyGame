package com.gamelabgraz.jam.tpbjg.items;

import java.util.Arrays;

import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;

/**
 * @author vinzynth Sep 27, 2014 - 6:34:52 PM
 *
 */
public class MultipleItemAction implements IItemAction {

  private IItemAction[] toMultiply;
  private int count;

  public MultipleItemAction(int count, IItemAction... toMultiply) {
    this.count = count;
    this.toMultiply = toMultiply;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#process(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public void process(ThePeanutButterJellyGame game) {
    Arrays.stream(toMultiply).forEach(a -> {
     for (int i = 0; i < count; i++) {
      a.process(game);
    }});
  }
}
