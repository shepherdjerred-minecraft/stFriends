package com.shepherdjerred.thestorm.friends.friend;

import com.shepherdjerred.thestorm.friends.player.PlayerIdentifier;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Friend {

  @Getter
  private final PlayerIdentifier playerIdentifier;
  @Getter
  private final String name;
  @Getter
  private final LocalDateTime lastLogin;
  @Getter
  private final boolean isOnline;

}
