//package controller.command;
//
//import model.IModel2;
//import View.IView;
//import controller.IController;
//
//import java.time.LocalDate;
//
//import javax.swing.*;
//
//public class GuiGetPortfolioValue implements ICommand2 {
//  private final IView view;
//  private final IController controller;
//  private final String portfolio;
//  private final LocalDate date;
//
//  public GuiGetPortfolioValue(IView view, IController controller, String portfolio, LocalDate date) {
//    this.view = view;
//    this.controller = controller;
//    this.portfolio = portfolio;
//    this.date = date;
//  }
//
//  @Override
//  public void run(IModel2 model) {
//    if (portfolio == null || model.isInvalidPortfolio(portfolio)) {
//      view.showError("Invalid date.");
//    }
//
//    if (date == null) {
//      view.showError("Invalid date.");
//    }
//
//    double value = model.getPortfolioValueV2(portfolio, date);
//    controller.updatePortfolioValue(value);
//    controller.updateView("The value of " + portfolio + " on " + date + " is: $" + value);
//  }
//}
