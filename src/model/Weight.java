package model;

public class Weight {
  private final double share;
  private final String ticker;


  public Weight(double share, String ticker) {
    this.share = share;
    this.ticker = ticker;
  }

  public String getTicker() {
    return ticker;
  }

  public double getShare() {
    return share;
  }
}
