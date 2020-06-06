package com.shepherdjerred.thestorm.friends.player.information;

import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifierBukkitPlayerGetter;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;

@AllArgsConstructor
public class PlayerInformationGetter<T extends PlayerIdentifier> {

    private final PlayerIdentifierBukkitPlayerGetter<T> playerGetter;

    public PlayerInformation getPlayerInformation(T playerIdentifier) {
        var friendPlayer = playerGetter.getOfflinePlayer(playerIdentifier);
        var lastOnlineTimeInMillis = friendPlayer.getLastPlayed();
        var lastOnlineTime = Instant.ofEpochMilli(lastOnlineTimeInMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return new PlayerInformation(playerIdentifier,
                friendPlayer.getName(),
                lastOnlineTime,
                friendPlayer.isOnline());
    }
}