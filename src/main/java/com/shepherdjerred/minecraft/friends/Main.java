package com.shepherdjerred.minecraft.friends;

import com.shepherdjerred.minecraft.friends.datastore.Datastore;
import com.shepherdjerred.minecraft.friends.datastore.flatfile.FlatfileDatastore;
import com.shepherdjerred.minecraft.friends.datastore.flatfile.JsonSerializer;
import com.shepherdjerred.minecraft.friends.friend.FriendGetter;
import com.shepherdjerred.minecraft.friends.notification.NotificationCreator;
import com.shepherdjerred.minecraft.friends.notification.OnJoinEventHandler;
import com.shepherdjerred.minecraft.friends.player.identifier.UuidPlayerIdentifier;
import com.shepherdjerred.minecraft.friends.player.identifier.UuidPlayerIdentifierBukkitPlayerGetter;
import com.shepherdjerred.minecraft.friends.player.identifier.UuidPlayerIdentifierFactory;
import com.shepherdjerred.minecraft.friends.player.information.PlayerInformationGetter;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

  @Override
  public void onEnable() {
    var path = getDataFolder() + "/data/friends.json";
    var serializer = new JsonSerializer();
    Datastore<UuidPlayerIdentifier> datastore = new FlatfileDatastore<>(serializer, path);
    var bukkitPlayerGetter = new UuidPlayerIdentifierBukkitPlayerGetter();
    var uuidPlayerIdentifierFactory = new UuidPlayerIdentifierFactory();
    var playerInformationGetter = new PlayerInformationGetter<>(bukkitPlayerGetter);
    var friendGetter = new FriendGetter<>(datastore, playerInformationGetter);
    var notificationCreator = new NotificationCreator<>(friendGetter);

    getServer().getPluginManager().registerEvents(new OnJoinEventHandler<>(notificationCreator, uuidPlayerIdentifierFactory), this);
  }
}
