package com.shepherdjerred.minecraft.friends.datastore.flatfile;

import com.google.gson.*;
import com.google.gson.JsonSerializer;
import com.shepherdjerred.minecraft.friends.player.identifier.UuidPlayerIdentifier;
import java.lang.reflect.Type;
import java.util.UUID;

public class UuidPlayerIdentifierJsonSerializer implements JsonSerializer<UuidPlayerIdentifier>, JsonDeserializer<UuidPlayerIdentifier> {

  @Override
  public UuidPlayerIdentifier deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return new UuidPlayerIdentifier(UUID.fromString(json.getAsString()));
  }

  @Override
  public JsonElement serialize(UuidPlayerIdentifier src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(src.getUuid().toString());
  }
}
