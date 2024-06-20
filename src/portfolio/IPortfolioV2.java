package portfolio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import model.IStock;

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
  void rebalance(LocalDate date, List<Weight> weights);

  /**
   * Changes the value of a stock on a current date.
   *
   * @param share  the amount changed
   * @param ticker the stock changed
   * @param date   the date changed
   */
  void buyStock(double share, String ticker, LocalDate date);

  /**
   * Changes the value of a stock on a current date.
   *
   * @param share  the amount changed
   * @param ticker the stock changed
   * @param date   the date changed
   */
  void buyStockGUI(double share, String ticker, LocalDate date, Map<LocalDate, IStock> stockMap,
                Map<LocalDate, Double> shareMap);

  /**
   * Sells a stock.
   * @param share  the amount changed
   * @param ticker the stock changed
   * @param date   the date changed
   */
  void sellStock(double share, String ticker, LocalDate date);

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

  String portfolioToString(String dateString, LocalDate date);

  String toXml();

  void saveXml(String fileName) throws IOException;


}
