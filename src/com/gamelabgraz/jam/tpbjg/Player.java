package com.gamelabgraz.jam.tpbjg;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.gamelabgraz.jam.tpbjg.items.Item;
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

  private final FoodType type;

  public Player(final GameContainer container, final ThePeanutButterJellyGame game, final FoodType type, final boolean useGamepad)
      throws SlickException {
    controls = new Controls(type, useGamepad);
    this.container = container;
    this.game = game;
    this.type = type;

    switch (type) {
    default:
    case PEANUT:
      up = new Animation(new SpriteSheet("assets/graphics/peanut_nach_oben.png", 64, 64), 300);
      down = new Animation(new SpriteSheet("assets/graphics/peanut_nach_unten.png", 64, 64), 300);
      left = new Animation(new SpriteSheet("assets/graphics/peanut_nach_links.png", 64, 64), 300);
      right = new Animation(new SpriteSheet("assets/graphics/peanut_nach_rechts.png", 64, 64), 300);
      break;
    case JELLY:
      up = new Animation(new SpriteSheet("assets/graphics/jelly_nach_oben.png", 64, 64), 300);
      down = new Animation(new SpriteSheet("assets/graphics/jelly_nach_unten.png", 64, 64), 300);
      left = new Animation(new SpriteSheet("assets/graphics/jelly_nach_links.png", 64, 64), 300);
      right = new Animation(new SpriteSheet("assets/graphics/jelly_nach_rechts.png", 64, 64), 300);
      break;
    }
    up.setAutoUpdate(false);
    down.setAutoUpdate(false);
    left.setAutoUpdate(false);
    right.setAutoUpdate(false);

    current = down;
  }

  public void move(final int delta) {
    final Input input = container.getInput();
    final float x_orig = x;
    final float y_orig = y;
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

    checkLine(x_orig, y_orig, x, y);
  }

  private void moveUp(final int delta) {
    float dy = -delta * speed;
    y = getMaxMoveDistanceY(dy);
    current = up;
    current.update(delta);
  }

  private void moveDown(final int delta) {
    float dy = delta * speed;
    y = getMaxMoveDistanceY(dy);
    current = down;
    current.update(delta);
  }

  private void moveLeft(final int delta) {
    float dx = -delta * speed;
    x = getMaxMoveDistanceX(dx);
    current = left;
    current.update(delta);
  }

  private void moveRight(final int delta) {
    float dx = delta * speed;
    x = getMaxMoveDistanceX(dx);
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
    System.out.println("Set speed from " + this.speed + " to " + speed);
    this.speed = speed;
  }

  private boolean isScreenCollition(final float x, final float y) {
    if (x < 0 || y < 0 || (x + size) > container.getWidth() || (y + size) > container.getHeight())
      return true;

    return false;
  }

  /**
   * checks for collition between this player and other players
   * 
   * @param x
   * @param y
   * @return the collided player or null if no player is on this position
   */
  private Player playerCollition(final float x, final float y) {
    for (Player p : game.getPlayers()) {
      if (p != this && x < p.getX() + size && x + size > p.getX() && y < p.getY() + size && size + y > p.getY())
        return p;
    }
    return null;
  }

  private boolean isWorldCollition(final float x, final float y) {
    if (game.getGameMap().getField((int) (x + (size / 2)) / size, (int) (y + (size / 2)) / size) != FieldType.EMPTY)
      return true;

    return false;
  }

  private float getMaxMoveDistanceX(final float dx_desired) {
    float dx = 0;
    final float distance = (dx_desired > 0 ? size : -size) / 2;

    for (int i = 1; dx != dx_desired; i++) {
      dx = i * distance;

      if (dx_desired > 0 ? dx > dx_desired : dx < dx_desired)
        dx = dx_desired;

      if (isScreenCollition(x + dx, y)) {
        System.out.println("screen: " + x + dx);
        return dx_desired > 0 ? container.getWidth() - size : 0;
      }

      final Player p;
      if ((p = playerCollition(x + dx, y)) != null) {
        System.out.println("player: " + x + dx);
        return dx_desired > 0 ? p.getX() - size : p.getX() + size;
      }

      if (isWorldCollition(x + dx, y)) {
        System.out.println("world: " + x + dx);
        final float x_temp = x + dx + (size / 2);
        return dx_desired > 0 ? (((int) x_temp) / size) * (float) size - (size / 2) - 0.1f : (((int) x_temp) / size) * (float) size
            + (size / 2) + 0.1f;
      }
    }
    return x + dx;
  }

  private float getMaxMoveDistanceY(final float dy_desired) {
    float dy = 0;
    final float distance = (dy_desired > 0 ? size : -size) / 2;

    for (int i = 1; dy != dy_desired; i++) {
      dy = i * distance;

      if (dy_desired > 0 ? dy > dy_desired : dy < dy_desired)
        dy = dy_desired;

      if (isScreenCollition(x, y + dy)) {
        return dy_desired > 0 ? container.getHeight() - size : 0;
      }

      final Player p;
      if ((p = playerCollition(x, y + dy)) != null) {
        return dy_desired > 0 ? p.getY() - size : p.getY() + size;
      }

      if (isWorldCollition(x, y + dy)) {
        final float y_temp = y + dy + (size / 2);
        return dy_desired > 0 ? (((int) y_temp) / size) * (float) size - (size / 2) - 0.1f : (((int) y_temp) / size) * (float) size
            + (size / 2) + 0.1f;
      }
    }
    return y + dy;
  }

  private void checkLine(final float x1, final float y1, final float x2, final float y2) {
    final int x1_temp = (int) (x1 + (size / 2)) / size;
    final int y1_temp = (int) (y1 + (size / 2)) / size;
    final int x2_temp = (int) (x2 + (size / 2)) / size;
    final int y2_temp = (int) (y2 + (size / 2)) / size;

    ArrayList<Item> tempItems = new ArrayList<>(game.getGameMap().getItemsOnMap());

    tempItems.forEach(i -> {
      final int ix = i.getX();
      final int iy = i.getY();
      if (((ix >= x1_temp && ix <= x2_temp) || (ix <= x1_temp && ix >= x2_temp))
          && ((iy >= y1_temp && iy <= y2_temp) || (iy <= y1_temp && iy >= y2_temp))) {
        i.processEffect(game, this);
        Arrays.stream(i.getType().getItemActions()).forEach(
            ii -> game.getItemEffectHandler().registerEffect(ii, this, i.getType().getDuration()));
      }
    });
  }
}
