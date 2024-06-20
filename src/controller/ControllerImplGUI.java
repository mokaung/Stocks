package controller;


import java.io.IOException;
import java.time.LocalDate;

import view.IView;
import view.IViewListener;
import controller.command.AlphaVantageStreamReader;
import controller.command.IReader;
import model.IModel2;

/**
 * Controller for GUI view.
 */
public class ControllerImplGUI implements IController, IViewListener {
  private final IView view;
  private final IModel2 model;

  public ControllerImplGUI(IView view, IModel2 model) {
    this.view = view;
    this.model = model;
    view.addViewListener(this);
  }

  @Override
  public void init(IModel2 model) {
    view.render(true);
  }

  @Override
  public void handleCreatePortfolio(String ticker, double share, String name, LocalDate date) {
    // TODO try catch
    model.createPortfolioV2(ticker, share, name, date);
    view.requestFocus();
  }

  @Override
  public void handleSavePortfolio(String name) {
    try {
      model.savePortfolio(name);
      view.requestFocus();
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not save portfolio");
    }
  }

  @Override
  public void handleGetPortfolioValue(String name, LocalDate date) {
    model.getPortfolioValue(name, date);
    view.requestFocus();
  }

  @Override
  public void handleSellStock(String ticker, double share, String name, LocalDate date) {
    model.addToPortfolioV2(name, ticker, share, date);
    view.requestFocus();
  }

  @Override
  public void handleLoadPortfolio(String name) {

  }
}