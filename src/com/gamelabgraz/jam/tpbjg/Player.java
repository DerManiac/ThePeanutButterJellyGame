package com.gamelabgraz.jam.tpbjg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player {

  private final Animation up, down, left, right;
  private Animation current;

  private final Controls controls;

  private final GameContainer container;

  float x = 0, y = 0;

  private static final float speed = 0.1f;

  public Player(final GameContainer container, final int player, final boolean useGamepad) throws SlickException {
    controls = new Controls(player, useGamepad);
    this.container = container;

    up = new Animation(new SpriteSheet("assets/graphics/walls.png", 256, 70), 300);
    down = new Animation(new SpriteSheet("assets/graphics/ground.png", 64, 64), 300);
    left = new Animation(new SpriteSheet("assets/graphics/baseflag.png", 64, 64), 300);
    right = new Animation(new SpriteSheet("assets/graphics/baseflag.png", 64, 64), 300);
    up.setAutoUpdate(false);
    down.setAutoUpdate(false);
    left.setAutoUpdate(false);
    right.setAutoUpdate(false);

    current = down;
  }

  public void move(final int delta) {
    final Input input = container.getInput();
    if (controls.isUseGamepad()) {
      final int actionButton = controls.getActionButton();
      if (input.isControllerUp(actionButton))
        moveUp(delta);
      else if (input.isControllerUp(actionButton))
        moveDown(delta);
      else if (input.isControllerUp(actionButton))
        moveLeft(delta);
      else if (input.isControllerUp(actionButton))
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
    y -= delta * speed;
    current = up;
    current.update(delta);
  }

  private void moveDown(final int delta) {
    y += delta * speed;
    current = down;
    current.update(delta);
  }

  private void moveLeft(final int delta) {
    x -= delta * speed;
    current = left;
    current.update(delta);
  }

  private void moveRight(final int delta) {
    x += delta * speed;
    current = right;
    current.update(delta);
  }

  private void moveStop() {
    current.setCurrentFrame(0);
  }

  public void render() {
    current.draw(x, y);
  }
}
