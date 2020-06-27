package com.shepherdjerred.minecraft.friends.friend;

import com.shepherdjerred.minecraft.friends.datastore.Datastore;
import com.shepherdjerred.minecraft.friends.player.identifier.PlayerIdentifier;
import com.shepherdjerred.minecraft.friends.player.information.PlayerInformation;
import com.shepherdjerred.minecraft.friends.player.information.PlayerInformationGetter;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

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
