package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
  private final Map<String, Stock> stocks;

  public Portfolio() {
    this.stocks = new HashMap<>();
  }

  public void set(Stock s) {
    stocks.put(s.name, s);
  }

  public double getValueAt(Date d, Stock s) {
    return 0;
  }
}
