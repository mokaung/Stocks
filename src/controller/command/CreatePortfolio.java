package controller.command;

import java.time.LocalDate;
import java.util.Scanner;

import Model.IModel2;

import static controller.command.ControllerUtil.calToString;
import static controller.command.ControllerUtil.getLocalDate;
import static controller.command.ControllerUtil.writeMessage;

/**
 * This class represents a command to create a new portfolio.
 * Users can view the portfolio's value and add stocks.
 */
public class CreatePortfolio implements ICommand {

  private final Appendable out;

  /**
   * Constructs a CreatePortfolio command object with an appendable object to send outputs.
   *
   * @param out the appendable object to which outputs are sent
   */
  public CreatePortfolio(Appendable out) {
    this.out = out;
  }

  /**
   * Command given to the controller run to create a portfolio.
   *
   * @param sc    the scanner to read user inputs
   * @param model the model. used to call the createPortfolio method in model.
   */
  @Override
  public void run(Scanner sc, IModel2 model) {
    writeMessage("Creating new portfolio... " + System.lineSeparator(), out);
    addNewStock(sc, model, true);
    String confirmation = "";
    while (!confirmation.equals("N")) {
      writeMessage("Would you like to add one more stock to this portfolio? Y/N" + System.lineSeparator(), out);
      confirmation = sc.next().trim().toUpperCase();
      while (!confirmation.equals("Y") && !confirmation.equals("N")) {
        writeMessage("Please type 'Y' or 'N'." + System.lineSeparator(), out);
        confirmation = sc.next().trim().toUpperCase();
      }
      if (confirmation.equals("Y")) {
        addNewStock(sc, model, false);
      }
    }
    writeMessage("Success! Created new portfolio." + System.lineSeparator(), out);
  }

  /**
   * ability to addNewStocks, which has been separated from the run method to provide different
   * behavior at the start, when user has no stocks in portfolio, versus later, when user
   * is asked if they want to input more stocks.
   *
   * @param sc    inherited from run.
   * @param model inherited from run.
   * @param tf    true false boolean to check if command is at the start of running.
   */
  private void addNewStock(Scanner sc, IModel2 model, boolean tf) {
    String name = "";
    writeMessage("Which stock would you like to add into this portfolio? " + System.lineSeparator(), out);
    String ticker = sc.next();
    if (model.isInvalidTicker(ticker)) {
      throw new IllegalArgumentException("Make sure to spell the ticker correctly and populate first.");
    }
    writeMessage("How many shares of " + ticker + " would you like? " + System.lineSeparator(), out);
    int shares = sc.nextInt();
    LocalDate date1 = null;
    writeMessage("At what date would you like to buy these shares? " + System.lineSeparator(), out);
    date1 = getLocalDate(sc.next());
    if (model.isInvalidLocalDate(date1, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date1) + " doesn't exist.");
    }
    writeMessage("Adding " + shares + " shares of " + ticker + " to this portfolio..." + System.lineSeparator(), out);
    if (tf) {
      writeMessage("Enter a name for this portfolio: " + System.lineSeparator(), out);
      name = sc.next();
    }
    isNewPortfolio(tf, model, ticker, shares, name, date1);
  }

  /**
   * Checks if portfolio is new.
   *
   * @param tf     inherited from addNewStock.
   * @param model  inherited from addNewStock.
   * @param ticker inherited from addNewStock.
   * @param shares inherited from addNewStock.
   * @param name   inherited from addNewStock.
   */
  private void isNewPortfolio(boolean tf, IModel2 model, String ticker, int shares, String name, LocalDate date1) {
    if (tf) {
      model.createPortfolioV2(ticker, shares, name, date1);
    } else {
      model.addToPortfolioV2(ticker, name, shares, date1);
    }
  }
}
