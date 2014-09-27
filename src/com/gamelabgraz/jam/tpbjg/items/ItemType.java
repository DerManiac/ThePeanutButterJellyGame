package com.gamelabgraz.jam.tpbjg.items;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.Player;
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
  COMET(false, "assets/graphics/baseflag.png", 64, 64, 0, 0, new CometAction()), //
  EARTH(false, "assets/graphics/baseflag.png", 64, 64, 1, 0, new EarthAction()), //
  MAGNET(false, "assets/graphics/baseflag.png", 64, 64, 2, 0, new MagnetAction()), //

  // Trap trigger
  TRAP(true, "assets/graphics/baseflag.png", 64, 64, 3, 0, new TrapAction()),

  // Multiple Trap
  TRAPTRAP(false, "assets/graphics/baseflag.png", 64, 64, 3, 0, new MultipleItemAction(5, new TrapAction())),

  // Traps
  FREEZE(true, "assets/graphics/baseflag.png", 64, 64, 3, 0, new TrapAction());

  private boolean isTrapTrigger;
  private IItemAction[] actions;
  private Image image;

  private ItemType(boolean isTrap, String spritePath, int width, int height, int x, int y, IItemAction... actions) {
    this.isTrapTrigger = isTrap;
    this.actions = actions;

    try {
      image = new SpriteSheet(spritePath, width, height).getSprite(x, y);
    } catch (SlickException e) {
      System.err.println("Error loading background sprite.");
      e.printStackTrace();
    }
  }

  public Image getImage() {
    return image;
  }

  public void process(ThePeanutButterJellyGame game, Player player) {
    for (IItemAction iItemAction : actions)
      iItemAction.startEffect(game, player);
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
