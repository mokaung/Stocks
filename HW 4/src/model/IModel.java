package model;

import java.util.ArrayList;
import java.util.Calendar;

public interface IModel {
  /**
   * Splits the output of AlphaVantage and adds the information of the stock into the specific fields.
   *
   * @param readable Every transaction of a given stock for one month.
   */
  public void populate(Readable readable, String ticker);

  ArrayList<Calendar> crossover(int window, Calendar date1, Calendar date2, String ticker);

  Double gainOrLoss(Calendar date1, Calendar date2, String ticker);

  Double movingAverage(int window, Calendar date, String ticker);

//  IStock getStock(String ticker);

//  IPortfolio createPortfolio(String ticker, int share);

  // has all teh commands here, but implemented under controller
  // think model as storage
  // user -> controller -> model -> controller -> user

  // controller is the glue from user to functionality therefore command is implemented in model

  // modle doesnt care where the data is being displaced or displaying
  // controller does this.
  // model does nto care.
}
