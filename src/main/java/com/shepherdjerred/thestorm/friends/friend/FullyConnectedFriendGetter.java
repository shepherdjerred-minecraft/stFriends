package com.shepherdjerred.thestorm.friends.friend;

import com.shepherdjerred.thestorm.friends.player.PlayerIdentifier;
import com.shepherdjerred.thestorm.friends.player.PlayerIdentifierBukkitPlayerGetter;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * A getter where everyone is a friend with everyone else.
 */
@AllArgsConstructor
public class FullyConnectedFriendGetter<T extends PlayerIdentifier> implements FriendGetter {

  private final Set<T> friends;
  private final PlayerIdentifierBukkitPlayerGetter<T> playerGetter;

  @Override
  public Set<Friend> getFriends(PlayerIdentifier player) {
    if (hasFriends(player)) {
      return friends.stream()
          .filter(friend -> !friend.isSamePlayer(player))
          .map(friendIdentifier -> {
            var friendPlayer = playerGetter.getOfflinePlayer(friendIdentifier);
            var lastOnlineTimeInMillis = friendPlayer.getLastPlayed();
            var lastOnlineTime =
                Instant.ofEpochMilli(lastOnlineTimeInMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            return new Friend(friendIdentifier,
                friendPlayer.getName(),
                lastOnlineTime,
                friendPlayer.isOnline());
          })
          .collect(Collectors.toSet());
    } else {
      return new HashSet<>();
    }
  }

  @Override
  public boolean hasFriends(PlayerIdentifier player) {
    return friends.stream().anyMatch(friend -> friend.isSamePlayer(player));
  }
}
