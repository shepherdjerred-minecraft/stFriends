package com.shepherdjerred.thestorm.friends;

import com.shepherdjerred.thestorm.friends.friend.FriendNotificationOnJoinEventHandler;
import com.shepherdjerred.thestorm.friends.friend.FullyConnectedFriendGetter;
import com.shepherdjerred.thestorm.friends.player.UuidPlayerIdentifier;
import com.shepherdjerred.thestorm.friends.player.UuidPlayerIdentifierBukkitPlayerGetter;
import com.shepherdjerred.thestorm.friends.player.UuidPlayerIdentifierFactory;
import java.util.HashSet;
import java.util.UUID;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  @Override
  public void onEnable() {
    var friends = new HashSet<UuidPlayerIdentifier>();
    friends.add(new UuidPlayerIdentifier(UUID.fromString("f62616e4-6c7d-4240-9dad-131f6383564a")));
    friends.add(new UuidPlayerIdentifier(UUID.fromString("01395b25-3f13-4f00-94a1-368b77cae988")));

    var bukkitPlayerGetter = new UuidPlayerIdentifierBukkitPlayerGetter();
    var uuidPlayerIdentifierFactory = new UuidPlayerIdentifierFactory();
    var friendGetter = new FullyConnectedFriendGetter<>(friends, bukkitPlayerGetter);

    getServer().getPluginManager().registerEvents(new FriendNotificationOnJoinEventHandler<>(
        friendGetter,
        uuidPlayerIdentifierFactory), this);
  }
}
