package controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import View.IView;
import controller.command.AddStockToPortfolio;
import controller.command.CreatePortfolio;
import controller.command.Crossover;
import controller.command.GainOrLoss;
import controller.command.GetPerformance;
import controller.command.GetPortfolioValue;
import controller.command.ICommand;
import controller.command.LoadPortfolio;
import controller.command.MovingAverage;
import controller.command.Populate;
import controller.command.PortFolioToString;
import controller.command.Rebalance;
import controller.command.SavePortfolio;

public class GUIController implements IGuiController {

  private IView view;

  public GUIController(IView view) {
    this.view = view;
  }

  @Override
  public void onDateSelected(LocalDate date) {
    // Handle date selected action if needed
  }

  @Override
  public void onPortfolioSelected(String portfolio) {
    // Handle portfolio selected action if needed
  }

  @Override
  public void onGetPortfolioValue() {
    String portfolio = view.getSelectedPortfolio();
    LocalDate date = view.getSelectedDate();
    new GUIGetPortfolioValue(view, this, portfolio, date).run(null, model); // We pass null for the Scanner since it's not used
  }

  @Override
  public void onLoadPortfolios() {
    String[] portfolios = model.getAllPortfolios().toArray(new String[0]);
    view.setPortfolios(portfolios);
  }

  @Override
  public void updateView(String message) {
    view.setOutputText(message);
  }

  @Override
  public void updatePortfolioValue(double value) {
    view.setPortfolioValue(value);
  }

  public void showError(String errorMessage) {
    view.showError(errorMessage);
  }
}
