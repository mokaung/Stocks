package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public interface IPortfolioV2 extends IPortfolio {

  void rebalance(LocalDate date, ArrayList<Weight> weights);

  /**
   * Changes the value of a stock on a current date.
   *
   * @param share  the amount changed
   * @param ticker the stock changed
   * @param date   the date changed
   */
  void buyStock(double share, String ticker, LocalDate date);

  void setValue(Map<LocalDate, IStock> stock, Map<LocalDate, Double> share, String ticker);

  String getName();
}
