package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModelImpl implements IModel {
  private final Map<String, Map<Calendar, IStock>> stocks;
  private Map<String, IPortfolio> portfolios;

  public ModelImpl() {
    stocks = new HashMap<>();
  }

  @Override
  public void populate(Readable readable, String ticker) {
    Scanner s = new Scanner(readable);
    if (s.hasNext()) {
      // removes the header
      s.next();
    }

    Map<Calendar, IStock> dateStock = new HashMap<>();

    while (s.hasNext()) {
      String stockLine = s.next();
      String[] parts = stockLine.split(",");

      String[] dateParts = parts[0].split("-");

      Calendar cal = Calendar.getInstance();
      cal.set(Integer.parseInt(dateParts[0]),
              Integer.parseInt(dateParts[1]),
              Integer.parseInt(dateParts[2]));

      System.out.println(dateParts[0]);
      System.out.println(dateParts[1]);
      System.out.println(dateParts[2]);

      double open = Double.parseDouble(parts[1]);
      double high = Double.parseDouble(parts[2]);
      double low = Double.parseDouble(parts[3]);
      double close = Double.parseDouble(parts[4]);
      int volume = Integer.parseInt(parts[5]);
      IStock stock = new Stock(cal, open, high, low, close, volume, ticker);

      dateStock.put(cal, stock);
    }
    this.stocks.put(ticker, dateStock);
  }

  @Override
  public ArrayList<Calendar> crossover(int window, Calendar date1, Calendar date2, String ticker) {
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
    double startingClosePrice = stocks.get(ticker).get(date1).getClose();
    double endingClosePrice = stocks.get(ticker).get(date2).getClose();
    return endingClosePrice - startingClosePrice;
  }

  @Override
  public double movingAverage(int window, Calendar date, String ticker) {
    double movingSum = 0;
    Calendar currentDate = (Calendar) date.clone();
    currentDate.add(Calendar.DAY_OF_YEAR, -window);
    while (currentDate.before(date)) {
      movingSum += stocks.get(ticker).get(currentDate).getClose();
      currentDate.add(Calendar.DAY_OF_YEAR, 1);
    }
    return movingSum / window;
  }

  @Override
  public boolean isValidCalendar(Calendar cal, String ticker) {
    return stocks.get(ticker).containsKey(cal);
  }

  @Override
  public boolean isValidPortfolio(String name) {
    return portfolios.containsKey(name);
  }

  @Override
  public boolean isValidTicker(String ticker) {
    return !stocks.containsKey(ticker);
  }

  @Override
  public IPortfolio createPortfolio(String ticker, int share, String name) {
    Portfolio p = new Portfolio();
    Map<Calendar, IStock> info = stocks.get(ticker);
    p.setValue(info, share, ticker);
    portfolios.put(name, p);
    return p;
  }

  @Override
  public void addToPortfolio(String portFolioName, String ticker, int share) {
    portfolios.get(portFolioName);
  }

  @Override
  public Double getPortfolioValue(String s, Calendar cal) {
    return portfolios.get(s).getValue(cal);
  }
}
