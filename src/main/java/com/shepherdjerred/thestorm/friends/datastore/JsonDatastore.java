package com.shepherdjerred.thestorm.friends.datastore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * A JSON flatfile datastore
 */
public class JsonDatastore<T extends PlayerIdentifier> implements Datastore<T> {
    private final Gson gson;
    private final File file;

    public JsonDatastore(String filePath) {
        gson = new Gson();
        file = new File(filePath);
    }

    @Override
    public Set<T> getFriends(T player) throws FileNotFoundException {
        return loadFriendsMap().get(player);
    }

    @Override
    public boolean hasFriends(T player) throws FileNotFoundException {
        return !getFriends(player).isEmpty();
    }

    @Override
    public void addFriend(T player, T friend) throws IOException {
        var friendsMap = loadFriendsMap();
        friendsMap.get(player).add(friend);
        saveFriendsMap(friendsMap);
    }

    @Override
    public void removeFriend(T player, T friend) throws IOException {
        var friendsMap = loadFriendsMap();
        friendsMap.get(player).remove(friend);
        saveFriendsMap(friendsMap);
    }

    private Map<T, Set<T>> loadFriendsMap() throws FileNotFoundException {
        var fileReader = new FileReader(file);
        return gson.fromJson(fileReader, new TypeToken<Map<T, Set<T>>>() {}.getType());
    }

    private void saveFriendsMap(Map<T, Set<T>> friendsMap) throws IOException {
        var fileWriter = new FileWriter(file);
        gson.toJson(friendsMap, fileWriter);
    }
}
