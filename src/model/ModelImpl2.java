package model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.performance.PortfolioPerformance;
import model.performance.StockPerformance;
import portfolio.IPortfolioV2;
import portfolio.PortfolioV2;
import portfolio.Weight;

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

  @Override
  public void addToPortfolioV2(String portFolioName, String ticker, double share, LocalDate date) {
    Map<LocalDate, Double> shareMap = new HashMap<>();
    shareMap.put(date, share);
    portfoliosV2.get(portFolioName).setValueV2(stocks.get(ticker), shareMap, ticker);
  }

  @Override
  public void rebalance(LocalDate date, List<Weight> weightyList, String name) {
    portfoliosV2.get(name).rebalance(date, weightyList);
  }

  @Override
  public String portfolioToString(String name, String dateString, LocalDate date) {
    return portfoliosV2.get(name).portfolioToString(dateString, date);
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
  public double getPortfolioValueV2(String name, LocalDate cal) throws IllegalArgumentException {
    return portfoliosV2.get(name).getValue(cal);
  }

  @Override
  public boolean isInvalidPortfolio(String name) {
    return !portfoliosV2.containsKey(name);
  }

  public ArrayList<String> getPortfolioNames() {
    ArrayList<String> nameList = new ArrayList<String>();
    for (String key : portfoliosV2.keySet()) {
      nameList.add(key);
    }
    return nameList;
  }

}
