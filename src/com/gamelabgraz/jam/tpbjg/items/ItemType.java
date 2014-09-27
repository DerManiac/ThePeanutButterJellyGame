package com.gamelabgraz.jam.tpbjg.items;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.implementation.CometAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.EarthAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.MagnetAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.TrapAction;

/**
 * @author vinzynth Sep 27, 2014 - 5:23:52 PM
 *
 */
public enum ItemType {
  COMET(false, new CometAction()),
  EARTH(false, new EarthAction()),
  MAGNET(false, new MagnetAction()),

  // Trap trigger
  TRAP(true, new TrapAction()),

  // Multiple Trap
  TRAPTRAP(false, new MultipleItemAction(5, new TrapAction())),

  // Traps
  FREEZE(true, new TrapAction());

  private boolean isTrapTrigger;
  private IItemAction[] actions;

  private ItemType(boolean isTrap, IItemAction... actions) {
    this.isTrapTrigger = isTrap;
    this.actions = actions;
  }

  public void process(ThePeanutButterJellyGame game) {
    for (IItemAction iItemAction : actions)
      iItemAction.process(game);
  }

  public static Collection<ItemType> getNonTraps() {
    return Arrays.stream(ItemType.values()).filter(it -> !it.isTrapTrigger).collect(Collectors.toList());
  }

  public static Collection<ItemType> getTraps() {
    return Arrays.stream(ItemType.values()).filter(it -> it.isTrapTrigger).collect(Collectors.toList());
  }

  public static ItemType getRandomTrap() {
    Collection<ItemType> traps = getTraps();
    return traps.toArray(new ItemType[traps.size()])[Rnd.nextInt(traps.size())];
  }
}
