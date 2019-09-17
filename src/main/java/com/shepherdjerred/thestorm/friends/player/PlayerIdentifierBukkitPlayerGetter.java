package com.shepherdjerred.thestorm.friends.player;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface PlayerIdentifierBukkitPlayerGetter<T extends PlayerIdentifier> {
  Player getPlayer(T playerIdentifier);

  OfflinePlayer getOfflinePlayer(T playerIdentifier);
}
