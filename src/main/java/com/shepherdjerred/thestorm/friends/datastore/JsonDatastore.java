package com.shepherdjerred.thestorm.friends.datastore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A JSON flatfile datastore
 */
@Log4j2
public class JsonDatastore<T extends PlayerIdentifier> implements Datastore<T> {
    private final Gson gson;
    private final File file;

    public JsonDatastore(String filePath) {
        gson = new Gson();
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
        log.info(player);
        var friendsMap = loadFriendsMap();
        Set<T> currentFriends = friendsMap.get(player);
        if (currentFriends == null) {
            currentFriends = new HashSet<>();
        }
        friendsMap.put(player, currentFriends);
        log.info(friendsMap);
        return friendsMap;
    }

    private void prepareFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    private Map<T, Set<T>> loadFriendsMap() throws IOException {
        prepareFile(file);
        var fileReader = new FileReader(file);
        Map<T, Set<T>> map = gson.fromJson(fileReader, new TypeToken<Map<T, Set<T>>>() {
        }.getType());
        if (map == null) {
            map = new HashMap<>();
        }
        return map;
    }

    private void saveFriendsMap(Map<T, Set<T>> friendsMap) throws IOException {
        prepareFile(file);
        var fileWriter = new FileWriter(file);
        gson.toJson(friendsMap, fileWriter);
    }
}
