package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 * This interface for the operations that can be performed.
 */
public interface IModel {

  /**
   * Populates the model with stock data extracted from a readable source.
   *
   * @param readable stock information
   * @param ticker   ticker symbol for the stock
   */
  void populate(Readable readable, String ticker);

  /**
   * Finds the crossover points between the moving average and the actual stock prices.
   *
   * @param avg    the window size of the moving average
   * @param date1  the start date of the analysis period
   * @param date2  the end date of the analysis period
   * @param ticker ticker symbol for the stock
   * @return a list of dates when crossovers occur between the moving average and the stock prices
   */
  ArrayList<LocalDate> crossover(int avg, LocalDate date1, LocalDate date2, String ticker);

  /**
   * Calculates the gain or loss of a stock between two dates.
   *
   * @param start  the start date
   * @param close  the end date
   * @param ticker ticker symbol for the stock
   * @return the percentage gain or loss during the period
   */
  double gainOrLoss(LocalDate start, LocalDate close, String ticker);

  /**
   * Find a stock's moving average over a window of time.
   *
   * @param movingWindow the number of days in the moving average window
   * @param date         the date for which to calculate the moving average
   * @param ticker       ticker symbol for the stock
   * @return the moving average price of the stock
   */
  double movingAverage(int movingWindow, LocalDate date, String ticker);

  /**
   * Creates a new stock portfolio with an initial stock.
   *
   * @param ticker ticker symbol for the stock
   * @param share  the number of shares of the ticker to include
   * @param name   the name of the new portfolio
   * @return the newly created portfolio
   */
  IPortfolio createPortfolio(String ticker, int share, String name);

  /**
   * Checks if the given ticker is in stocks.
   *
   * @param cal    date
   * @param ticker ticker symbol for the stock
   * @return if the ticker is in stocks.
   */
  boolean isValidLocalDate(LocalDate cal, String ticker);

  /**
   * Validates whether a portfolio with the specified name exists.
   *
   * @param name the name of the portfolio
   * @return if the portfolio name exists
   */
  boolean isValidPortfolio(String name);

  /**
   * Checks if the given ticker is in stocks.
   *
   * @param ticker ticker symbol for the stock
   * @return if the ticker is in stocks
   */
  boolean isValidTicker(String ticker);

  /**
   * Adds stocks to an existing portfolio.
   *
   * @param s      the name of the portfolio
   * @param ticker ticker symbol for the stock
   * @param share  the number of shares to add
   */
  void addToPortfolio(String s, String ticker, int share);

  /**
   * Retrieves the value of a portfolio on a given date.
   *
   * @param s   the name of the portfolio
   * @param cal date to evaluate the portfolio at
   * @return the value of the portfolio
   */
  Double getPortfolioValue(String s, LocalDate cal);

  /**
   * Getter for stocks.
   *
   * @return stocks
   */
  Map<String, Map<LocalDate, IStock>> getStock();

}
