package com.shepherdjerred.thestorm.friends.player.information;

import com.shepherdjerred.thestorm.friends.player.identifier.PlayerIdentifier;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class PlayerInformation {
  PlayerIdentifier playerIdentifier;
  String name;
  LocalDateTime lastLogin;
  boolean isOnline;
}
