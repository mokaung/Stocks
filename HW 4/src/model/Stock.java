package model;

import java.util.Calendar;

/**
 * Information of a stock that a person owns.
 */
public class Stock implements IStock {
  private final Calendar time;
  private final double open;
  private final double high;
  private final double low;
  private final double close;
  private final int volume;
  private final String ticker;

  /**
   * Create a stock with the specific fields on a specific date.
   * @param time
   * @param open
   * @param high
   * @param low
   * @param close
   * @param volume
   * @param ticker
   */
  public Stock(Calendar time, double open, double high, double low, double close, int volume, String ticker) {
    this.time = time;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.ticker = ticker;
  }

  @Override
  public Calendar getDate() {
    return time;
  }

  @Override
  public double getClose() {
    return close;
  }

  @Override
  public String getTicker() {
    return ticker;
  }
}
