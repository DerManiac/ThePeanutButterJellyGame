package com.gamelabgraz.jam.tpbjg.items.implementation;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.IItemAction;

public class BrickAction implements IItemAction {

  private Sound sound = null;

  public BrickAction() {
    try {
      sound = new Sound("assets/sounds/pickup_brick.wav");
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
  public void startEffect(ThePeanutButterJellyGame game, Player effector) {
    effector.addBrick();
    if (sound != null)
      sound.play();
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#endEffect(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame,
   *      com.gamelabgraz.jam.tpbjg.Player)
   */
  @Override
  public void endEffect(ThePeanutButterJellyGame game, Player effector) {

  }

}
