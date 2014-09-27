package com.gamelabgraz.jam.tpbjg.items.implementation;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.IItemAction;

public class CometAction implements IItemAction {

  final float speedfactor = 2;
  
  float speedDiff;
  
  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#process(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public void startEffect(ThePeanutButterJellyGame game, Player effector) {
    float normalspeed = effector.getSpeed();
    speedDiff = normalspeed * (speedfactor - 1);
    effector.setSpeed(effector.getSpeed() + speedDiff);
  }

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#endEffect(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame, com.gamelabgraz.jam.tpbjg.Player)
   */
  @Override
  public void endEffect(ThePeanutButterJellyGame game, Player effector) {
    effector.setSpeed(effector.getSpeed() - speedDiff);
  }

}
