package com.gamelabgraz.jam.tpbjg;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ThePeanutButterJellyGame extends BasicGame {

  private int gameSpeed = 100;

  private Controls player_controls[] = new Controls[] { new Controls(0), new Controls(1) };
  private int position_players[][] = new int[][] { { 580, 200 }, { 20, 200 } };

  public ThePeanutButterJellyGame() {
    super("The Peanut Butter Jelly Game");
  }

  @Override
  public void render(GameContainer container, Graphics graphics) throws SlickException {

    graphics.setColor(Color.blue);
    graphics.fillRect(position_players[0][0], position_players[0][1], 10, 10);

    graphics.setColor(Color.red);
    graphics.fillRect(position_players[1][0], position_players[1][1], 10, 10);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    container.setMaximumLogicUpdateInterval(gameSpeed);
    container.setMinimumLogicUpdateInterval(gameSpeed);

  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {

    // for both players, check input
    Input input = container.getInput();
    for (int i = 0; i < 2; i++) {
      boolean use_gamepad = player_controls[i].isUseGamepad();
      if (use_gamepad) {
        int gamepad = player_controls[i].getGamepadNumber();
        if (input.isControllerUp(gamepad)) {
          position_players[i][1] -= 20;
        } else if (input.isControllerDown(gamepad)) {
          position_players[i][1] += 20;
        } else if (input.isControllerRight(gamepad)) {
          position_players[i][0] += 20;
        } else if (input.isControllerLeft(gamepad)) {
          position_players[i][0] -= 20;
        }
      } else {
        if (input.isKeyPressed(player_controls[i].getUpButton())) {
          position_players[i][1] -= 20;
        } else if (input.isKeyPressed(player_controls[i].getDownButton())) {
          position_players[i][1] += 20;
        } else if (input.isKeyPressed(player_controls[i].getRightButton())) {
          position_players[i][0] += 20;
        } else if (input.isKeyPressed(player_controls[i].getLeftButton())) {
          position_players[i][0] -= 20;
        }
      }
    }
  }

  public void controllerButtonPressed(int controller, int button) {
    if (button == Controls.GAMEPAD_START) {
      if (!player_controls[0].isUseGamepad()) {
        player_controls[0].setUseGamepad(true);
        player_controls[0].setGamepadNumber(controller);
        System.out.println("Player 1 registered as gamepad " + controller);
      } else if (!player_controls[1].isUseGamepad()) {
        player_controls[1].setUseGamepad(true);
        player_controls[1].setGamepadNumber(controller);
        System.out.println("Player 2 registered as gamepad " + controller);
      }
    }
  }

  public void keyPressed(int key, char c) {
    System.out.println("Pressed " + key);
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
