package com.gamelabgraz.jam.tpbjg.items.implementation;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.IItemAction;

public class MagnetAction implements IItemAction {

  private Sound sound = null;

  public MagnetAction() {
    try {
      sound = new Sound("assets/sounds/magnet.wav");
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
    for (Player p : game.getPlayers()) {
      if (p != player) {
        p.removeCrashCharge();

      }
    }

  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#endEffect(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame,
   *      com.gamelabgraz.jam.tpbjg.Player)
   */
  @Override
  public void endEffect(ThePeanutButterJellyGame game, Player effector) {
    // TODO Auto-generated method stub

  }
}
