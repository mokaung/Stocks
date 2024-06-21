package controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import model.IModel2;
import view.IView;
import view.IViewListener;


/**
 * A mock controller for testing.
 */
public class MockController extends ControllerImplGUI implements IController, IViewListener {
  private IView view;
  private IModel2 model;
  private final StringBuilder log;

  public MockController(IView view, IModel2 model, StringBuilder log) {
    super(model, view);
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void init(IModel2 model) {
    log.append("init");
    view.render(true);
  }

  @Override
  public void handleCreatePortfolio(String ticker, double share, String name, LocalDate date) {
    log.append("handleCreatePortfolio");
  }

  @Override
  public void handleSavePortfolio(String name) {
    log.append("handleSavePortfolio");
  }

  @Override
  public String handleGetPortfolioValue(String name, LocalDate date) {
    log.append("handleGetPortfolioValue");
    return name;
  }

  @Override
  public void handleLoadPortfolio(String name) {
    log.append("handleLoadPortfolio");
  }

  @Override
  public ArrayList<String> listPortfolios() throws IllegalArgumentException {
    log.append("listPortfolios");
    return model.getPortfolioNames();
  }

  @Override
  public ArrayList<String> listStocks() throws IllegalArgumentException {
    log.append("listStocks");
    return model.getStockNames();
  }

  @Override
  public void handleBuyStock(String name, String ticker,
                             int share, LocalDate date1) throws IllegalArgumentException {
    log.append("handleBuyStock");
  }

  @Override
  public void handleSellStock(String name, String ticker,
                              double share, LocalDate date1) throws IllegalArgumentException {
    log.append("handleSellStock");
  }

  @Override
  public void handlePopulateStock(String ticker) throws IllegalArgumentException {
    log.append("handlePopulateStock");
  }

  @Override
  public void handleCSVStock(String ticker) throws IllegalArgumentException {
    log.append("handleCSVStock");
  }

  @Override
  public ArrayList<String> listLoadablePortfolios() throws IllegalArgumentException {
    log.append("listLoadablePortfolios");
    return new ArrayList<>();
  }

  @Override
  public void handleAddToPortfolio(String ticker, double share, String name,
                                   LocalDate date1) throws IllegalArgumentException {
    log.append("handleAddToPortfolio");
  }
}
