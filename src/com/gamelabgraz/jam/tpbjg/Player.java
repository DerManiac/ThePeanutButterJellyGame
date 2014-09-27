package com.gamelabgraz.jam.tpbjg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.gamelabgraz.jam.tpbjg.map.FieldType;

public class Player {

  private final Animation up, down, left, right;
  private Animation current;

  private final Controls controls;

  private final GameContainer container;
  private final ThePeanutButterJellyGame game;

  float x = 0, y = 0;

  private static final int size = 64;

  private float speed = 0.1f;

  public Player(final GameContainer container, final ThePeanutButterJellyGame game, final int player, final boolean useGamepad)
      throws SlickException {
    controls = new Controls(player, useGamepad);
    this.container = container;
    this.game = game;

    up = new Animation(new SpriteSheet("assets/graphics/peanut_nach_oben.png", 64, 64), 300);
    down = new Animation(new SpriteSheet("assets/graphics/peanut_nach_unten.png", 64, 64), 300);
    left = new Animation(new SpriteSheet("assets/graphics/peanut_nach_links.png", 64, 64), 300);
    right = new Animation(new SpriteSheet("assets/graphics/peanut_nach_rechts.png", 64, 64), 300);
    up.setAutoUpdate(false);
    down.setAutoUpdate(false);
    left.setAutoUpdate(false);
    right.setAutoUpdate(false);

    current = down;
  }

  public void move(final int delta) {
    final Input input = container.getInput();
    if (controls.isUseGamepad()) {
      final int gamepadNumber = controls.getGamepadNumber();
      if (input.isControllerUp(gamepadNumber))
        moveUp(delta);
      else if (input.isControllerDown(gamepadNumber))
        moveDown(delta);
      else if (input.isControllerLeft(gamepadNumber))
        moveLeft(delta);
      else if (input.isControllerRight(gamepadNumber))
        moveRight(delta);
      else
        moveStop();

    } else {
      if (input.isKeyDown(controls.getUpButton()))
        moveUp(delta);
      else if (input.isKeyDown(controls.getDownButton()))
        moveDown(delta);
      else if (input.isKeyDown(controls.getLeftButton()))
        moveLeft(delta);
      else if (input.isKeyDown(controls.getRightButton()))
        moveRight(delta);
      else
        moveStop();
    }

  }

  private void moveUp(final int delta) {
    final float y_temp = y - (delta * speed);
    if (isCollition(x, y_temp))
      return;
    y = y_temp;
    current = up;
    current.update(delta);
  }

  private void moveDown(final int delta) {
    final float y_temp = y + (delta * speed);
    if (isCollition(x, y_temp))
      return;
    y = y_temp;
    current = down;
    current.update(delta);
  }

  private void moveLeft(final int delta) {
    final float x_temp = x - (delta * speed);
    if (isCollition(x_temp, y))
      return;
    x = x_temp;
    current = left;
    current.update(delta);
  }

  private void moveRight(final int delta) {
    final float x_temp = x + (delta * speed);
    if (isCollition(x_temp, y))
      return;
    x = x_temp;
    current = right;
    current.update(delta);
  }

  private void moveStop() {
    current.setCurrentFrame(0);
  }

  public void render() {
    current.draw(x, y);
  }

  public Controls getControls() {
    return controls;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  /**
   * @return the speed
   */
  public float getSpeed() {
    return speed;
  }

  /**
   * @param speed
   *          the speed to set
   */
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  private boolean isCollition(float x, float y) {

    if (x < 0 || y < 0 || (x + size) > container.getWidth() || (y + size) > container.getHeight())
      return true;

    for (Player p : game.getPlayers()) {
      if (p != this && x < p.getX() + size - 1 && x + size - 1 > p.getX() && y < p.getY() + size - 1 && size - 1 + y > p.getY())
        return true;
    }

    if (game.getGameMap().getField((int) (x + (size / 2)) / size, (int) (y + (size / 2)) / size) != FieldType.EMPTY)
      return true;

    return false;
  }
}
