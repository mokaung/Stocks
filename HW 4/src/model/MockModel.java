package model;

public class MockModel implements IModel {
  private final StringBuilder log;

  public MockModel() {
    this.log = new StringBuilder();
  }

  @Override
  public void populate(Readable readable, String ticker) {
    log.append(readable);
  }

  @Override
  public void Crossover() {
    log.append("Crossover");
  }

  @Override
  public void GainOrLoss() {
    log.append("Gain or Loss");
  }

  @Override
  public void MovingAverage() {
    log.append("Moving Average");
  }

  @Override
  public IStock getStock() {
    log.append("getStock");
    return null;
  }

  @Override
  public IPortfolio createPortfolio(String ticker, int share) {
    log.append("createPortfolio");
    return null;
  }
}
