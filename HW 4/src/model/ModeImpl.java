package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import controller.command.MovingAverage;

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

  /**
   * Method that gives the user which days in a specified window of time are crossover days.
   *
   * @param window
   * @param date1
   * @param date2
   * @param ticker
   * @return
   */
  @Override
  public ArrayList<Calendar> crossover(int window, Calendar date1, Calendar date2, String ticker) {
//    ArrayList<Calendar> results = new ArrayList<Calendar>();
//    Iterator<Map.Entry<Calendar, IStock>> iterator = stocks.get(ticker).entrySet().iterator();
//
//    int iterateCount = 0;
//    while (iterator.hasNext()) {
//      Map.Entry<Calendar, IStock> entry = iterator.next();
//      Calendar key = entry.getKey();
//      if (key.before(date1)) {
//        continue;
//      }
//      if (key.after(date2)) {
//        break;
//      }
//      double currentMovingAverage = movingAverage(window, key, ticker);
//      if (currentMovingAverage >= stocks.get(ticker).get(key).getClose()) {
//        results.add(key);
//      }
//      iterateCount++;
//    }
    if (isValidCalendar(date1)) {
      throw new IllegalArgumentException("Sorry, stock information for " + date1 + " doesn't exist.");
    }
    if (isValidCalendar(date2)) {
      throw new IllegalArgumentException("Sorry, stock information for " + date2 + " doesn't exist.");
    }
    ArrayList<Calendar> results = new ArrayList<Calendar>();
    Calendar currentDate = (Calendar) date1.clone();
    while (currentDate.before(date2)) {
      Double currentMovingAverage = movingAverage(window, currentDate, ticker);
      if (currentMovingAverage < stocks.get(ticker).get(currentDate).getClose()) {
        results.add((Calendar) currentDate.clone());
      }
      currentDate.add(Calendar.DAY_OF_YEAR, 1);
    }
    return results;
  }

  @Override
  public Double gainOrLoss(Calendar date1, Calendar date2, String ticker)throws IllegalArgumentException {
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
  public Double movingAverage(int window, Calendar date, String ticker) {
//    double movingSum = 0;
//    Iterator<Map.Entry<Calendar, IStock>> iterator = stocks.get(ticker).entrySet().iterator();
//
//    int iterateCount = 0;
//    Calendar key = Calendar.getInstance();
//    key.set(0, Calendar.JANUARY, 1);
//    while (iterator.hasNext() && iterateCount < window && key.before(date)) {
//      Map.Entry<Calendar, IStock> entry = iterator.next();
//      key = entry.getKey();
//      if (key.before(date)) {
//        continue;
//      }
//      //is there a limit to how far back it can go?
//      movingSum = movingSum + stocks.get(ticker).get(key).getClose();
//      iterateCount++;
//    }
//    return movingSum/window;

    //new implementation uses Calendar.add to iterate through our Map<Calendar, IStock>
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
    if (stocks.containsKey(cal)) {
      return true;
    }
    return false;
  }
  // change to fix above commands the best
//  @Override
//  public IStock getStock(String ticker) {
//    return stocks.get(ticker);
//  }
//
//  @Override
//  public IPortfolio createPortfolio(String ticker, int share) {
//    Portfolio p = new Portfolio();
//    for (IStock stock : stocks.values()) {
//      if (stock.getTicker().equals(ticker)) {
//        p.setValue(stock, share);
//      }
//    }
//    return p;
//  }
}
