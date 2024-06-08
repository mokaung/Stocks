package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MockModel used to test controller inputs.
 */
public class MockModel implements IModel {
  private Map<String, IPortfolio> portfolios;
  private final StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void populate(Readable readable, String ticker) {
    log.append(readable);
  }

  @Override
  public ArrayList<LocalDate> crossover(int avg, LocalDate date1, LocalDate date2, String ticker) {
    log.append("crossover");
    return null;
  }

  @Override
  public double gainOrLoss(LocalDate start, LocalDate close, String ticker) {
    log.append("start: "+start+" "+"close: "+close+" "+"ticker: "+ticker+" ");
    return 0;
  }

  @Override
  public double movingAverage(int movingWindow, LocalDate date, String ticker) {
    log.append("movingAverage");
    return 0;
  }

  @Override
  public IPortfolio createPortfolio(String ticker, int share, String name) {
    log.append("createPortfolio");
    return null;
  }

  @Override
  public boolean isValidLocalDate(LocalDate cal, String ticker) {
    log.append("isValidLocalDate");
    return false;
  }

  @Override
  public boolean isValidTicker(String ticker) {
    log.append("isValidTicker");
    return false;
  }

  @Override
  public boolean isValidPortfolio(String name) {
    return false;
  }

  public void addToPortfolio(String s, String ticker, int share) {
  }

  public Double getPortfolioValue(String s, LocalDate cal) {
    return 0.0;
  }

  @Override
  public Map<String, Map<LocalDate, IStock>> getStock() {
    return Map.of();
  }

}