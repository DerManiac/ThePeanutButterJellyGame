package com.gamelabgraz.jam.tpbjg;

public class Controls {

  public static final int GAMEPAD_START = 8;

  private boolean useGamepad;

  private int player;
  private int upButton;
  private int downButton;
  private int leftButton;
  private int rightButton;
  private int actionButton;

  private int gamepadNumber;

  public Controls(int player) {
    // initializing default controls
    this(player, false);
  }

  public Controls(int player, boolean useGamepad) {
    // initializing default controls
    if (useGamepad) {
      this.useGamepad = useGamepad;
      actionButton = 1;
    } else {
      if (player == 1) {
        actionButton = 57; // space
      } else {
        actionButton = 28; // enter
      }
    }

    // init backup directional keyboard controls even if gamepad is used
    if (player == 1) {
      upButton = 17; // W
      leftButton = 30; // A
      downButton = 31; // S
      rightButton = 32; // D
    } else {
      upButton = 200; // arrow up
      leftButton = 203; // arrow left
      downButton = 208; // arrow down
      rightButton = 205; // arrow right
    }

  }

  public boolean isUseGamepad() {
    return useGamepad;
  }

  public void setUseGamepad(boolean useGamepad) {
    this.useGamepad = useGamepad;
  }

  public int getUpButton() {
    return upButton;
  }

  public void setUpButton(int upButton) {
    this.upButton = upButton;
  }

  public int getDownButton() {
    return downButton;
  }

  public void setDownButton(int downButton) {
    this.downButton = downButton;
  }

  public int getLeftButton() {
    return leftButton;
  }

  public void setLeftButton(int leftButton) {
    this.leftButton = leftButton;
  }

  public int getRightButton() {
    return rightButton;
  }

  public void setRightButton(int rightButton) {
    this.rightButton = rightButton;
  }

  public int getActionButton() {
    return actionButton;
  }

  public void setActionButton(int actionButton) {
    this.actionButton = actionButton;
  }

  public int getGamepadNumber() {
    return gamepadNumber;
  }

  public void setGamepadNumber(int gamepadNumber) {
    this.gamepadNumber = gamepadNumber;
  }

}
