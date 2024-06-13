package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import portfolio.IPortfolio;
import portfolio.IPortfolioV2;
import portfolio.Weight;

/**
 * MockModel used to test controller inputs.
 */
public class MockModel extends ModelImpl2 implements IModel2 {
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

  @Override
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
    return stocks;
  }

  @Override
  public void rebalance(LocalDate date, List<Weight> weightList, String name) {
    log.append("rebalance");
    super.rebalance(date, weightList, name);
  }

  @Override
  public String getPerformance(boolean b, String name, LocalDate start, LocalDate end) {
    log.append("getPerformance");
    return super.getPerformance(b, name, start, end);
  }

  @Override
  public void savePortfolio(String portFolioName) {
    log.append("savePortfolio");
    super.savePortfolio(portFolioName);
  }

  @Override
  public IPortfolioV2 createPortfolioV2(String ticker, double share, String name, LocalDate date) {
    log.append("createPortfolioV2");
    return super.createPortfolioV2(ticker, share, name, date);
  }

  @Override
  public String portfolioToString(String name, String dateString, LocalDate date) {
    log.append("portfolioToString");
    return super.portfolioToString(name, dateString, date);
  }

  @Override
  public void addToPortfolioV2(String portFolioName, String ticker, double share, LocalDate date) {
    log.append("addToPortfolioV2");
    super.addToPortfolioV2(portFolioName, ticker, share, date);
  }

  @Override
  public double getPortfolioValueV2(String name, LocalDate cal) throws IllegalArgumentException {
    log.append("getPortfolioValueV2");
    return super.getPortfolioValueV2(name, cal);
  }
}
