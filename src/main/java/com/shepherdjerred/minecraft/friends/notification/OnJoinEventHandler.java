package com.shepherdjerred.minecraft.friends.notification;

import com.shepherdjerred.minecraft.friends.player.identifier.PlayerIdentifier;
import com.shepherdjerred.minecraft.friends.player.identifier.PlayerIdentifierFactory;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@AllArgsConstructor
public class OnJoinEventHandler<T extends PlayerIdentifier> implements Listener {
  private final NotificationCreator<T> notificationCreator;
  private final PlayerIdentifierFactory<T> playerIdentifierFactory;

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
    var joiningPlayer = event.getPlayer();
    var joiningPlayerIdentifier = playerIdentifierFactory.get(joiningPlayer);
    var notification = notificationCreator.createNotification(joiningPlayerIdentifier);
    joiningPlayer.sendMessage(notification);
  }
}
