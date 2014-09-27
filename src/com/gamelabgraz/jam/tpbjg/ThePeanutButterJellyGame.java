package com.gamelabgraz.jam.tpbjg;

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

  private Player player1, player2;

  private GameMapRenderer gameMapRenderer;

  public ThePeanutButterJellyGame() {
    super("The Peanut Butter Jelly Game");
  }

  @Override
  public void render(GameContainer container, Graphics graphics) throws SlickException {
    gameMapRenderer.render();

    player1.render();
    player2.render();
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setMaximumLogicUpdateInterval(gameSpeed);
    container.setMinimumLogicUpdateInterval(gameSpeed);
    container.setVSync(true);
    player1 = new Player(container, 1, false);
    player2 = new Player(container, 2, false);

    // Load sample map
    SampleGameMapFactory factory = new SampleGameMapFactory();
    IGameMap sample_map = factory.getGameMap(0);
    gameMapRenderer = new GameMapRenderer(container.getGraphics(), sample_map);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    player1.move(delta);
    player2.move(delta);
  }

  public void controllerButtonPressed(int controller, int button) {
    if (button == Controls.GAMEPAD_START) {
      if (!player1.getControls().isUseGamepad()) {
        player1.getControls().setUseGamepad(true);
        player1.getControls().setGamepadNumber(controller);
        System.out.println("Player 1 registered as gamepad " + controller);
      } else if (!player2.getControls().isUseGamepad()) {
        player2.getControls().setUseGamepad(true);
        player2.getControls().setGamepadNumber(controller);
        System.out.println("Player 2 registered as gamepad " + controller);
      }
    }
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
