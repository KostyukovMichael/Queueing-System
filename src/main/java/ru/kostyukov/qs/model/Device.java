package ru.kostyukov.qs.model;

import java.util.Random;

public class Device {
  private final int id;
  private final double a;
  private final double b;
  private final Random random;
  private Bid currentBid = null;
  private double releaseTime = -1.0;
  private double totalWorkTime = 0.0;
  private int servicedBidsCount = 0;

  public Device(int id, double a, double b) {
    this.id = id;
    this.a = a;
    this.b = b;
    this.random = new Random();
  }

  public Device(int id, double a, double b, long seed) {
    this.id = id;
    this.a = a;
    this.b = b;
    this.random = new Random(seed);
  }

  public boolean isFree() {
    return currentBid == null;
  }

  public double calculateServiceTime() {
    return a + (b - a) * random.nextDouble();
  }

  public double startService(Bid bid, double currentTime) {
    currentBid = bid;
    currentBid.setServiceStartTime(currentTime);
    double duration = calculateServiceTime();
    releaseTime = currentTime + duration;
    totalWorkTime += duration;

    return releaseTime;
  }

  public Bid finishService(double currentTime) {
    if (currentBid == null) {
      return null;
    }

    currentBid.setCompletionTime(currentTime);
    servicedBidsCount++;

    Bid finishedBid = currentBid;
    currentBid = null;
    releaseTime = -1.0;

    return finishedBid;
  }

  public int getId() {
    return id;
  }

  public double getA() {
    return a;
  }

  public double getB() {
    return b;
  }

  public Bid getCurrentBid() {
    return currentBid;
  }

  public double getReleaseTime() {
    return releaseTime;
  }

  public double getTotalWorkTime() {
    return totalWorkTime;
  }

  public int getServicedBidsCount() {
    return servicedBidsCount;
  }

  @Override
  public String toString() {
    return "П"
        + id
        + (isFree()
            ? " [Свободен]"
            : " [Занят заявкой "
                + currentBid.getId()
                + " до "
                + String.format("%.2f", releaseTime)
                + "]");
  }
}
