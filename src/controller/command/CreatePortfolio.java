package controller.command;

import java.util.Scanner;

import model.IModel;

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
  public void run(Scanner sc, IModel model) {
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

  // Add a stock to the portfolio.
  private void addNewStock(Scanner sc, IModel model, boolean tf) {
    String name = "";
    writeMessage("Which stock would you like to add into this portfolio? " + System.lineSeparator(), out);
    String ticker = sc.next();
    if (model.isValidTicker(ticker)) {
      throw new IllegalArgumentException("Make sure to spell the ticker correctly and populate first.");
    }
    writeMessage("How many shares of " + ticker + " would you like? " + System.lineSeparator(), out);
    int shares = sc.nextInt();
    writeMessage("Adding " + shares + " shares of " + ticker + " to this portfolio..." + System.lineSeparator(), out);
    if (tf) {
      writeMessage("Enter a name for this portfolio: " + System.lineSeparator(), out);
      name = sc.next();
    }
    isNewPortfolio(tf, model, ticker, shares, name);
  }

  // Checks and creates a portfolio.
  private void isNewPortfolio(boolean tf, IModel model, String ticker, int shares, String name) {
    if (tf) {
      model.createPortfolio(ticker, shares, name);
    } else {
      model.addToPortfolio(name, ticker, shares);
    }
  }
}
