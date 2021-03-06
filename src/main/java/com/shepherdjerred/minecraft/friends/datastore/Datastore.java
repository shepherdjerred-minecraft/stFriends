package com.shepherdjerred.minecraft.friends.datastore;

import com.shepherdjerred.minecraft.friends.player.identifier.PlayerIdentifier;
import java.io.IOException;
import java.util.Set;

public interface Datastore<T extends PlayerIdentifier> {
  Set<T> getFriends(T player) throws IOException;

  boolean hasFriends(T player) throws IOException;

  void addFriend(T player, T friend) throws IOException;

  void removeFriend(T player, T friend) throws IOException;
}
