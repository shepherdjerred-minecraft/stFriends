package com.shepherdjerred.thestorm.friends.friend;

import com.shepherdjerred.thestorm.friends.datastore.Datastore;
import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import com.shepherdjerred.thestorm.friends.player.information.PlayerInformation;
import com.shepherdjerred.thestorm.friends.player.information.PlayerInformationGetter;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FriendGetter<T extends PlayerIdentifier> {
  private final Datastore<T> datastore;
  private final PlayerInformationGetter<T> playerInformationGetter;

  public Set<PlayerInformation> getFriends(T player) throws IOException {
      var friendIdentifiers = datastore.getFriends(player);
      return friendIdentifiers.stream().map(playerInformationGetter::getPlayerInformation).collect(Collectors.toSet());
  }

  public boolean hasFriends(T player) throws IOException {
      return datastore.hasFriends(player);
  }
}
