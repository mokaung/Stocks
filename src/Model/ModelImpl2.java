package Model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Performance.IPerformance;
import Model.Performance.PortfolioPerformance;
import Model.Performance.StockPerformance;
import Portfolio.IPortfolioV2;
import Portfolio.PortfolioV2;
import Portfolio.Weight;

/**
 * Model for the program. Stores all the information. Is run from Controller when user wants
 * something.
 */
public class ModelImpl2 extends ModelImpl implements IModel2 {
  private final Map<String, IPortfolioV2> portfoliosV2;

  /**
   * Constructor for creating a blank model.
   */
  public ModelImpl2() {
    super();
    portfoliosV2 = new HashMap<>();
  }

  /**
   * To create a portfolio of stocks.
   *
   * @param ticker ticker symbol for the stock
   * @param share  the number of shares of the ticker to include
   * @param name   the name of the new portfolio
   * @return a new portfolio.
   */
  @Override
  public IPortfolioV2 createPortfolio(String ticker, int share, String name) {
    PortfolioV2 p = new PortfolioV2(name);
    Map<LocalDate, IStock> info = stocks.get(ticker);
    p.setValue(info, share, ticker);
    portfoliosV2.put(name, p);
    return p;
  }

  @Override
  public IPortfolioV2 createPortfolioV2(String ticker, double share, String name, LocalDate date) {
    PortfolioV2 p = new PortfolioV2(name);
    Map<LocalDate, IStock> info = stocks.get(ticker);
    Map<LocalDate, Double> shareMap = new HashMap<>();
    shareMap.put(date, share);
    p.setValueV2(info, shareMap, ticker);
    portfoliosV2.put(name, p);
    return p;
  }


  /**
   * Adds stocks from the loaded ones into an existing stock.
   *
   * @param portFolioName the name of the portfolio
   * @param ticker        ticker symbol for the stock
   * @param share         the number of shares to add
   */
  @Override
  public void addToPortfolio(String portFolioName, String ticker, int share) {
    portfoliosV2.get(portFolioName).setValue(stocks.get(ticker), share, ticker);
  }

  @Override
  public void addToPortfolioV2(String portFolioName, String ticker, double share, LocalDate date) {
    Map<LocalDate, Double> shareMap = new HashMap<>();
    shareMap.put(date, share);
    portfoliosV2.get(portFolioName).setValueV2(stocks.get(ticker), shareMap, ticker);
  }

  @Override
  public void rebalance(LocalDate date, ArrayList<Weight> weightArrayList, String name) {
    portfoliosV2.get(name).rebalance(date, weightArrayList);
  }

  @Override
  public String getPerformance(boolean isPortfolio, String name, LocalDate start, LocalDate end) {
    if (isPortfolio) {
      IPortfolioV2 p = portfoliosV2.get(name);
      return new PortfolioPerformance(p).getPerformance(start, end);
    } else {
      Map<LocalDate, IStock> s = stocks.get(name);
      return new StockPerformance(s).getPerformance(start, end);
    }
  }

  @Override
  public void savePortfolio(String portFolioName) {
    try {
      portfoliosV2.get(portFolioName).saveXml(portFolioName);
    } catch (IOException e) {
      throw new IllegalStateException("Could not save portfolio.");
    }
  }

  @Override
  public Double getPortfolioValueV2(String name, LocalDate cal) throws IllegalArgumentException {
    return portfoliosV2.get(name).getValue(cal);
  }

  @Override
  public boolean isInvalidPortfolio(String name) {
    return !portfoliosV2.containsKey(name);
  }


}
