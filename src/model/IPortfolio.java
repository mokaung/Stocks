package model;

import java.time.LocalDate;
import java.util.Map;

/**
 * Interface for portfolios. Allows all portfolios to get and set values.
 */
public interface IPortfolio {

  /**
   * Get the value of a portfolio on the date.
   *
   * @param date date the value is evaluated.
   * @return the value of the portfolio.
   */
  double getValue(LocalDate date);

  /**
   * Adds a stock's information into portfolio sorted by date and share.
   *
   * @param stock  Map of the stock's information on certain date.
   * @param share  how much share a person has.
   * @param ticker ticker for stock.
   */
  void setValue(Map<LocalDate, IStock> stock, int share, String ticker);

  /**
   * Getter for the stocks in a portfolio.
   *
   * @return a map from date to stocks.
   */
  Map<String, Map<LocalDate, IStock>> getStocks();
}
