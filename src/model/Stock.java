package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Information of a stock that a person owns.
 */
public class Stock implements IStock {
  private final LocalDate time;
  private final double open;
  private final double high;
  private final double low;
  private final double close;
  private final int volume;
  private final String ticker;

  /**
   * Create a stock with the specific fields on a specific date.
   *
   * @param time   date of stock
   * @param open   open price
   * @param high   high price
   * @param low    low price
   * @param close  closing price
   * @param volume volume exchanged
   * @param ticker ticker symbol
   */
  public Stock(LocalDate time, double open, double high, double low, double close, int volume, String ticker) {
    this.time = time;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.ticker = ticker;
  }

  @Override
  public LocalDate getDate() {
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

  /**
   * Overrides equals to allow comparison of 2 stocks.
   *
   * @param o other stock.
   * @return true if stocks are equal, false if not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Stock)) {
      return false;
    }
    Stock stock = (Stock) o;

    return time.equals(stock.time) &&
            Double.compare(open, stock.open) == 0 &&
            Double.compare(high, stock.high) == 0 &&
            Double.compare(low, stock.low) == 0 &&
            Double.compare(close, stock.close) == 0 &&
            volume == stock.volume &&
            ticker.equals(stock.ticker);
  }

  /**
   * Overrides hashcode for equals().
   *
   * @return hashcode info.
   */
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + time.hashCode();
    result = 31 * result + Double.hashCode(open);
    result = 31 * result + Double.hashCode(high);
    result = 31 * result + Double.hashCode(low);
    result = 31 * result + Double.hashCode(close);
    result = 31 * result + Integer.hashCode(volume);
    result = 31 * result + ticker.hashCode();
    return result;
  }

  @Override
  public String toJson() {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    return ("{"
            + "\"time\":\""
            + time.format(formatter)
            + "\","
            + "\"open\":"
            + open
            + ","
            + "\"high\":"
            + high
            + ","
            + "\"low\":"
            + low
            + ","
            + "\"close\":"
            + close
            + ","
            + "\"volume\":"
            + volume
            + ","
            + "\"ticker\":\""
            + ticker
            + "\""
            + "}");
  }
}
