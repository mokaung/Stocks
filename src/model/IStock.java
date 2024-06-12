package model;

import java.time.LocalDate;

/**
 * Interface for representing stock data.
 */
public interface IStock {

  /**
   * Get a stock's date.
   *
   * @return the date
   */
  LocalDate getDate();

  /**
   * Get a stock's closing price.
   *
   * @return the closing price
   */
  double getClose();

  /**
   * Getter for the ticker.
   *
   * @return the ticker symbol
   */
  String getTicker();

  String toJson();
}
