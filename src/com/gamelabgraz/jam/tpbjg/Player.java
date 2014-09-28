package com.gamelabgraz.jam.tpbjg;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import com.gamelabgraz.jam.tpbjg.config.TPBJGConfig;
import com.gamelabgraz.jam.tpbjg.items.Item;
import com.gamelabgraz.jam.tpbjg.map.FieldType;
import com.gamelabgraz.jam.tpbjg.map.renderer.GameMapRenderer;

public class Player {

  private Animation up, down, left, right;
  private Animation current;
  private SpriteSheet glassSpritesheet;
  private Image glassSprite;

  private final Controls controls;

  private final GameContainer container;
  private final ThePeanutButterJellyGame game;

  private int lives;

  private int player;
  private FoodType type;

  private boolean carriesGlass = false;

  float x = 0, y = 0;

  private static final int size = 64;

  private float speed = 0.1f;

  private int crashCharges = 0;
  private int bricks = 0;

  public Player(final GameContainer container, final ThePeanutButterJellyGame game, final FoodType type, final boolean useGamepad)
      throws SlickException {
    controls = new Controls(type, useGamepad);
    this.container = container;
    this.game = game;
    this.type = type;

    updatePlayerSprites();

    setLives(TPBJGConfig.PLAYER_LIVES);

    current = down;
  }

