package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Modified design of Portfolio that allows for additional commands.
 */
public class PortfolioV2 implements IPortfolioV2 {
  private final String name;
  private final Map<String, Map<LocalDate, IStock>> stocks;
  // from string to int to:
  private final Map<String, Map<LocalDate, Double>> share;
  private final IModel model;

  public PortfolioV2(IModel model, String name) {
    this.name = name;
    stocks = new HashMap<>();
    share = new HashMap<>();
    this.model = model;
  }

  @Override
  public double getValue(LocalDate date) {
    double answer = 0;
    for (Map.Entry<String, Map<LocalDate, IStock>> entry : stocks.entrySet()) {
      IStock stock = entry.getValue().get(date);
      if (stock == null) {
        throw new IllegalArgumentException("Sorry, information for the portfolio at " + date + " is unavailable. Please try another date.");
      }
      if (stock.getDate().equals(date)) {
        double stockShare = share.get(entry.getKey()).get(date);
        answer += stock.getClose() * stockShare;
      }
    }
    return answer;
  }

  //TODO: SOLID principal here
  @Override
  public void setValue(Map<LocalDate, IStock> stock, int share, String ticker) {

  }

  @Override
  public void rebalance(LocalDate date, ArrayList<Weight> weights) {
    String ticker = weights.get(0).getTicker();
    double value = getValue(date);
    for (int i = 0; i < weights.size(); i++) {
      double percent = model.gainOrLoss(date.minusDays(1), date, ticker) / value;
      if (weights.get(i).getShare() != percent) {
        // do math to change

        share.get(ticker).put(date, 0.0);
      }
    }
  }

  public void buyStock(double share, String ticker, LocalDate date) {
    // TODO: implement transaction class that checks if this transaction is valid
    double owned = this.share.get(ticker).get(date);
    this.share.get(ticker).put(date, owned + share);
  }

  @Override
  public void setValue(Map<LocalDate, IStock> stock, Map<LocalDate, Double> share, String ticker) {
    stocks.put(ticker, stock);
    this.share.put(ticker, share);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Map<String, Map<LocalDate, IStock>> getStocks() {
    return stocks;
  }

}
// new pro and model . change controller command