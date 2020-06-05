package com.shepherdjerred.thestorm.friends.friend;

import com.shepherdjerred.thestorm.friends.player.PlayerIdentifier;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Friend {
  PlayerIdentifier playerIdentifier;
  String name;
  LocalDateTime lastLogin;
  boolean isOnline;
}
