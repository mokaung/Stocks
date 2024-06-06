package model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// model is essentially the portfolio
// that stores it
public class ModeImpl implements IModel {
  private final Map<String, Map<Calendar, IStock>> stocks;

  public ModeImpl() {
    stocks = new HashMap<>();
  }

  // new stock and preloaded stock
  // outdated data: saving & preloading make it simple for now
  // step 1: make fetching work first

  // either keep this or create a stock creator class
  // delegation of responsibility

  // make into controller commnad like the others
  @Override
  public void populate(Readable readable, String ticker) {
    Scanner s = new Scanner(readable);
    if (s.hasNext()) {
      // removes the header
      s.next();
    }

    String[] stocks = s.toString().split(System.lineSeparator());
    Map<Calendar, IStock> d = new HashMap<>();

    for (String string : stocks) {
      String[] info = string.split(",");

      String[] date = info[0].split("-");
      Calendar cal = Calendar.getInstance();
      cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[0]), Integer.parseInt(date[1]));

      double open = Double.parseDouble(info[1]);
      double high = Double.parseDouble(info[2]);
      double low = Double.parseDouble(info[3]);
      double close = Double.parseDouble(info[4]);
      int volume = Integer.parseInt(info[5]);
      IStock stock = new Stock(cal, open, high, low, close, volume, ticker);

      d.put(cal, stock);
    }
    this.stocks.put(ticker, d);
  }

  @Override
  public  crossover() {

  }

  @Override
  public Double gainOrLoss() {
    double startingClosePrice = model.tempGetClose(date1);
    double endingClosePrice = model.tempGetClose(date2);
    double diff = endingClosePrice - startingClosePrice;
    return diff;
  }

  @Override
  public void movingAverage() {

  }

  // change to fix above commands the best
  @Override
  public IStock getStock() {
    return null;
  }

  @Override
  public IPortfolio createPortfolio(String ticker, int share) {
    Portfolio p = new Portfolio();
    Map<Calendar, IStock> info = stocks.get(ticker);
    p.setValue(info, share, ticker);

    return p;
  }
}
