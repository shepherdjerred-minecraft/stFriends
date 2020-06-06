package com.shepherdjerred.thestorm.friends.player.identifier;

import lombok.Value;

import java.util.UUID;

@Value
public class UuidPlayerIdentifier implements PlayerIdentifier {

  UUID uuid;

  @Override
  public boolean isSamePlayer(PlayerIdentifier playerIdentifier) {
    if (playerIdentifier instanceof UuidPlayerIdentifier) {
      return this.equals(playerIdentifier);
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String toString() {
      return uuid.toString();
  }
}
