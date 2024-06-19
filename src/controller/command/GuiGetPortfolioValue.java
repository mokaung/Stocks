//package controller.command;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import javax.swing.*;
//
//import View.IView;
//import model.IModel2;
//
//import static controller.command.ControllerUtil.getLocalDate;
//
//public class GuiGetPortfolioValue implements ICommand {
//  private final IView view;
//  public GuiGetPortfolioValue(IView view) {
//
//    this.view = view;
//  }
//  @Override
//  public void run(Scanner sc, IModel2 model) {
//    ArrayList<String> portfolios = model.getPortfolioNames(); // Assuming this method exists in the model
//    String portfolio = (String) JOptionPane.showInputDialog(null,
//            "Select portfolio to analyze",
//            "Portfolio Selection",
//            JOptionPane.QUESTION_MESSAGE,
//            null,
//            portfolios.toArray(),
//            portfolios.get(0));
//
//    if (portfolio == null || model.isInvalidPortfolio(portfolio)) {
//      view.setOutputText("Invalid portfolio.");
//      return;
//    }
//
//    String dateStr = JOptionPane.showInputDialog("Enter a date (YYYY-MM-DD) to calculate the value:");
//    LocalDate date;
//    try {
//      date = getLocalDate(dateStr);
//    } catch (IllegalArgumentException e) {
//      view.setOutputText("Invalid date format.");
//      return;
//    }
//
//    // Calculate portfolio value and display it in the GUI
//    try {
//      double value = model.getPortfolioValueV2(portfolio, date);
//      view.setOutputText("The value of " + portfolio + " on " + calToString(date) + " is: $" + value);
//    } catch (IllegalArgumentException e) {
//      view.setOutputText("Error calculating portfolio value: " + e.getMessage());
//    }
//  }
//}
