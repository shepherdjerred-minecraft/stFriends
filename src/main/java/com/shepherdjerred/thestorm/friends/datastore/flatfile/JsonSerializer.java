package com.shepherdjerred.thestorm.friends.datastore.flatfile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shepherdjerred.thestorm.friends.player.identifier.UuidPlayerIdentifier;
import java.util.Map;
import java.util.Set;

public class JsonSerializer implements Serializer<UuidPlayerIdentifier> {
  private final Gson gson;

  public JsonSerializer() {
    var builder = new GsonBuilder();
    builder.enableComplexMapKeySerialization();
    builder.registerTypeAdapter(UuidPlayerIdentifier.class, new UuidPlayerIdentifierJsonSerializer());
    gson = builder.create();
  }

  @Override
  public String serialize(Map<UuidPlayerIdentifier, Set<UuidPlayerIdentifier>> map) {
    return gson.toJson(map, new TypeToken<Map<UuidPlayerIdentifier, Set<UuidPlayerIdentifier>>>() {}.getType());
  }

  @Override
  public Map<UuidPlayerIdentifier, Set<UuidPlayerIdentifier>> deserialize(String string) {
    return gson.fromJson(string, new TypeToken<Map<UuidPlayerIdentifier, Set<UuidPlayerIdentifier>>>() {}.getType());
  }
}
