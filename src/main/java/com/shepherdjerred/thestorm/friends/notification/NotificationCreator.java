package com.shepherdjerred.thestorm.friends.notification;

import com.shepherdjerred.thestorm.friends.friend.FriendGetter;
import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import com.shepherdjerred.thestorm.friends.player.information.PlayerInformation;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotificationCreator<T extends PlayerIdentifier> {
  private final FriendGetter<T> friendGetter;

  public String createNotification(T player) throws IOException {
    if (friendGetter.hasFriends(player)) {
      var friends = friendGetter.getFriends(player);
      var friendsMessage = new StringBuilder();

      friendsMessage.append("Other were last online:\n");
      friends
        .stream()
        .map(
          playerInformation -> {
            var friendLastOnlineMessage = getLastOnlineMessage(
              playerInformation
            );
            return String.format(
              "%s: %s\n",
              playerInformation.getName(),
              friendLastOnlineMessage
            );
          }
        )
        .forEach(friendsMessage::append);

      return friendsMessage.toString();
    } else {
      return "You have no friends! Add some with /friends add";
    }
  }

  public String getLastOnlineMessage(PlayerInformation playerInformation) {
    var friendLoginTime = playerInformation.getLastLogin();
    var now = LocalDateTime.now();

    var minutesSinceLastLogin = ChronoUnit.MINUTES.between(
      friendLoginTime,
      now
    );
    var hoursSinceLastLogin = ChronoUnit.HOURS.between(friendLoginTime, now);
    var daysSinceLastLogin = ChronoUnit.DAYS.between(friendLoginTime, now);

    if (playerInformation.isOnline()) {
      return "Right now";
    }

    String noun;
    long amount;

    if (daysSinceLastLogin > 0) {
      amount = daysSinceLastLogin;
      noun = "day";
    } else if (hoursSinceLastLogin > 0) {
      amount = hoursSinceLastLogin;
      noun = "hour";
    } else if (minutesSinceLastLogin > 0) {
      amount = minutesSinceLastLogin;
      noun = "minute";
    } else {
      return "Less than a minute ago";
    }

    return String.format("%s %s ago", amount, getPluralizedNoun(amount, noun));
  }

  private String getPluralizedNoun(long amount, String noun) {
    if (amount == 1) {
      return noun;
    } else {
      return noun + "s";
    }
  }
}
