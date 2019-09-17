package com.shepherdjerred.thestorm.friends.player;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class UuidPlayerIdentifier implements PlayerIdentifier {

  @Getter
  private final UUID uuid;

  @Override
  public boolean isSamePlayer(PlayerIdentifier playerIdentifier) {
    if (playerIdentifier instanceof UuidPlayerIdentifier) {
      return this.equals(playerIdentifier);
    } else {
      throw new IllegalArgumentException();
    }
  }
}
