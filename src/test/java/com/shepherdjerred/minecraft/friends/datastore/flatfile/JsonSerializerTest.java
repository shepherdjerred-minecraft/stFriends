package com.shepherdjerred.minecraft.friends.datastore.flatfile;

import com.shepherdjerred.minecraft.friends.player.identifier.UuidPlayerIdentifier;
import java.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonSerializerTest {
  private JsonSerializer serializer;
  private Map<UuidPlayerIdentifier, Set<UuidPlayerIdentifier>> map;

  private UUID firstUuid = UUID.randomUUID();
  private UUID secondUuid = UUID.randomUUID();
  private UuidPlayerIdentifier firstIdentifier = new UuidPlayerIdentifier(firstUuid);
  private UuidPlayerIdentifier secondIdentifier = new UuidPlayerIdentifier(secondUuid);

  @Before
  public void setup() {
    serializer = new JsonSerializer();
    map = new HashMap<>();
  }

  @Test
  public void serializeReturnsEmptyJsonMapWhenGivenEmptyMap() {
    var result = serializer.serialize(map);
    Assert.assertEquals("{}", result);
  }

  @Test
  public void serializeReturnsMapWithOneEntryJsonMapWhenGivenMapWithOneEntry() {
    map.put(firstIdentifier, Collections.singleton(firstIdentifier));
    var result = serializer.serialize(map);
    var expected = String.format("{\"%s\":[\"%s\"]}", firstUuid, firstUuid);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void deserialize() {}
}
