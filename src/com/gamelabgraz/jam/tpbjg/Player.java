package com.gamelabgraz.jam.tpbjg;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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

  private boolean carriesGlass = false;

  float x = 0, y = 0;

  private static final int size = 64;

  private float speed = 0.1f;

  private int crashCharges = 0;

  public Player(final GameContainer container, final ThePeanutButterJellyGame game, final FoodType type, int player,
      final boolean useGamepad) throws SlickException {
    controls = new Controls(type, useGamepad);
    this.container = container;
    this.game = game;
    this.player = player;
    updatePlayerSprites();

    setLives(TPBJGConfig.PLAYER_LIVES);

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

    checkPosition();
  }

  private void moveUp(final int delta) {

    float y_delta = (delta * speed);
    float y_temp = y - y_delta;
    if (isCollition(x, y_temp)) {
      while (y_delta > 0.1f) {
        y_delta /= 2;
        if (isCollition(x, y_temp))
          y_temp += y_delta;
        else
          y_temp -= y_delta;
      }
      if (isCollition(x, y_temp))
        return;
    }
    y = y_temp;
    current = up;
    current.update(delta);
  }

  private void moveDown(final int delta) {
    float y_delta = (delta * speed);
    float y_temp = y + y_delta;
    if (isCollition(x, y_temp)) {
      while (y_delta > 0.1f) {
        y_delta /= 2;
        if (isCollition(x, y_temp))
          y_temp -= y_delta;
        else
          y_temp += y_delta;
      }
      if (isCollition(x, y_temp))
        return;
    }
    y = y_temp;
    current = down;
    current.update(delta);
  }

  private void moveLeft(final int delta) {
    float x_delta = (delta * speed);
    float x_temp = x - x_delta;
    if (isCollition(x_temp, y)) {
      while (x_delta > 0.1f) {
        x_delta /= 2;
        if (isCollition(x_temp, y))
          x_temp += x_delta;
        else
          x_temp -= x_delta;
      }
      if (isCollition(x_temp, y))
        return;
    }
    x = x_temp;
    current = left;
    current.update(delta);
  }

  private void moveRight(final int delta) {
    float x_delta = (delta * speed);
    float x_temp = x + x_delta;
    if (isCollition(x_temp, y)) {
      while (x_delta > 0.1f) {
        x_delta /= 2;
        if (isCollition(x_temp, y))
          x_temp -= x_delta;
        else
          x_temp += x_delta;
      }
      if (isCollition(x_temp, y))
        return;
    }
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

  public void removeCrashCharge() {
    crashCharges = Math.max(crashCharges - 1, 0);
  }

  private boolean isCollition(float x, float y) {

    if (x < 0 || y < 0 || (x + size) > container.getWidth() || (y + size) > container.getHeight())
      return true;

    for (Player p : game.getPlayers()) {
      if (p != this && x < p.getX() + size - 1 && x + size - 1 > p.getX() && y < p.getY() + size - 1 && size - 1 + y > p.getY())
        return true;
    }

    if (game.getGameMap().getField((int) (x + (size / 2)) / size, (int) (y + (size / 2)) / size) != FieldType.EMPTY) {
      if (crashCharges > 0) {
        game.getGameMap().setField((int) (x + (size / 2)) / size, (int) (y + (size / 2)) / size, FieldType.EMPTY);
        crashCharges--;
        return false;
      }
      return true;
    }

    return false;
  }

  private void checkPosition() {
    final int x_temp = (int) (x + (size / 2)) / size;
    final int y_temp = (int) (y + (size / 2)) / size;
    ArrayList<Item> temp = new ArrayList<>(game.getGameMap().getItemsOnMap());

    temp.forEach(i -> {
      if (i.getX() == x_temp && i.getY() == y_temp) {
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
          if (x_temp == other_base_coordinates[0] && y_temp == other_base_coordinates[1]) {
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
      if (x_temp == bread_coordinates[0] && y_temp == bread_coordinates[1]) {
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
