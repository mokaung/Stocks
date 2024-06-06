package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// model is essentially the portfolio
// that stores it
public class ModelImpl implements IModel {
  private final Map<String, Map<Calendar, IStock>> stocks;

  public ModelImpl() {
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
  public ArrayList<Calendar> crossover(int avg, Calendar date1, Calendar date2) {
    ArrayList<Calendar> results = new ArrayList<Calendar>();
//    //assuming we can subtract dates
//    for (int i = date1; i < (date2); i++) {
//      MovingAverage response = new MovingAverage(avg, i);
//      //assuming MovingAverage's run will return a double
//      double movingAverageResult = response.run(model);
//      if (movingAverageResult >= model.tempGetClose(i)) ;
//      results.add(i);
//    }
    return results;
  }

  @Override
  public double gainOrLoss(String ticker, Calendar start, Calendar close) {
    double startingClosePrice = stocks.get(ticker).get(start).getClose();
    double endingClosePrice = stocks.get(ticker).get(close).getClose();
    return endingClosePrice - startingClosePrice;
  }

  @Override
  public double movingAverage(int movingAverage, Calendar date, String ticker) {
    double movingSum = 0;
    double answer;
    for (int i = movingAverage; i > 0; i--) {
      //apparently you can calculate moving averages on closing, opening, high, low
      //currently average is based on closing prices
      //currently uses date-i, assumes that date will be an int
      movingSum = movingSum + stocks.get(ticker).get(date - 1).getClose();
    }
    answer = movingSum / movingAverage;
    return answer;
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
