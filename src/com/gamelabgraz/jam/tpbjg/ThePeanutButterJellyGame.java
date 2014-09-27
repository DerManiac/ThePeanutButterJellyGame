package com.gamelabgraz.jam.tpbjg;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.config.TPBJGConfig;
import com.gamelabgraz.jam.tpbjg.items.Item;
import com.gamelabgraz.jam.tpbjg.items.implementation.ItemGenerator;
import com.gamelabgraz.jam.tpbjg.map.FieldType;
import com.gamelabgraz.jam.tpbjg.map.IGameMap;
import com.gamelabgraz.jam.tpbjg.map.implementation.SampleGameMapFactory;
import com.gamelabgraz.jam.tpbjg.map.renderer.GameMapRenderer;

public class ThePeanutButterJellyGame extends BasicGame {

  private int gameSpeed = 10;

  private ArrayList<Player> players = new ArrayList<Player>();

  private GameMapRenderer gameMapRenderer;
  private IGameMap gameMap;

  private ArrayList<Item> itemsOnMap;

  private int itemSpawnTimer;

  private static final int P1_START_X = 0;
  private static final int P1_START_Y = 0;
  private static final int P2_START_X = 100;
  private static final int P2_START_Y = 100;

  public ThePeanutButterJellyGame() {
    super("The Peanut Butter Jelly Game");
  }

  @Override
  public void render(GameContainer container, Graphics graphics) throws SlickException {
    gameMapRenderer.render();
    players.forEach(Player::render);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setMaximumLogicUpdateInterval(gameSpeed);
    container.setMinimumLogicUpdateInterval(gameSpeed);
    container.setVSync(true);

    Player p1 = new Player(container, this, 1, false);
    Player p2 = new Player(container, this, 2, false);
    p1.setX(P1_START_X);
    p1.setY(P1_START_Y);
    p2.setX(P2_START_X);
    p2.setY(P2_START_Y);
    players.add(p1);
    players.add(p2);

    // Load sample map
    SampleGameMapFactory factory = new SampleGameMapFactory();
    IGameMap sample_map = factory.getGameMap(0);
    gameMapRenderer = new GameMapRenderer(sample_map);
    gameMap = sample_map;

    itemsOnMap = new ArrayList<Item>();
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    players.forEach(p -> p.move(delta));

    itemSpawnTimer += delta;
    if (itemSpawnTimer > TPBJGConfig.ITEM_SPAWN_TIME) {
      ArrayList<int[]> empty_fields = new ArrayList<int[]>();
      gameMap.foreachField((x, y, type) -> {
        if (type == FieldType.EMPTY) {
          boolean field_is_free = true;
          for (Item current_item : itemsOnMap) {
            if (current_item.getX() == x && current_item.getY() == y) {
              field_is_free = false;
            }
          }
          if (field_is_free) {
            empty_fields.add(new int[] { x, y });
          }
        }
      });

      int spawn_index = Rnd.nextInt(empty_fields.size());
      int[] spawn_location = empty_fields.get(spawn_index);
      Item item_to_spawn = ItemGenerator.getInstance().generateRandomItem(spawn_location[0], spawn_location[1]);
      itemsOnMap.add(item_to_spawn);
    }
  }

  public void controllerButtonPressed(int controller, int button) {
    if (button == Controls.GAMEPAD_START) {
      for (Player p : players) {
        if (!p.getControls().isUseGamepad()) {
          for (Player pr : players)
            if (pr.getControls().getGamepadNumber() == controller)
              return;
          p.getControls().setUseGamepad(true);
          p.getControls().setGamepadNumber(controller);
          System.out.println("Player " + players.indexOf(p) + " registered as gamepad " + controller);
          break;
        }
      }
    }
  }

  public Collection<Player> getPlayers() {
    return players;
  }

  public IGameMap getMap() {
    return gameMap;
  }

  public static void main(String[] args) {
    try {
      ThePeanutButterJellyGame game = new ThePeanutButterJellyGame();
      AppGameContainer container = new AppGameContainer(game, 800, 600, false);
      container.start();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

}
