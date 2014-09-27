package com.gamelabgraz.jam.tpbjg.items;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;

public interface IItemAction {
  void startEffect(final ThePeanutButterJellyGame game, final Player effector);
  void endEffect(final ThePeanutButterJellyGame game, final Player effector);
}
