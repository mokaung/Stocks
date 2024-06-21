package view;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interface for listening to view events in the GUI.
 */
public interface IViewListener {

  /**
   * Initializes the model and starts the view rendering.
   *
   * @param model the model to be initialized.
   */
  void init(model.IModel2 model);

  /**
   * Lists the names of the portfolios.
   *
   * @return an ArrayList of portfolio names.
   * @throws IllegalArgumentException if an error occurs while retrieving the portfolio names.
   */
  ArrayList<String> listPortfolios() throws IllegalArgumentException;

  /**
   * Lists the names of the stocks.
   *
   * @return an ArrayList of stock names.
   * @throws IllegalArgumentException if an error occurs while retrieving the stock names.
   */
  ArrayList<String> listStocks() throws IllegalArgumentException;

  /**
   * Gets the value of a portfolio on a specific date.
   *
   * @param name the name of the portfolio.
   * @param date the date for which to get the portfolio value.
   * @return
   * @throws IllegalArgumentException if the portfolio is invalid or the date is invalid.
   */
  String handleGetPortfolioValue(String name, LocalDate date) throws IllegalArgumentException;

  /**
   * Buys a stock for a portfolio on a specific date.
   *
   * @param name   the name of the portfolio.
   * @param ticker the stock ticker.
   * @param share  the number of shares to buy.
   * @param date1  the date of the transaction.
   * @throws IllegalArgumentException if the portfolio, ticker, or date is invalid.
   */
  void handleBuyStock(String name, String ticker, int share, LocalDate date1) throws IllegalArgumentException;

  /**
   * Sells a stock from a portfolio on a specific date.
   *
   * @param name   the name of the portfolio.
   * @param ticker the stock ticker.
   * @param share  the number of shares to sell.
   * @param date1  the date of the transaction.
   * @throws IllegalArgumentException if the portfolio, ticker, or date is invalid.
   */
  void handleSellStock(String name, String ticker, double share, LocalDate date1) throws IllegalArgumentException;

  /**
   * Populates stock data for a specific ticker.
   *
   * @param ticker the stock ticker.
   * @throws IllegalArgumentException if the stock data cannot be loaded.
   */
  void handlePopulateStock(String ticker) throws IllegalArgumentException;

  /**
   * Populates stock data from a CSV file for a specific ticker.
   *
   * @param ticker the stock ticker.
   * @throws IllegalArgumentException if the stock data cannot be loaded.
   */
  void handleCSVStock(String ticker) throws IllegalArgumentException;

  /**
   * Saves a portfolio.
   *
   * @param name the name of the portfolio to save.
   * @throws IllegalArgumentException if the portfolio is invalid or an I/O error occurs.
   */
  void handleSavePortfolio(String name) throws IllegalArgumentException;

  /**
   * Lists the names of the loadable portfolios.
   *
   * @return an ArrayList of loadable portfolio names.
   * @throws IllegalArgumentException if an error occurs while retrieving the portfolio names.
   */
  ArrayList<String> listLoadablePortfolios() throws IllegalArgumentException;

  /**
   * Loads a portfolio from an XML file.
   *
   * @param name the name of the portfolio to load.
   * @throws IllegalArgumentException if the portfolio cannot be loaded.
   */
  void handleLoadPortfolio(String name) throws IllegalArgumentException;

  /**
   * Creates a new portfolio with the specified stock and share.
   *
   * @param ticker the stock ticker.
   * @param share  the number of shares.
   * @param name   the name of the portfolio.
   * @param date1  the date of creation.
   * @throws IllegalArgumentException if the ticker or date is invalid.
   */
  void handleCreatePortfolio(String ticker, double share, String name, LocalDate date1) throws IllegalArgumentException;

  /**
   * Adds stock to an existing portfolio.
   *
   * @param ticker the stock ticker.
   * @param share  the number of shares.
   * @param name   the name of the portfolio.
   * @param date1  the date of the addition.
   * @throws IllegalArgumentException if the ticker or date is invalid.
   */
  void handleAddToPortfolio(String ticker, double share, String name, LocalDate date1) throws IllegalArgumentException;
}