  public void update(final int delta) {
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
      if (input.isControlPressed(controls.getActionButton()))
        ;
      action();

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
      if (input.isKeyDown(controls.getActionButton()))
        action();
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

  public void renderGlass() {
    int[] coordinates = game.getGameMap().getPlayerGlassSpawns().get(this.player - 1);
    glassSprite.draw(coordinates[0] * GameMapRenderer.FIELD_WIDTH, coordinates[1] * GameMapRenderer.FIELD_HEIGHT);
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

  private void updatePlayerSprites() {
    try {
      if (player == 1) {
        if (carriesGlass) {
          up = new Animation(new SpriteSheet("assets/graphics/peanut_nach_oben_glass.png", 64, 64), 300);
          down = new Animation(new SpriteSheet("assets/graphics/peanut_nach_unten_glass.png", 64, 64), 300);
          left = new Animation(new SpriteSheet("assets/graphics/peanut_nach_links_glass.png", 64, 64), 300);
          right = new Animation(new SpriteSheet("assets/graphics/peanut_nach_rechts_glass.png", 64, 64), 300);
        } else {
          up = new Animation(new SpriteSheet("assets/graphics/peanut_nach_oben.png", 64, 64), 300);
          down = new Animation(new SpriteSheet("assets/graphics/peanut_nach_unten.png", 64, 64), 300);
          left = new Animation(new SpriteSheet("assets/graphics/peanut_nach_links.png", 64, 64), 300);
          right = new Animation(new SpriteSheet("assets/graphics/peanut_nach_rechts.png", 64, 64), 300);
        }
        glassSpritesheet = new SpriteSheet("assets/graphics/peanutbutter_glass_tiled.png", 64, 64);
      } else {
        if (carriesGlass) {
          up = new Animation(new SpriteSheet("assets/graphics/jelly_nach_oben_glass.png", 64, 64), 300);
          down = new Animation(new SpriteSheet("assets/graphics/jelly_nach_unten_glass.png", 64, 64), 300);
          left = new Animation(new SpriteSheet("assets/graphics/jelly_nach_links_glass.png", 64, 64), 300);
          right = new Animation(new SpriteSheet("assets/graphics/jelly_nach_rechts_glass.png", 64, 64), 300);
        } else {
          up = new Animation(new SpriteSheet("assets/graphics/jelly_nach_oben.png", 64, 64), 300);
          down = new Animation(new SpriteSheet("assets/graphics/jelly_nach_unten.png", 64, 64), 300);
          left = new Animation(new SpriteSheet("assets/graphics/jelly_nach_links.png", 64, 64), 300);
          right = new Animation(new SpriteSheet("assets/graphics/jelly_nach_rechts.png", 64, 64), 300);
        }
        glassSpritesheet = new SpriteSheet("assets/graphics/jelly_glass_tiled.png", 64, 64);
      }
      up.setAutoUpdate(false);
      down.setAutoUpdate(false);
      left.setAutoUpdate(false);
      right.setAutoUpdate(false);
    } catch (SlickException e) {
      System.err.println("Cannot load player sprites.");
      e.printStackTrace();
    }
  }

  public void action() {
    if (this.bricks > 0) {
      int x_temp = (int) (x + (size / 2)) / size;
      int y_temp = (int) (y + (size / 2)) / size;
      if (current == up) {
        y_temp--;
      } else if (current == down) {
        y_temp++;
      } else if (current == right) {
        x_temp++;
      } else if (current == left) {
        x_temp--;
      }
      if (game.getGameMap().getField(x_temp, y_temp) == FieldType.EMPTY) {
        game.getGameMap().setField(x_temp, y_temp, FieldType.WALL);
        try {
          new Sound("assets/sounds/build_wall.wav").play();
        } catch (SlickException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Sets number of lives and adapts the base glass image
   * 
   * @param lives
   *          number of lives
   */
  public void setLives(int lives) {
    this.lives = lives;
    this.glassSprite = glassSpritesheet.getSprite(TPBJGConfig.PLAYER_LIVES - this.lives, 0);
  }

  /**
   * Decrement the number of lives
   */
  public void lostLife() {
    setLives(this.lives - 1);
  }

  /**
   * @param speed
   *          the speed to set
   */
  public void setSpeed(float speed) {
    System.out.println("Set speed from " + this.speed + " to " + speed);
    this.speed = speed;
  }

  public void addCrashCharge() {
    crashCharges++;
  }

  public void addBrick() {
    bricks++;
  }

  public void removeCrashCharge() {
    crashCharges = Math.max(crashCharges - 1, 0);
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
    if (game.getGameMap().getField((int) (x + (size / 2)) / size, (int) (y + (size / 2)) / size) != FieldType.EMPTY) {
      if (crashCharges > 0) {
        game.getGameMap().setField((int) (x + (size / 2)) / size, (int) (y + (size / 2)) / size, FieldType.EMPTY);
        crashCharges--;
        try {
          new Sound("assets/sounds/wall_crash.wav").play();
        } catch (SlickException e) {
          e.printStackTrace();
        }
        return false;
      }
      return true;
    }
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

    if (!carriesGlass) {
      // check if we are on enemy glass
      for (int i = 0; i < game.getPlayers().size(); i++) {
        if (i != player - 1) {
          int[] other_base_coordinates = game.getGameMap().getPlayerGlassSpawns().get(i);
          if (((other_base_coordinates[0] >= x1_temp && other_base_coordinates[0] <= x2_temp) || (other_base_coordinates[0] <= x1_temp && other_base_coordinates[0] >= x2_temp))
              && ((other_base_coordinates[1] >= y1_temp && other_base_coordinates[1] <= y2_temp) || (other_base_coordinates[1] <= y1_temp && other_base_coordinates[1] >= y2_temp))) {
            // we got a glass
            game.getPlayers().get(i).lostLife();
            carriesGlass = true;
            updatePlayerSprites();
          }
        }
      }
    } else {
      // check if we are on our own bread
      int[] bread_coordinates = game.getGameMap().getPlayerSpawns().get(player - 1);
      if (((bread_coordinates[0] >= x1_temp && bread_coordinates[0] <= x2_temp) || (bread_coordinates[0] <= x1_temp && bread_coordinates[0] >= x2_temp))
          && ((bread_coordinates[1] >= y1_temp && bread_coordinates[1] <= y2_temp) || (bread_coordinates[1] <= y1_temp && bread_coordinates[1] >= y2_temp))) {
        // we dropped the glass
        carriesGlass = false;
        updatePlayerSprites();
        for (Player p : game.getPlayers()) {
          if (p != this && p.lives == 1) {
            game.setPlayerWon(player);
          }
        }
      }
    }
  }
}
