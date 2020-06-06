package com.shepherdjerred.thestorm.friends.datastore.flatfile;

import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import java.util.Map;
import java.util.Set;

public interface Serializer<T extends PlayerIdentifier> {
  String serialize(Map<T, Set<T>> map);

  Map<T, Set<T>> deserialize(String string);
}
