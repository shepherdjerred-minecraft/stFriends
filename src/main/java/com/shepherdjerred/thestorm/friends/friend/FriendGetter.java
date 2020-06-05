package com.shepherdjerred.thestorm.friends.friend;

import com.shepherdjerred.thestorm.friends.player.PlayerIdentifier;
import java.util.Set;

public interface FriendGetter {
  Set<Friend> getFriends(PlayerIdentifier player);

  boolean hasFriends(PlayerIdentifier player);
}
