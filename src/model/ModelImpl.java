package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Model for the program. Stores all the information. Is run from Controller when user wants
 * something.
 */
public class ModelImpl implements IModel {
  private final Map<String, Map<LocalDate, IStock>> stocks;
  private final Map<String, IPortfolioV2> portfolios;

  /**
   * Constructor for creating a blank model.
   */
  public ModelImpl() {
    stocks = new HashMap<>();
    portfolios = new HashMap<>();
  }

  /**
   * Used for populating a stock and storing its info in the program.
   *
   * @param readable stock information
   * @param ticker   ticker symbol for the stock
   */
  @Override
  public void populate(Readable readable, String ticker) {
    Scanner s = new Scanner(readable);
    if (s.hasNext()) {
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

  /**
   * Finds the crossover days in a specified window of time.
   *
   * @param window the window size of the moving average
   * @param date1  the start date of the analysis period
   * @param date2  the end date of the analysis period
   * @param ticker ticker symbol for the stock
   * @return all the dates that are crossover days.
   */
  @Override
  public ArrayList<LocalDate> crossover(int window, LocalDate date1, LocalDate date2, String ticker) {
    ArrayList<LocalDate> results = new ArrayList<>();
    LocalDate currentDate = date1;

    while (currentDate.isBefore(date2)) {
      double currentMovingAverage = movingAverage(window, currentDate, ticker);
      if (stocks.get(ticker).get(currentDate) == null) {
        throw new IllegalArgumentException("At least one day in the time window doesn't have information about $" + ticker);
      }
      if (currentMovingAverage < stocks.get(ticker).get(currentDate).getClose()) {
        results.add(currentDate);
      }
      currentDate = currentDate.plusDays(1);
    }
    return results;
  }

  /**
   * Finds the gain or loss of a stock in a certain window of time.
   *
   * @param date1  the start date
   * @param date2  the end date
   * @param ticker ticker symbol for the stock
   * @return the gain or loss value
   */
  @Override
  public double gainOrLoss(LocalDate date1, LocalDate date2, String ticker) {
    double startingClosePrice = stocks.get(ticker).get(date1).getClose();
    double endingClosePrice = stocks.get(ticker).get(date2).getClose();
    return endingClosePrice - startingClosePrice;
  }

  /**
   * Finds the x day moving average of a stock on a particular day.
   *
   * @param window the number of days in the moving average window
   * @param date   the date for which to calculate the moving average
   * @param ticker ticker symbol for the stock
   * @return moving Average price.
   */
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

  /**
   * Checks if date is valid.
   *
   * @param cal    date
   * @param ticker ticker symbol for the stock
   * @return true if valid, false if not.
   */
  @Override
  public boolean isValidLocalDate(LocalDate cal, String ticker) {
    return stocks.get(ticker).containsKey(cal);
  }

  /**
   * Checks if portfolio is valid.
   *
   * @param name the name of the portfolio
   * @return whether portfolio is valid or not.
   */
  @Override
  public boolean isValidPortfolio(String name) {
    return !portfolios.containsKey(name);
  }

  /**
   * Checks if ticker is valid for the stock.
   *
   * @param ticker ticker symbol for the stock
   * @return whether stock exists in the program currently.
   */
  @Override
  public boolean isValidTicker(String ticker) {
    return !stocks.containsKey(ticker);
  }

  /**
   * To create a portfolio of stocks.
   *
   * @param ticker ticker symbol for the stock
   * @param share  the number of shares of the ticker to include
   * @param name   the name of the new portfolio
   * @return a new portfolio.
   */
  //TODO: change to work wiht new portfolio
  @Override
  public IPortfolioV2 createPortfolio(String ticker, int share, String name) {
    PortfolioV2 p = new PortfolioV2(new ModelImpl(), "placeholder");
    Map<LocalDate, IStock> info = stocks.get(ticker);
    p.setValue(info, share, ticker);
    portfolios.put(name, p);
    return p;
  }

  /**
   * Adds stocks from the loaded ones into an existing stock.
   *
   * @param portFolioName the name of the portfolio
   * @param ticker        ticker symbol for the stock
   * @param share         the number of shares to add
   */
  @Override
  public void addToPortfolio(String portFolioName, String ticker, int share) {
    portfolios.get(portFolioName).setValue(stocks.get(ticker), share, ticker);
  }

  @Override
  public void savePortfolio(String portFolioName)throws IOException {
    try {
      portfolios.get(portFolioName).saveXml(portFolioName);
    }
    catch (IOException e) {
      throw e;
    }
  }

  /**
   * Gets the value of an existing portfolio.
   *
   * @param name the name of the portfolio
   * @param cal  date to evaluate the portfolio at
   * @return gets the portfolio's value on the date
   * @throws IllegalArgumentException throws exception when there is no name or cal
   */
  @Override
  public Double getPortfolioValue(String name, LocalDate cal) throws IllegalArgumentException {
    return portfolios.get(name).getValue(cal);
  }

  /**
   * Getter method to return stocks. Maybe used for future implementation.
   *
   * @return stocks
   */
  @Override
  public Map<String, Map<LocalDate, IStock>> getStock() {
    return stocks;
  }
}
