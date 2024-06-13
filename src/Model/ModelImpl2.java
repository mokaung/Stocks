package Model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
  public void rebalance(LocalDate date, ArrayList<Weight> weightArrayList, String name) {
    portfoliosV2.get(name).rebalance(date, weightArrayList);
  }

  @Override
  public String portfolioToString(String name, String dateString, LocalDate date){
    return portfoliosV2.get(name).portfolioToString(dateString, date);
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

  @Override
  public String getPerformance(String name, LocalDate start, LocalDate end) {
    IPortfolioV2 portfolio = portfoliosV2.get(name);
    StringBuilder out = new StringBuilder();
    out.append("Performance of portfolio " +
            portfolio.getName() + " from " + start + " to " + end +
            System.lineSeparator() + System.lineSeparator());

    String month = start.getMonth().toString();
    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
      double b = 0.0;
      if (month.equals(date.getMonth().toString())) {
        b += portfolio.getValue(date);
      } else {
        String nameMonth = month.substring(0, 3);
        out.append(nameMonth + " "
                + date.getYear() + ": " + b + System.lineSeparator());
        month = date.getMonth().toString();
      }
    }
    out.append("Scale: * = 1000");
    return out.toString();
  }

  // changes the value into asterisks rounded to the nearest hundred
  // each * is worth 1000
  private String create(double value) {
    StringBuilder b = new StringBuilder();
    while (value >= 1000.0) {
      b.append("*");
      value -= 1000.0;
    }
    if (value >= 500.0) {
      b.append("*");
    }
    return b.toString();
  }
}
