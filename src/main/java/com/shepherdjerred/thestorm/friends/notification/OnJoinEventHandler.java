package com.shepherdjerred.thestorm.friends.notification;

import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifierFactory;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.FileNotFoundException;
import java.util.Optional;

@AllArgsConstructor
public class OnJoinEventHandler<T extends PlayerIdentifier> implements Listener {

    private final NotificationCreator<T> notificationCreator;
    private final PlayerIdentifierFactory<T> playerIdentifierFactory;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws FileNotFoundException {
        var joiningPlayer = event.getPlayer();
        var joiningPlayerIdentifier = playerIdentifierFactory.get(joiningPlayer);
        var notification = notificationCreator.createNotification(joiningPlayerIdentifier);
        joiningPlayer.sendMessage(notification);
    }
}
