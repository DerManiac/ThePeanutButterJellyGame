package com.gamelabgraz.jam.tpbjg.items.implementation;

import java.util.ArrayList;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.items.IItemGenerator;
import com.gamelabgraz.jam.tpbjg.items.Item;
import com.gamelabgraz.jam.tpbjg.items.ItemType;
import com.gamelabgraz.jam.tpbjg.map.FieldType;
import com.gamelabgraz.jam.tpbjg.map.IGameMap;

/**
 * @author vinzynth Sep 27, 2014 - 5:41:03 PM
 *
 */
public class ItemGenerator implements IItemGenerator {
  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemGenerator#generateRandomItem(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public Item generateRandomItem(final int x, final int y) {
    return new Item(x, y, ItemType.values()[Rnd.nextInt(ItemType.values().length)]);
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemGenerator#generateRandomTrap(int,
   *      int)
   */
  @Override
  public Item generateRandomTrap(int x, int y) {
    return new Item(x, y, ItemType.getRandomTrap());
  }

  @Override
  public Item generateRandomTrap(IGameMap game) {
    int[] coordinates = findRandomEmptyCoordinates(game);
    if (coordinates != null) {
      return generateRandomTrap(coordinates[0], coordinates[1]);
    }
    return null;
  }

  @Override
  public Item generateRandomItem(IGameMap game) {
    int[] coordinates = findRandomEmptyCoordinates(game);
    if (coordinates != null) {
      return generateRandomItem(coordinates[0], coordinates[1]);
    }
    return null;
  }

  private int[] findRandomEmptyCoordinates(IGameMap game) {
    ArrayList<int[]> empty_fields = new ArrayList<int[]>();
    game.foreachField((x, y, type) -> {
      if (type == FieldType.EMPTY) {
        boolean field_is_free = true;
        for (Item current_item : game.getItemsOnMap()) {
          if (current_item.getX() == x && current_item.getY() == y) {
            field_is_free = false;
          }
        }
        if (field_is_free) {
          empty_fields.add(new int[] { x, y });
        }
      }
    });

    if (empty_fields.size() > 0) {
      int spawn_index = Rnd.nextInt(empty_fields.size());
      int[] spawn_location = empty_fields.get(spawn_index);
      return spawn_location;
    }
    return null;
  }

  private static class SingletonHolder {
    private static ItemGenerator instance = new ItemGenerator();
  }

  public static ItemGenerator getInstance() {
    return SingletonHolder.instance;
  }

  /**
   * {@inheritDoc}
   * @see com.gamelabgraz.jam.tpbjg.items.IItemGenerator#generateRandomStartItem(com.gamelabgraz.jam.tpbjg.map.IGameMap)
   */
  @Override
  public Item generateRandomStartItem(IGameMap game) {
    Item[] items = game.getStartItems().toArray(new Item[game.getStartItems().size()]);
    for (int i = 0; i < items.length; i++) {
      for (Item item : game.getItemsOnMap()) {
        if(item.getX() != items[i].getX() && item.getY() != items[i].getY())
          return new Item(item.getX(), item.getY(), item.getType());
      }
    }
    return null;
  }
}
