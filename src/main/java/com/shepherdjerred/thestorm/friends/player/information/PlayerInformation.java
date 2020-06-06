package com.shepherdjerred.thestorm.friends.player.information;

import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PlayerInformation {
  PlayerIdentifier playerIdentifier;
  String name;
  LocalDateTime lastLogin;
  boolean isOnline;
}
