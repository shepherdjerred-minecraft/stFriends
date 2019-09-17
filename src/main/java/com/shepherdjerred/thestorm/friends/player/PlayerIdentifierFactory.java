package com.shepherdjerred.thestorm.friends.player;

import org.bukkit.entity.Player;

public interface PlayerIdentifierFactory<T extends PlayerIdentifier> {
  T get(Player player);
}
