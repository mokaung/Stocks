package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Portfolio.IPortfolioV2;
import Portfolio.PortfolioV2;

/**
 * Model for the program. Stores all the information. Is run from Controller when user wants
 * something.
 */
public class ModelImpl2 extends ModelImpl implements IModel2 {
  private final Map<String, Map<LocalDate, IStock>> stocks;
  private final Map<String, IPortfolioV2> portfolios;

  /**
   * Constructor for creating a blank model.
   */
  public ModelImpl2() {
    stocks = new HashMap<>();
    portfolios = new HashMap<>();
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
    PortfolioV2 p = new PortfolioV2(name);
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
  public void savePortfolio(String portFolioName) {
    try {
      portfolios.get(portFolioName).saveXml(portFolioName);
    } catch (IOException e) {
      throw new IllegalStateException("Could not save portfolio.");
    }
  }
}
