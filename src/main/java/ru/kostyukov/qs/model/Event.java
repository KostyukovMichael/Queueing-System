package ru.kostyukov.qs.model;

import java.util.Objects;

public record Event(double time, EventType eventType, int sourceId, int deviceId)
    implements Comparable<Event> {
  public Event {
    if (time < 0) {
      throw new IllegalArgumentException("Time must be positive: " + time);
    }
    Objects.requireNonNull(eventType, "event type must not be null");
  }

  public static Event generation(double time, int sourceId) {
    return new Event(time, EventType.GENERATION, sourceId, -1);
  }

  public static Event deviceRelease(double time, int deviceId) {
    return new Event(time, EventType.DEVICE_RELEASE, -1, deviceId);
  }

  @Override
  public int compareTo(Event other) {
    int timeComparison = Double.compare(this.time, other.time);
    if (timeComparison != 0) {
      return timeComparison;
    }

    int typeComparison = this.eventType.compareTo(other.eventType);
    if (typeComparison != 0) {
      return typeComparison;
    }

    if (this.eventType == EventType.GENERATION) {
      return Integer.compare(this.sourceId, other.sourceId);
    } else {
      return Integer.compare(this.deviceId, other.deviceId);
    }
  }
}
