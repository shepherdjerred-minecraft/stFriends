package com.shepherdjerred.thestorm.friends.friend;

import com.shepherdjerred.thestorm.friends.player.PlayerIdentifier;
import com.shepherdjerred.thestorm.friends.player.PlayerIdentifierBukkitPlayerGetter;
import com.shepherdjerred.thestorm.friends.player.PlayerIdentifierFactory;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@AllArgsConstructor
public class FriendNotificationOnJoinEventHandler<T extends PlayerIdentifier> implements Listener {

  private final FriendGetter friendGetter;
  private final PlayerIdentifierFactory<T> playerIdentifierFactory;
  private final PlayerIdentifierBukkitPlayerGetter<T> playerIdentifierBukkitPlayerGetter;

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    var joiningPlayer = event.getPlayer();
    var joiningPlayerIdentifier = playerIdentifierFactory.get(joiningPlayer);

    if (friendGetter.hasFriends(joiningPlayerIdentifier)) {
      var friends = friendGetter.getFriends(joiningPlayerIdentifier);
      var friendsMessage = new StringBuilder();

      friendsMessage.append("Other players were last online:");
      friends.stream()
          .map(friend -> {
            var friendLastOnlineMessage = getLastOnlineMessage(friend);
            return String.format("%s: %s ago", friend.getName(), friendLastOnlineMessage);
          }).forEach(friendsMessage::append);

      joiningPlayer.sendMessage(friendsMessage.toString());
    }
  }

  public String getLastOnlineMessage(Friend friend) {
    var friendLoginTime = friend.getLastLogin();
    var now = LocalDateTime.now();

    var minutesSinceLastLogin = ChronoUnit.MINUTES.between(friendLoginTime, now);
    var hoursSinceLastLogin = ChronoUnit.HOURS.between(friendLoginTime, now);
    var daysSinceLastLogin = ChronoUnit.DAYS.between(friendLoginTime, now);

    if (daysSinceLastLogin > 0) {
      return String.format("%s days", daysSinceLastLogin);
    } else if (hoursSinceLastLogin > 0) {
      return String.format("%s hours", hoursSinceLastLogin);
    } else if (minutesSinceLastLogin > 0) {
      return String.format("%s minutes", minutesSinceLastLogin);
    } else {
      throw new IllegalStateException();
    }
  }
}
