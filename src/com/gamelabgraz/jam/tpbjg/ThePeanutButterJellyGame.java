package com.gamelabgraz.jam.tpbjg;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ThePeanutButterJellyGame extends BasicGame {

  private int gameSpeed = 100;

  private Player player1;

  public ThePeanutButterJellyGame() {
    super("The Peanut Butter Jelly Game");
  }

  @Override
  public void render(GameContainer container, Graphics graphics) throws SlickException {

    player1.render();
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setMaximumLogicUpdateInterval(gameSpeed);
    container.setMinimumLogicUpdateInterval(gameSpeed);
    container.setVSync(true);
    player1 = new Player(container, 1, false);

  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {

    player1.move(delta);
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
