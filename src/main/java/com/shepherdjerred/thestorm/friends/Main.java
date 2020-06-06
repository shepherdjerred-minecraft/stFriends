package com.shepherdjerred.thestorm.friends;

import com.shepherdjerred.thestorm.friends.datastore.Datastore;
import com.shepherdjerred.thestorm.friends.datastore.JsonDatastore;
import com.shepherdjerred.thestorm.friends.friend.FriendGetter;
import com.shepherdjerred.thestorm.friends.notification.NotificationCreator;
import com.shepherdjerred.thestorm.friends.notification.OnJoinEventHandler;
import com.shepherdjerred.thestorm.friends.player.identifier.UuidPlayerIdentifier;
import com.shepherdjerred.thestorm.friends.player.identifier.UuidPlayerIdentifierBukkitPlayerGetter;
import com.shepherdjerred.thestorm.friends.player.identifier.UuidPlayerIdentifierFactory;
import com.shepherdjerred.thestorm.friends.player.information.PlayerInformationGetter;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        var path = getDataFolder() + "/data/friends.json";
        Datastore<UuidPlayerIdentifier> datastore = new JsonDatastore<>(path);
        var bukkitPlayerGetter = new UuidPlayerIdentifierBukkitPlayerGetter();
        var uuidPlayerIdentifierFactory = new UuidPlayerIdentifierFactory();
        var playerInformationGetter = new PlayerInformationGetter<>(bukkitPlayerGetter);
        var friendGetter = new FriendGetter<>(datastore, playerInformationGetter);
        var notificationCreator = new NotificationCreator<>(friendGetter);

        getServer().getPluginManager().registerEvents(new OnJoinEventHandler<>(
                notificationCreator,
                uuidPlayerIdentifierFactory), this);
    }
}
