package com.shepherdjerred.thestorm.friends.player.identifier;

import java.util.UUID;
import lombok.Value;

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
}
