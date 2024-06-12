package model;

public class Weight {
  private final double percent;
  private final String ticker;


  public Weight(double share, String ticker) {
    this.percent = share;
    this.ticker = ticker;
  }

  public String getTicker() {
    return ticker;
  }

  public double getPercent() {
    return percent;
  }
}
