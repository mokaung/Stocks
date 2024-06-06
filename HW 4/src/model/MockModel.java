package model;

import java.util.ArrayList;
import java.util.Calendar;

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
  public ArrayList<Calendar> crossover(int avg, Calendar date1, Calendar date2, String ticker) {
    return null;
  }

  @Override
  public double gainOrLoss(Calendar start, Calendar close, String ticker) {
    return 0;
  }

  @Override
  public double movingAverage(int movingWindow, Calendar date, String ticker) {
    return 0;
  }

  @Override
  public IStock getStock() {
    return null;
  }

  @Override
  public IPortfolio createPortfolio(String ticker, int share) {
    return null;
  }

}
