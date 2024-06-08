package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Model for the program. Stores all the information.
 */
public class ModelImpl implements IModel {
  private final Map<String, Map<LocalDate, IStock>> stocks;
  private Map<String, IPortfolio> portfolios;

  /**
   * Constructor for creating a blank model.
   */
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

    Map<LocalDate, IStock> dateStock = new HashMap<>();

    while (s.hasNext()) {

      String stockLine = s.next();
      String[] parts = stockLine.split(",");

      String[] dateParts = parts[0].split("-");

      int year = Integer.parseInt(dateParts[0]);
      int month = Integer.parseInt(dateParts[1]);
      int day = Integer.parseInt(dateParts[2]);

      LocalDate cal = LocalDate.of(year, month, day);

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
  public ArrayList<LocalDate> crossover(int window, LocalDate date1, LocalDate date2, String ticker) {
    ArrayList<LocalDate> results = new ArrayList<>();
    LocalDate currentDate = date1;

    while (currentDate.isBefore(date2)) {
      double currentMovingAverage = movingAverage(window, currentDate, ticker);
      if (currentMovingAverage < stocks.get(ticker).get(currentDate).getClose()) {
        results.add(currentDate);
      }
      currentDate = currentDate.plusDays(1);
    }
    return results;
  }

  @Override
  public double gainOrLoss(LocalDate date1, LocalDate date2, String ticker) throws IllegalArgumentException {
    double startingClosePrice = stocks.get(ticker).get(date1).getClose();
    double endingClosePrice = stocks.get(ticker).get(date2).getClose();
    return endingClosePrice - startingClosePrice;
  }

  @Override
  public double movingAverage(int window, LocalDate date, String ticker) {
    double movingSum = 0;
    if (window == 0) {
      return movingSum;
    }

    LocalDate currentDate = date.minusDays(window);
    int count = 0;

    while (currentDate.isBefore(date)) {
      if (stocks.containsKey(ticker) && stocks.get(ticker).containsKey(currentDate)) {
        movingSum += stocks.get(ticker).get(currentDate).getClose();
        count++;
      }
      currentDate = currentDate.plusDays(1);
    }
    return movingSum / count;
  }

  @Override
  public boolean isValidLocalDate(LocalDate cal, String ticker) {
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
    Map<LocalDate, IStock> info = stocks.get(ticker);
    p.setValue(info, share, ticker);
    portfolios.put(name, p);
    return p;
  }

  @Override
  public void addToPortfolio(String portFolioName, String ticker, int share) {
    portfolios.get(portFolioName).setValue(stocks.get(ticker), share, ticker);
  }

  @Override
  public Double getPortfolioValue(String name, LocalDate cal) throws IllegalArgumentException {
    return portfolios.get(name).getValue(cal);
  }

  @Override
  public Map<String, Map<LocalDate, IStock>> getStock() {
    return stocks;
  }
}
