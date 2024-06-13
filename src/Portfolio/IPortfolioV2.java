package Portfolio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import Model.IStock;

/**
 * Extension to the previous portfolio.
 * Added additional functionalities.
 */
public interface IPortfolioV2 extends IPortfolio {

  /**
   * Rebalance the portfolio to the given weights.
   *
   * @param date    date in which rebalance is based on.
   * @param weights list of weights that matches ticker and percentage.
   */
  void rebalance(LocalDate date, ArrayList<Weight> weights);

  /**
   * Changes the value of a stock on a current date.
   *
   * @param share  the amount changed
   * @param ticker the stock changed
   * @param date   the date changed
   */
  void buyStock(double share, String ticker, LocalDate date);

  /**
   * Sets the value of a portfolio.
   *
   * @param stock  all stock information and their dates
   * @param share  amount of shares a stock has on a date
   * @param ticker the stock's ticker.
   */
  void setValueV2(Map<LocalDate, IStock> stock, Map<LocalDate, Double> share, String ticker);

  /**
   * Get the name of the portfolio.
   *
   * @return name of the portfolio.
   */
  String getName();

  String toXml();

  void saveXml(String fileName)throws IOException;


}
