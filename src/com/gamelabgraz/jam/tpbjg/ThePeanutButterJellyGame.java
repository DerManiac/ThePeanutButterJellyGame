package com.gamelabgraz.jam.tpbjg;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.gamelabgraz.jam.tpbjg.map.IGameMap;
import com.gamelabgraz.jam.tpbjg.map.implementation.SampleGameMapFactory;
import com.gamelabgraz.jam.tpbjg.map.renderer.GameMapRenderer;


public class ThePeanutButterJellyGame extends BasicGame {

  private int gameSpeed = 100;

  private ArrayList<Player> players = new ArrayList<Player>();

  private GameMapRenderer gameMapRenderer;

  public ThePeanutButterJellyGame() {
    super("The Peanut Butter Jelly Game");
  }

  @Override
  public void render(GameContainer container, Graphics graphics) throws SlickException {
    players.forEach(Player::render);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setMaximumLogicUpdateInterval(gameSpeed);
    container.setMinimumLogicUpdateInterval(gameSpeed);
    container.setVSync(true);
    players.add(new Player(container, this, 1, false));
    // players.add(new Player(container, this, 2, false));
    // players.get(1).setX(65);
    
    // Load sample map
    SampleGameMapFactory factory = new SampleGameMapFactory();
    IGameMap sample_map = factory.getGameMap(0);
    gameMapRenderer = new GameMapRenderer(container.getGraphics(), sample_map);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    players.forEach(p -> p.move(delta));
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
