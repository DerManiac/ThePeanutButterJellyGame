package com.gamelabgraz.jam.tpbjg.items;

import java.util.Arrays;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;

/**
 * @author vinzynth Sep 27, 2014 - 6:34:52 PM
 *
 */
public class MultipleItemAction implements IItemAction {

  private IItemAction[] toMultiply;
  private int count;

  Sound sound = null;

  public MultipleItemAction(int count, IItemAction... toMultiply) {
    this.count = count;
    this.toMultiply = toMultiply;
    try {
      sound = new Sound("assets/sounds/switch_trap.wav");
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#process(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public void startEffect(ThePeanutButterJellyGame game, Player player) {
    if (sound != null)
      sound.play();
    Arrays.stream(toMultiply).forEach(a -> {
      for (int i = 0; i < count; i++) {
        a.startEffect(game, player);
      }
    });
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#endEffect(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame,
   *      com.gamelabgraz.jam.tpbjg.Player)
   */
  @Override
  public void endEffect(ThePeanutButterJellyGame game, Player effector) {
    Arrays.stream(toMultiply).forEach(a -> {
      for (int i = 0; i < count; i++) {
        a.endEffect(game, effector);
      }
    });
  }
}
