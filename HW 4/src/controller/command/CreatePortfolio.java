package controller.command;

import java.util.Scanner;

import model.IModel;

import static controller.command.ControllerUtil.writeMessage;

public class CreatePortfolio implements ICommand {

  private final Appendable out;


  public CreatePortfolio(Appendable out) {
    this.out = out;
  }

  @Override
  public void run(Scanner sc, IModel model) {
    writeMessage("Creating new portfolio... " + System.lineSeparator(), out);
    addNewStock(sc, model, true);
    String confirmation = "";
    while (confirmation.equals("N")) {
      writeMessage("Would you like to add one more stock to this portfolio? Y/N" + System.lineSeparator(), out);
      confirmation = sc.next();
      while (!confirmation.equals("Y") && !confirmation.equals("N")) {
        confirmation = sc.next();
        writeMessage("Please type 'Y' or 'N'." + System.lineSeparator(), out);
      }
      if (sc.next().equals("Y")) {
        addNewStock(sc, model, false);
      }
    }
    writeMessage("Success! Created new portfolio." + System.lineSeparator(), out);
  }


  private void addNewStock(Scanner sc, IModel model, boolean tf) {
    writeMessage("Which stock would you like to add into this portfolio? " + System.lineSeparator(), out);
    String ticker = sc.next();
    if (model.isValidTicker(ticker)) {
      throw new IllegalArgumentException("Invalid ticker.");
    }
    writeMessage("How many shares of " + ticker + "would you like? " + System.lineSeparator(), out);
    int shares = sc.nextInt();
    writeMessage("Adding " + shares + " shares of " + ticker + " to this portfolio..." + System.lineSeparator(), out);
    writeMessage("Enter a name for this portfolio: " + System.lineSeparator(), out);
    String name = sc.next();
    isNewPortfolio(tf, model, ticker, shares, name);
  }

  private void isNewPortfolio (boolean tf, IModel model, String ticker, int shares, String name) {
    if (tf) {
      model.createPortfolio(ticker, shares, name);
    }
    else {
      model.addToPortfolio(name, ticker, shares);
    }
  }
}
