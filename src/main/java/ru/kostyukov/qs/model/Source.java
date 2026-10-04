package ru.kostyukov.qs.model;

import java.util.Random;

public class Source {
  private final int id;
  private final double lambda;
  private final Random random;
  private int generatedBidsCount = 0;
  private int refusedBidsCount = 0;

  public Source(int id, double lambda) {
    this.id = id;
    this.lambda = lambda;
    this.random = new Random();
  }

  public Source(int id, double lambda, long seed) {
    this.id = id;
    this.lambda = lambda;
    this.random = new Random(seed);
  }

  public double generateNextInterval() {
    double r = random.nextDouble();
    while (r == 0.0) {
      r = random.nextDouble();
    }
    return (-1 / lambda) * Math.log(r);
  }

  public Bid generateBid(double currentTime) {
    generatedBidsCount++;
    return new Bid(this.id, this.generatedBidsCount, currentTime);
  }

  public void incrementRefusedCount() {
    refusedBidsCount++;
  }

  public int getId() {
    return id;
  }

  public double getLambda() {
    return lambda;
  }

  public int getGeneratedBidsCount() {
    return generatedBidsCount;
  }

  public int getRefusedBidsCount() {
    return refusedBidsCount;
  }

  @Override
  public String toString() {
    return "И"
        + id
        + " [lambda="
        + lambda
        + ", сген="
        + generatedBidsCount
        + ", отказ="
        + refusedBidsCount
        + ']';
  }
}
