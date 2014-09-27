package com.gamelabgraz.jam.tpbjg.items;

import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;

@FunctionalInterface
public interface IItemAction {
  void process(final ThePeanutButterJellyGame game);
}
