package controller;

import java.time.LocalDate;
import java.util.Objects;

import model.IModel2;
import view.IView;
import view.IViewListener;

public class MockController extends ControllerImplGUI implements IController, IViewListener {
  private IView view;
  private IModel2 model;
  private final StringBuilder log;

  public MockController(IView view, IModel2 model, StringBuilder log) {
    super(view, model);
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
  public void handleGetPortfolioValue(String name, LocalDate date) {
    log.append("handleGetPortfolioValue");
  }

  @Override
  public void handleSellStock(String ticker, double share, String name, LocalDate date) {
    log.append("handleStock");
  }

  @Override
  public void handleLoadPortfolio(String name) {
    log.append("handleLoadPortfolio");
  }

}
