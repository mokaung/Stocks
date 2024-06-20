package model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import portfolio.IPortfolioV2;
import portfolio.Weight;

/**
 * Interface for an improved model that allows greater functionalities.
 */
public interface IModel2 extends IModel {
  /**
   * Rebalance a portfolio based on the given weights.
   *
   * @param date       the date at which the portfolio is rebalanced.
   * @param weightList a list of the weights. Weight include a ticker and percentage.
   * @param name       name of the portfolio.
   */
  void rebalance(LocalDate date, List<Weight> weightList, String name);

  /**
   * Generates a graph of the portfolio or stock's performance from the start to end date.
   *
   * @param b     true analyzing portfolio, false if stocks.
   * @param name  ticker or name of portfolio.
   * @param start starting date.
   * @param end   ending date.
   * @return a graph of the portfolio or the stock's performance.
   */
  String getPerformance(boolean b, String name, LocalDate start, LocalDate end);

  /**
   * Saves a portfolio.
   *
   * @param portFolioName name of the portfolio being saved.
   * @throws IOException could not save the portfolio as xml.
   */
  void savePortfolio(String portFolioName) throws IOException;

  /**
   * Creates a portfolioV2.
   *
   * @param ticker name of the first stock.
   * @param share  amount of share the stock has.
   * @param name   name of the portfolio.
   * @param date   date portfolio is created.
   * @return a new portfolioV2.
   */
  IPortfolioV2 createPortfolioV2(String ticker, double share, String name, LocalDate date);

  /**
   * Displays the information inside a portfolio.
   *
   * @param name       name of desired portfolio.
   * @param dateString date as a string.
   * @param date       date.
   * @return the given portfolio with its information.
   */
  String portfolioToString(String name, String dateString, LocalDate date);

  /**
   * Add a stock to a given portfolio.
   *
   * @param portFolioName name of portfolio.
   * @param ticker        ticker of stock being added.
   * @param share         amount of share added.
   * @param date          date being dated.
   */
  void addToPortfolioV2(String portFolioName, String ticker, double share, LocalDate date);

  /**
   * Get the vale of the portfolio at a certain date.
   *
   * @param name name of the portfolio.
   * @param cal  date.
   * @return value of the portfolio.
   * @throws IllegalArgumentException if date is invalid.
   */
  double getPortfolioValueV2(String name, LocalDate cal) throws IllegalArgumentException;

  ArrayList<String> getPortfolioNames();
}
