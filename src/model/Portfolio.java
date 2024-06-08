package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent a portfolio, which stores a bunch of stocks and their values.
 */
public class Portfolio implements IPortfolio {
  private final Map<String, Map<LocalDate, IStock>> stocks;
  private final Map<String, Integer> share;

  /**
   * constructor that initializes the maps.
   */
  public Portfolio() {
    this.stocks = new HashMap<>();
    this.share = new HashMap<>();
  }

  /**
   * Gives the value of the portfolio at a particular date.
   * @param date date the value is evaluated.
   * @return the value of the portfolio.
   */
  @Override
  public double getValue(LocalDate date) {
    double answer = 0;
    for (Map.Entry<String, Map<LocalDate, IStock>> entry : stocks.entrySet()) {
      IStock stock = entry.getValue().get(date);
      if (stock == null) {
        throw new IllegalArgumentException("Sorry, information for the portfolio at " + date + " is unavailable. Please try another date.");
      }
      if (stock.getDate().equals(date)) {
        double stockShare = share.get(stock.getTicker());
        answer += stock.getClose() * stockShare;
      }
    }
    return answer;
  }

  /**
   * Used when stocks are added into portfolio (including when the portfolio is being
   * created).
   * @param stock  Map of the stock's information on certain date.
   * @param share  how much share a person has.
   * @param ticker ticker for stock.
   */
  @Override
  public void setValue(Map<LocalDate, IStock> stock, int share, String ticker) {
    stocks.put(ticker, stock);
    this.share.put(ticker, share);
  }
  public Map<String, Map<LocalDate, IStock>> getStocks()throws IllegalArgumentException {
    return stocks;
  }

  /**
   * for future implementation.
   * @param stock the stock being added.
   */
  @Override
  public void addStock(IStock stock) {
  }
}
