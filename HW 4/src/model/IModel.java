package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public interface IModel {
  /**
   * Splits the output of AlphaVantage and adds the information of the stock into the specific fields.
   *
   * @param readable Every transaction of a given stock for one month.
   */
  void populate(Readable readable, String ticker);

  /**
   * @param avg
   * @param date1
   * @param date2
   * @param ticker
   * @return
   */
  ArrayList<LocalDate> crossover(int avg, LocalDate date1, LocalDate date2, String ticker);

  double gainOrLoss(LocalDate start, LocalDate close, String ticker);

  double movingAverage(int movingWindow, LocalDate date, String ticker);

  IPortfolio createPortfolio(String ticker, int share, String name);

  boolean isValidLocalDate(LocalDate cal, String ticker);

  boolean isValidPortfolio(String name);

  boolean isValidTicker(String ticker);

  void addToPortfolio(String s, String ticker, int share);

  Double getPortfolioValue(String s, LocalDate cal);

  Map<String, Map<LocalDate, IStock>> getStock();
}
