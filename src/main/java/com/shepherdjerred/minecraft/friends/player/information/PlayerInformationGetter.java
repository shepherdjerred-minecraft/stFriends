package com.shepherdjerred.minecraft.friends.player.information;

import com.shepherdjerred.minecraft.friends.player.identifier.PlayerIdentifier;
import com.shepherdjerred.minecraft.friends.player.identifier.PlayerIdentifierBukkitPlayerGetter;
import java.time.Instant;
import java.time.ZoneId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerInformationGetter<T extends PlayerIdentifier> {
  private final PlayerIdentifierBukkitPlayerGetter<T> playerGetter;

  public PlayerInformation getPlayerInformation(T playerIdentifier) {
    var friendPlayer = playerGetter.getOfflinePlayer(playerIdentifier);
    var lastOnlineTimeInMillis = friendPlayer.getLastPlayed();
    var lastOnlineTime = Instant.ofEpochMilli(lastOnlineTimeInMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();

    return new PlayerInformation(playerIdentifier, friendPlayer.getName(), lastOnlineTime, friendPlayer.isOnline());
  }
}
