package com.gamelabgraz.jam.tpbjg.items;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;

/**
 * @author vinzynth
 * Sep 27, 2014 - 9:12:41 PM
 *
 */
public class ItemEffectHandler {

  private List<RunningItemEffect> effects = new LinkedList<>();
  private ThePeanutButterJellyGame game;
  
  public ItemEffectHandler(ThePeanutButterJellyGame game) {
    this.game = game;
    }
  
  public void registerEffect(final IItemAction action, final Player player, final long timeout){
    if(timeout <= 0){
      action.endEffect(game, player);
      return;
    }
    effects.add(new RunningItemEffect(action, player, timeout));
  }
  
  public void processDelta(int delta){
    ListIterator<RunningItemEffect> iter = effects.listIterator();
    
    while(iter.hasNext()){
      RunningItemEffect rie = iter.next();
      rie.timeout = rie.timeout - delta;
      if(rie.timeout <= 0){
        rie.action.endEffect(game, rie.player);
        iter.remove();
      }
    }
  }
  
  private static final class RunningItemEffect{
    
    private IItemAction action;
    private long timeout;
    private Player player;

    public RunningItemEffect(final IItemAction action, final Player player, final long timeout) {
      this.action = action;
      this.player = player;
      this.timeout = timeout;
    }
  }
}
