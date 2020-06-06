package com.shepherdjerred.thestorm.friends.datastore.flatfile;

import com.shepherdjerred.thestorm.friends.datastore.Datastore;
import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A flatfile datastore
 */
@Log4j2
public class FlatfileDatastore<T extends PlayerIdentifier> implements Datastore<T> {
  private final Serializer<T> serializer;
  private final File file;

  public FlatfileDatastore(Serializer<T> serializer, String filePath) {
    this.serializer = serializer;
    file = new File(filePath);
  }

  @Override
  public Set<T> getFriends(T player) throws IOException {
    Map<T, Set<T>> currentFriends = getFriendsMapAndSetDefaultForPlayer(player);
    return currentFriends.get(player);
  }

  @Override
  public boolean hasFriends(T player) throws IOException {
    return !getFriends(player).isEmpty();
  }

  @Override
  public void addFriend(T player, T friend) throws IOException {
    Map<T, Set<T>> currentFriends = getFriendsMapAndSetDefaultForPlayer(player);
    currentFriends.get(player).add(friend);
    saveFriendsMap(currentFriends);
  }

  @Override
  public void removeFriend(T player, T friend) throws IOException {
    Map<T, Set<T>> currentFriends = getFriendsMapAndSetDefaultForPlayer(player);
    currentFriends.get(player).remove(friend);
    saveFriendsMap(currentFriends);
  }

  private Map<T, Set<T>> getFriendsMapAndSetDefaultForPlayer(T player) throws IOException {
    var friendsMap = loadFriendsMap();

    Set<T> currentFriends = friendsMap.get(player);
    if (currentFriends == null) {
      currentFriends = new HashSet<>();
    }

    friendsMap.put(player, currentFriends);
    return friendsMap;
  }

  private Map<T, Set<T>> loadFriendsMap() throws IOException {
    prepareFile(file);
    Map<T, Set<T>> map;

    try (var stream = new FileInputStream(file)) {
      var bytes = stream.readAllBytes();
      var jsonString = new String(bytes);
      map = serializer.deserialize(jsonString);
    }

    if (map == null) {
      map = new HashMap<>();
    }

    return map;
  }

  private void saveFriendsMap(Map<T, Set<T>> friendsMap) throws IOException {
    prepareFile(file);
    var friendsString = serializer.serialize(friendsMap);
    try (var stream = new FileOutputStream(file)) {
      stream.write(friendsString.getBytes());
    }
  }

  private void prepareFile(File file) throws IOException {
    file.getParentFile().mkdirs();
    file.createNewFile();
  }
}
