package ru.kostyukov.qs.model;


public class Bid {
  private final int sourceId;
  private final int bidNumber;
  private final double generationTime;

  private double bufferInTime = -1.0;
  private double bufferOutTime = -1.0;
  private double refusalTime = -1.0;
  private double completionTime = -1.0;
  private double serviceStartTime = -1.0;

  public Bid(int sourceId, int bidNumber, double generationTime) {
    this.sourceId = sourceId;
    this.bidNumber = bidNumber;
    this.generationTime = generationTime;
  }

  public int getSourceId() {
    return sourceId;
  }

  public int getBidNumber() {
    return bidNumber;
  }

  public double getGenerationTime() {
    return generationTime;
  }

  public double getBufferInTime() {
    return bufferInTime;
  }

  public void setBufferInTime(double bufferInTime) {
    this.bufferInTime = bufferInTime;
  }

  public double getBufferOutTime() {
    return bufferOutTime;
  }

  public void setBufferOutTime(double bufferOutTime) {
    this.bufferOutTime = bufferOutTime;
  }

  public double getRefusalTime() {
    return refusalTime;
  }

  public void setRefusalTime(double refusalTime) {
    this.refusalTime = refusalTime;
  }

  public double getCompletionTime() {
    return completionTime;
  }

  public void setCompletionTime(double completionTime) {
    this.completionTime = completionTime;
  }

  public double getServiceStartTime() {
    return serviceStartTime;
  }

  public void setServiceStartTime(double serviceStartTime) {
    this.serviceStartTime = serviceStartTime;
  }

  public boolean isRefused() {
    return refusalTime >= 0.0;
  }

  public double getWaitTime() {
    return (bufferInTime >= 0.0 && bufferOutTime >= bufferInTime) ? bufferOutTime - bufferInTime : 0.0;
  }

  public double getServiceTime() {
    return (serviceStartTime >= 0.0 && completionTime >= serviceStartTime) ? completionTime - serviceStartTime : 0.0;
  }

  public double getTotalTimeInSystem() {
    if (isRefused()) {
      return refusalTime - generationTime;
    }

    return (completionTime >= generationTime) ? completionTime - generationTime : 0.0;
  }

  public String getId() {
    return "И" + sourceId + "." + bidNumber;
  }

  @Override
  public String toString() {
    return getId() + " (ген: " + String.format("%.2f", generationTime) + ")";
  }
}
