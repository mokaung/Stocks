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

  ArrayList<Calendar> crossover(int avg, Calendar date1, Calendar date2, String ticker);

  double gainOrLoss(Calendar start, Calendar close, String ticker);

  double movingAverage(int movingWindow, Calendar date, String ticker);

  IStock getStock();

  IPortfolio createPortfolio(String ticker, int share);

  boolean isValidTicker(String ticker);
  boolean isValidCalendar(Calendar cal, String ticker);

  // has all teh commands here, but implemented under controller
  // think model as storage
  // user -> controller -> model -> controller -> user

  // controller is the glue from user to functionality therefore command is implemented in model

  // modle doesnt care where the data is being displaced or displaying
  // controller does this.
  // model does nto care.
}
