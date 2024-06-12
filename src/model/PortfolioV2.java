package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Modified design of Portfolio that allows for additional commands.
 */
public class PortfolioV2 extends Portfolio implements IPortfolioV2 {
  private final Map<String, Map<LocalDate, IStock>> stocks;
  // from string to int to:
  private final Map<String, Map<LocalDate, Integer>> share;

  public PortfolioV2() {
    stocks = new HashMap<>();
    share = new HashMap<>();
  }

  @Override
  public Portfolio rebalance() {
    return null;
  }

  @Override
  public double getValue(LocalDate date) {
    double answer = 0;
    for (Map.Entry<String, Map<LocalDate, IStock>> entry : stocks.entrySet()) {
      IStock stock = entry.getValue().get(date);
      if (stock == null) {
        throw new IllegalArgumentException("Sorry, information for the portfolio at " + date + " is unavailable. Please try another date.");
      }
      if (stock.getDate().equals(date)) {
        double stockShare = share.get(entry.getKey()).get(date);
        answer += stock.getClose() * stockShare;
      }
    }
    return answer;
  }
}
