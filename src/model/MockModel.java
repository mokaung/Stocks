package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import portfolio.IPortfolio;
import portfolio.IPortfolioV2;

/**
 * MockModel used to test controller inputs.
 */
public class MockModel extends ModelImpl implements IModel {
  private Map<String, IPortfolio> portfolios;
  private final StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void populate(Readable readable, String ticker) {
    log.append("populate");
    super.populate(readable, ticker);
  }

  @Override
  public ArrayList<LocalDate> crossover(int avg, LocalDate date1, LocalDate date2, String ticker) {
    log.append("crossover");
    return null;
  }

  @Override
  public double gainOrLoss(LocalDate start, LocalDate close, String ticker) {
    log.append("gainOrLoss");
    return 0;
  }

  @Override
  public double movingAverage(int movingWindow, LocalDate date, String ticker) {
    log.append("movingAverage");
    return 0;
  }

  @Override
  public IPortfolioV2 createPortfolio(String ticker, int share, String name) {
    log.append("createPortfolio");
    return null;
  }

  @Override
  public boolean isInvalidLocalDate(LocalDate cal, String ticker) {
    log.append("isValidLocalDate");
    return super.isInvalidLocalDate(cal, ticker);
  }

  @Override
  public boolean isInvalidTicker(String ticker) {
    log.append("isValidTicker");
    return super.isInvalidTicker(ticker);
  }

  @Override
  public boolean isInvalidPortfolio(String name) {
    log.append("isValidPortfolio");
    return true;
  }

  public void addToPortfolio(String s, String ticker, int share) {
    log.append("addToPortfolio");
  }

  public Double getPortfolioValue(String s, LocalDate cal) {
    log.append("getPortfolioValue");
    return 0.0;
  }

  @Override
  public Map<String, Map<LocalDate, IStock>> getStock() {
    log.append("getStock");
    return Map.of();
  }

}
