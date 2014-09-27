package com.gamelabgraz.jam.tpbjg.items.implementation;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.IItemAction;

/**
 * @author vinzynth Sep 27, 2014 - 6:34:14 PM
 *
 */
public class FreezeAction implements IItemAction {

  final float speedfactor = 0.3f;

  float speedDiff;

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#process(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public void startEffect(ThePeanutButterJellyGame game, Player effector) {
    float normalspeed = effector.getSpeed();
    speedDiff = normalspeed * (speedfactor - 1);
    game.getPlayers().forEach(p -> {
      if (p != effector)
        p.setSpeed(effector.getSpeed() + speedDiff);
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
    game.getPlayers().forEach(p -> {
      if (p != effector)
        p.setSpeed(effector.getSpeed() - speedDiff);
    });
  }

}
