package model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Portfolio implements IPortfolio {
  private final Map<String, IStock> stocks;
  private final Map<String, Integer> share;

  public Portfolio() {
    this.stocks = new HashMap<>();
    this.share = new HashMap<>();
  }


  @Override
  public double getValue(Calendar date) {
    double answer = 0;

    for (IStock stock : stocks.values()) {
      if (date.equals(stock.getDate())) {
        double stockShare = share.get(stock.getTicker());
        answer += stock.getClose() * stockShare;
      }
    }
    return answer;
  }

  @Override
  public void setValue(IStock stock, int share) {
    stocks.put(stock.getTicker(), stock);
    this.share.put(stock.getTicker(), share);
  }

}
