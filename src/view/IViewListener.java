package view;

import java.time.LocalDate;

public interface IViewListener {
  void handleCreatePortfolio(String ticker, double share, String name, LocalDate date);

  void handleSavePortfolio(String name);

  void handleGetPortfolioValue(String name, LocalDate date);

  /**
   * Sell a stock.
   *
   * @param ticker
   * @param share  amount of stock the user wants to sell. (Positive)
   * @param name
   * @param date
   */
  void handleSellStock(String ticker, double share, String name, LocalDate date);

  void handleLoadPortfolio(String name);
}
