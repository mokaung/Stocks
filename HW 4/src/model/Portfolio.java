package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Portfolio implements IPortfolio {
  private final Map<String, Map<LocalDate, IStock>> stocks;
  private final Map<String, Integer> share;

  public Portfolio() {
    this.stocks = new HashMap<>();
    this.share = new HashMap<>();
  }


  @Override
  public double getValue(LocalDate date) {
    double answer = 0;
    for (Map.Entry<String, Map<LocalDate, IStock>> entry : stocks.entrySet()) {
      IStock stock = entry.getValue().get(date);
      if (stock.getDate().equals(date)) {
        double stockShare = share.get(stock.getTicker());
        answer += stock.getClose() * stockShare;
      }
    }
    return answer;
  }

  @Override
  public void setValue(Map<LocalDate, IStock> stock, int share, String ticker) {
    stocks.put(ticker, stock);
    this.share.put(ticker, share);
  }

  // to be implemented
//  @Override
//  public void addStock() {
//  }
}
