package com.segment.analytics;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import com.segment.analytics.internal.gson.AutoGson;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;

@AutoValue @AutoGson //
public abstract class TrackPayload implements Payload {
  public static Builder builder(String event) {
    return new Builder(event);
  }

  public abstract String event();

  @Nullable public abstract Map<String, Object> properties();

  public static class Builder extends PayloadBuilder<TrackPayload, Builder> {
    String event;
    Map<String, Object> properties;

    private Builder(String event) {
      if (event == null) {
        throw new NullPointerException("Null event");
      }
      this.event = event;
    }

    public Builder properties(Map<String, Object> properties) {
      if (properties == null) {
        throw new NullPointerException("Null properties");
      }
      this.properties = ImmutableMap.copyOf(properties);
      return this;
    }

    @Override Builder self() {
      return this;
    }

    @Override TrackPayload realBuild() {
      return new AutoValue_TrackPayload(Type.TRACK, UUID.randomUUID(), new Date(), context,
          anonymousId, userId, event, properties);
    }
  }
}
