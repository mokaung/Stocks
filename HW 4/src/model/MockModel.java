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
    log.append("crossover");
    return null;
  }

  @Override
  public double gainOrLoss(Calendar start, Calendar close, String ticker) {
    log.append("gainOrLoss");
    return 0;
  }

  @Override
  public double movingAverage(int movingWindow, Calendar date, String ticker) {
    log.append("movingAverage");
    return 0;
  }

  @Override
  public IPortfolio createPortfolio(String ticker, int share) {
    log.append("createPortfolio");
    return null;
  }

  @Override
  public boolean isValidCalendar(Calendar cal, String ticker) {
    return false;
  }

  @Override
  public boolean isValidTicker(String ticker) {
    return false;
  }

}
