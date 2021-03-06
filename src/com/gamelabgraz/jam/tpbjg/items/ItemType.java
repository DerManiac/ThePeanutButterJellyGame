package com.gamelabgraz.jam.tpbjg.items;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.implementation.BrickAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.CometAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.EarthAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.FreezeAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.MagnetAction;
import com.gamelabgraz.jam.tpbjg.items.implementation.TrapAction;

/**
 * @author vinzynth Sep 27, 2014 - 5:23:52 PM
 *
 */
public enum ItemType {
  COMET(false, "assets/graphics/comet.png", 64, 64, 5000, new CometAction()), //
  EARTH(false, "assets/graphics/planet.png", 64, 64, 5000, new EarthAction()), //
  BRICK(false, "assets/graphics/brick.png", 64, 64, 5000, new BrickAction()), //
  MAGNET(false, "assets/graphics/magnet_single.png", 64, 64, 0, new MagnetAction()), //

  // Multiple Trap
  TRAPTRAP(false, "assets/graphics/grey_block.png", 64, 64, 0, new MultipleItemAction(5, new TrapAction())),
  // Traps
  FREEZE(true, "assets/graphics/ice_field.png", 64, 64, 5000, new FreezeAction());
  private boolean isTrapTrigger;
  private IItemAction[] actions;
  private Animation animation;
  private int duration;

  private ItemType(boolean isTrap, String spritePath, int width, int height, int duration, IItemAction... actions) {
    this.isTrapTrigger = isTrap;
    this.duration = duration;
    this.actions = actions;

    try {
      animation = new Animation(new SpriteSheet(spritePath, width, height), 150);
    } catch (SlickException | RuntimeException e) {
      System.err.println("Error loading background sprite.");
      e.printStackTrace();
    }
  }

  public Animation getImage() {
    return animation;
  }

  public IItemAction[] getItemActions() {
    return actions;
  }

  /**
   * @return the duration
   */
  public int getDuration() {
    return duration;
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
