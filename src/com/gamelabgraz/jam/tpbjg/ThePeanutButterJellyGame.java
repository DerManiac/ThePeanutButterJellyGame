package com.gamelabgraz.jam.tpbjg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import at.chrl.nutils.Rnd;

import com.gamelabgraz.jam.tpbjg.config.TPBJGConfig;
import com.gamelabgraz.jam.tpbjg.items.Item;
import com.gamelabgraz.jam.tpbjg.items.ItemEffectHandler;
import com.gamelabgraz.jam.tpbjg.items.implementation.ItemGenerator;
import com.gamelabgraz.jam.tpbjg.map.IGameMap;
import com.gamelabgraz.jam.tpbjg.map.implementation.GameMapFactory;
import com.gamelabgraz.jam.tpbjg.map.renderer.GameMapRenderer;

public class ThePeanutButterJellyGame extends BasicGame {

  private int gameSpeed = 10;

  private ArrayList<Player> players = new ArrayList<Player>();

  private GameMapRenderer gameMapRenderer;
  private IGameMap gameMap;
  private ItemEffectHandler itemEffectHandler;

  private int itemSpawnTimer;

  private static final int P1_START_X = 0;
  private static final int P1_START_Y = 0;
  private static final int P2_START_X = 500;
  private static final int P2_START_Y = 500;

  public ThePeanutButterJellyGame() {
    super("The Peanut Butter Jelly Game");
    this.itemEffectHandler = new ItemEffectHandler(this);
  }

  @Override
  public void render(GameContainer container, Graphics graphics) throws SlickException {
    gameMapRenderer.render();
    players.forEach(Player::render);
    gameMap.getItemsOnMap().forEach(i -> i.render());
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
    gameMap = GameMapFactory.getInstance().getGameMap(0);
    app.setDisplayMode(gameMap.getWidth() * GameMapRenderer.FIELD_WIDTH, gameMap.getHeight() * GameMapRenderer.FIELD_HEIGHT, false);
    gameMapRenderer = new GameMapRenderer(gameMap);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    itemEffectHandler.processDelta(delta);
    players.forEach(p -> p.move(delta));

    itemSpawnTimer += delta;
    if (itemSpawnTimer > TPBJGConfig.ITEM_SPAWN_TIME) {
      itemSpawnTimer = 0;
      Item itemToAdd = null;
      if (Rnd.nextBoolean())
        itemToAdd = ItemGenerator.getInstance().generateRandomItem(this.gameMap);
      else
        itemToAdd = ItemGenerator.getInstance().generateRandomStartItem(this.gameMap);
      if (Objects.nonNull(itemToAdd))
        ;
      this.gameMap.getItemsOnMap().add(itemToAdd);
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

  private static AppGameContainer app;

  public static void main(String[] args) {
    try {
      ThePeanutButterJellyGame game = new ThePeanutButterJellyGame();
      app = new AppGameContainer(game, 800, 600, false);
      app.start();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  /**
   * @return the itemEffectHandler
   */
  public ItemEffectHandler getItemEffectHandler() {
    return itemEffectHandler;
  }
}
