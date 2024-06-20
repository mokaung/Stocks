package view;

import java.time.LocalDate;

/**
 * Interface for connecting the view and controller.
 */
public interface IViewListener {
  /**
   * handles the createPortfolio event from the view.
   *
   * @param ticker selected stock's ticker.
   * @param share  amount of stock at  purchase.
   * @param name   name of portfolio.
   * @param date   date the stock is purchased.
   */
  void handleCreatePortfolio(String ticker, double share, String name, LocalDate date);

  /**
   * handles the savePortfolio event from the view.
   *
   * @param name name fo portfolio.
   */
  void handleSavePortfolio(String name);

  /**
   * handles the getPortfolioVa event from the view.
   *
   * @param name name of portfolio selected.
   * @param date date of evaluation.
   */
  void handleGetPortfolioValue(String name, LocalDate date);

  /**
   * handles the sellPortfolio event from the view.
   * this event can either sell or buy a stock
   *
   * @param ticker Stock's ticker.
   * @param share  amount of stock the user wants to sell. (Positive)
   * @param name   portfolio's name.
   * @param date   date of purchase.
   */
  void handleSellStock(String ticker, double share, String name, LocalDate date);

  /**
   * handles the loadPortfolio event from the view.
   *
   * @param name portfolio's name.
   */
  void handleLoadPortfolio(String name);
}
