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
    friends.add(new UuidPlayerIdentifier(UUID.fromString("d4ee8c5b-b58d-4aa0-a2d5-84b94650eb91")));
    friends.add(new UuidPlayerIdentifier(UUID.fromString("d90e4860-c902-43b9-af72-f7ef12f0383e")));
    friends.add(new UuidPlayerIdentifier(UUID.fromString("455dad50-64e1-45ce-a45e-550b90c1d7b0")));
    friends.add(new UuidPlayerIdentifier(UUID.fromString("92c6b060-6e4c-4776-822f-bd024d485956")));

    var bukkitPlayerGetter = new UuidPlayerIdentifierBukkitPlayerGetter();
    var uuidPlayerIdentifierFactory = new UuidPlayerIdentifierFactory();
    var friendGetter = new FullyConnectedFriendGetter<>(friends, bukkitPlayerGetter);
    
    getServer().getPluginManager().registerEvents(new FriendNotificationOnJoinEventHandler<>(
        friendGetter,
        uuidPlayerIdentifierFactory,
        bukkitPlayerGetter), this);
  }
}
