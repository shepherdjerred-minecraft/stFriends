package com.shepherdjerred.minecraft.friends.player.identifier;

import org.bukkit.entity.Player;

public interface PlayerIdentifierFactory<T extends PlayerIdentifier> {
  T get(Player player);
}
