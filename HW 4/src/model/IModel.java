package model;

import java.util.ArrayList;
import java.util.Calendar;

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
  ArrayList<Calendar> crossover(int avg, Calendar date1, Calendar date2, String ticker);

  double gainOrLoss(Calendar start, Calendar close, String ticker);

  double movingAverage(int movingWindow, Calendar date, String ticker);

  IPortfolio createPortfolio(String ticker, int share);

  boolean isValidCalendar(Calendar cal, String ticker);

  boolean isValidTicker(String ticker);

}
