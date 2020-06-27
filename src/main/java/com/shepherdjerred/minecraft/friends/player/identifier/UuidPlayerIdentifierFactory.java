package com.shepherdjerred.minecraft.friends.player.identifier;

import org.bukkit.entity.Player;

public class UuidPlayerIdentifierFactory implements PlayerIdentifierFactory<UuidPlayerIdentifier> {

  @Override
  public UuidPlayerIdentifier get(Player player) {
    return new UuidPlayerIdentifier(player.getUniqueId());
  }
}
