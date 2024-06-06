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

    String stockLine = s.next();
    String[] parts = stockLine.split(",");
    String[] dateParts = parts[0].split("-");
    //String[] stocks = s.toString().split(System.lineSeparator());
    Map<Calendar, IStock> d = new HashMap<>();

//    for (String string : stocks) {
//      String[] info = string.split(",");
//
//      String[] date = info[0].split("-");
//      Calendar cal = Calendar.getInstance();
//      cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[0]), Integer.parseInt(date[1]));
//
//      double open = Double.parseDouble(info[1]);
//      double high = Double.parseDouble(info[2]);
//      double low = Double.parseDouble(info[3]);
//      double close = Double.parseDouble(info[4]);
//      int volume = Integer.parseInt(info[5]);
//      IStock stock = new Stock(cal, open, high, low, close, volume, ticker);
//
//      d.put(cal, stock);
//    }
    this.stocks.put(ticker, d);
  }

  @Override
  public ArrayList<Calendar> crossover(int window, Calendar date1, Calendar date2, String ticker) {
    if (isValidCalendar(date1)) {
      throw new IllegalArgumentException("Sorry, stock information for " + date1 + " doesn't exist.");
    }
    if (isValidCalendar(date2)) {
      throw new IllegalArgumentException("Sorry, stock information for " + date2 + " doesn't exist.");
    }
    ArrayList<Calendar> results = new ArrayList<Calendar>();
    Calendar currentDate = (Calendar) date1.clone();
    while (currentDate.before(date2)) {
      double currentMovingAverage = movingAverage(window, currentDate, ticker);
      if (currentMovingAverage < stocks.get(ticker).get(currentDate).getClose()) {
        results.add((Calendar) currentDate.clone());
      }
      currentDate.add(Calendar.DAY_OF_YEAR, 1);
    }
    return results;
  }

  @Override
  public double gainOrLoss(Calendar date1, Calendar date2, String ticker) throws IllegalArgumentException {
    if (isValidCalendar(date1)) {
      throw new IllegalArgumentException("Sorry, stock information for " + date1 + " doesn't exist.");
    }
    if (isValidCalendar(date2)) {
      throw new IllegalArgumentException("Sorry, stock information for " + date2 + " doesn't exist.");
    }
    double startingClosePrice = stocks.get(ticker).get(date1).getClose();
    double endingClosePrice = stocks.get(ticker).get(date2).getClose();
    double diff = endingClosePrice - startingClosePrice;
    return diff;
  }

  @Override
  public double movingAverage(int window, Calendar date, String ticker) {
    if (isValidCalendar(date)) {
      throw new IllegalArgumentException("Sorry, stock information for " + date + " doesn't exist.");
    }
    double movingSum = 0;
    Calendar currentDate = (Calendar) date.clone();
    currentDate.add(Calendar.DAY_OF_YEAR, -window);
    if (isValidCalendar(currentDate)) {
      throw new IllegalArgumentException("The window you asked for exceeds the range of data for " + ticker + ".");
    }
    while (currentDate.before(date)) {
      movingSum += stocks.get(ticker).get(currentDate).getClose();
      currentDate.add(Calendar.DAY_OF_YEAR, 1);
    }
    return movingSum / window;
  }

  private boolean isValidCalendar(Calendar cal) {
    return stocks.containsKey(cal);
  }

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
