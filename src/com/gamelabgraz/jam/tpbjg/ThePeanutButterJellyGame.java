package com.gamelabgraz.jam.tpbjg;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.gamelabgraz.jam.tpbjg.config.TPBJGConfig;
import com.gamelabgraz.jam.tpbjg.items.Item;
import com.gamelabgraz.jam.tpbjg.items.implementation.ItemGenerator;
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
  private static final int P2_START_X = 500;
  private static final int P2_START_Y = 500;

  public ThePeanutButterJellyGame() {
    super("The Peanut Butter Jelly Game");
  }

  @Override
  public void render(GameContainer container, Graphics graphics) throws SlickException {
    gameMapRenderer.render();
    players.forEach(Player::render);
    itemsOnMap.forEach(Item::render);
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
    gameMap = factory.getGameMap(0);
    gameMapRenderer = new GameMapRenderer(gameMap);

    itemsOnMap = new ArrayList<Item>();
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    players.forEach(p -> p.move(delta));

    itemSpawnTimer += delta;
    if (itemSpawnTimer > TPBJGConfig.ITEM_SPAWN_TIME) {
      itemSpawnTimer = 0;
      Item item_to_spawn = ItemGenerator.getInstance().generateRandomItem(this);
      if (item_to_spawn != null)
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
          System.out.println();
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

  public IGameMap getGameMap() {
    return gameMap;
  }

  public ArrayList<Item> getItemsOnMap() {
    return itemsOnMap;
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
