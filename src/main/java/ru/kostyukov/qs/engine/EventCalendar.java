package ru.kostyukov.qs.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import ru.kostyukov.qs.model.Event;
import ru.kostyukov.qs.model.EventType;

public class EventCalendar {
  private final PriorityQueue<Event> calendar = new PriorityQueue<>();

  public void add(Event event) {
    calendar.add(event);
  }

  public Event poll() {
    return calendar.poll();
  }

  public Event peek() {
    return calendar.peek();
  }

  public boolean isEmpty() {
    return calendar.isEmpty();
  }

  public void clear() {
    calendar.clear();
  }

  public Optional<Event> getGenerationEvent(int sourceId) {
    for (Event event : calendar) {
      if (event.eventType() == EventType.GENERATION && event.sourceId() == sourceId) {
        return Optional.of(event);
      }
    }
    return Optional.empty();
  }

  public Optional<Event> getReleaseEvent(int deviceId) {
    for (Event event : calendar) {
      if (event.eventType() == EventType.DEVICE_RELEASE && event.deviceId() == deviceId) {
        return Optional.of(event);
      }
    }
    return Optional.empty();
  }

  public List<Event> getAllEvents() {
    return new ArrayList<>(calendar);
  }
}
