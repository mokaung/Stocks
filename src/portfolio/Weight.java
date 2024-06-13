package portfolio;

/**
 * Class that matches percentage and ticker for rebalancing.
 */
public class Weight implements IWeight {
  private final double percent;
  private final String ticker;


  public Weight(double share, String ticker) {
    this.percent = share;
    this.ticker = ticker;
  }

  @Override
  public String getTicker() {
    return ticker;
  }

  @Override
  public double getPercent() {
    return percent;
  }
}