package com.gamelabgraz.jam.tpbjg;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

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

  private Player winner = null;

  private TrueTypeFont menufont;

  public ThePeanutButterJellyGame() {
    super("The Peanut Butter Jelly Game");
    this.itemEffectHandler = new ItemEffectHandler(this);
  }

  @Override
  public void render(GameContainer container, Graphics graphics) throws SlickException {
    gameMapRenderer.render();
    gameMap.getItemsOnMap().forEach(i -> i.render());
    players.forEach(Player::renderGlass);
    players.forEach(Player::render);

    int position = 10;
    final int offset = 30;
    int x_offset = 0;

    graphics.setFont(menufont);
    for (Player p : players) {
      if (p.getControls().isUseGamepad() ? container.getInput().isButtonPressed(p.getControls().getStatusButton() - 1,
          p.getControls().getGamepadNumber()) : container.getInput().isKeyDown(p.getControls().getStatusButton())) {
        position = 10;
        graphics.drawString(p.getType().getName(), 20 + x_offset, position += offset);
        graphics.drawString("charges: " + p.getCrashCharges(), 20 + x_offset, position += offset);
        graphics.drawString("bricks: " + p.getBricks(), 20 + x_offset, position += offset);
        x_offset += 300;
      }
    }

    if (winner != null) {
      graphics.setFont(new TrueTypeFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 48), true));
      graphics.drawString(winner.getType().getName() + " WON!", app.getWidth() / 2 - 150, app.getHeight() / 2 - 100);
    }
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setMaximumLogicUpdateInterval(gameSpeed);
    container.setMinimumLogicUpdateInterval(gameSpeed);
    container.setVSync(true);

    menufont = new TrueTypeFont(new Font("Arial", Font.BOLD, 28), false);

    // Load sample map
    gameMap = GameMapFactory.getInstance().getGameMap(4);
    app.setDisplayMode(gameMap.getWidth() * GameMapRenderer.FIELD_WIDTH, gameMap.getHeight() * GameMapRenderer.FIELD_HEIGHT, false);
    gameMapRenderer = new GameMapRenderer(gameMap);

    Player p1 = new Player(container, this, FoodType.PEANUT, 1, false);
    Player p2 = new Player(container, this, FoodType.JELLY, 2, false);
    p1.setX(gameMap.getPlayerSpawns().get(0)[0] * GameMapRenderer.FIELD_WIDTH);
    p1.setY(gameMap.getPlayerSpawns().get(0)[1] * GameMapRenderer.FIELD_HEIGHT);
    p2.setX(gameMap.getPlayerSpawns().get(1)[0] * GameMapRenderer.FIELD_WIDTH);
    p2.setY(gameMap.getPlayerSpawns().get(1)[1] * GameMapRenderer.FIELD_HEIGHT);
    players.add(p1);
    players.add(p2);

    Sound bgmusic = new Sound("assets/sounds/theme1.wav");
    bgmusic.loop();
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    if (winner == null) {
      itemEffectHandler.processDelta(delta);
      players.forEach(p -> p.update(delta));

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

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayerWon(Player player) {
    winner = player;
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
