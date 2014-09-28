package com.gamelabgraz.jam.tpbjg.items.implementation;

import java.util.Objects;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.IItemAction;
import com.gamelabgraz.jam.tpbjg.items.Item;

/**
 * @author vinzynth Sep 27, 2014 - 6:22:37 PM
 *
 */
public class TrapAction implements IItemAction {

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#process(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public void startEffect(ThePeanutButterJellyGame game, Player player) {

    Item itemToAdd = null;
    if (Rnd.nextBoolean())
      itemToAdd = ItemGenerator.getInstance().generateRandomItem(game.getGameMap());
    if (Objects.nonNull(itemToAdd))
      game.getGameMap().getItemsOnMap().add(itemToAdd);
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
