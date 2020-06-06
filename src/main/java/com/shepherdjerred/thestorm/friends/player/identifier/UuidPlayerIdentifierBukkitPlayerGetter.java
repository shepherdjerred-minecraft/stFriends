package com.shepherdjerred.thestorm.friends.player.identifier;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class UuidPlayerIdentifierBukkitPlayerGetter implements PlayerIdentifierBukkitPlayerGetter<UuidPlayerIdentifier> {

  @Override
  public Player getPlayer(UuidPlayerIdentifier playerIdentifier) {
    return Bukkit.getPlayer(playerIdentifier.getUuid());
  }

  @Override
  public OfflinePlayer getOfflinePlayer(UuidPlayerIdentifier playerIdentifier) {
    return Bukkit.getOfflinePlayer(playerIdentifier.getUuid());
  }
}
