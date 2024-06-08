package controller.command;

import java.util.Scanner;

import model.IModel;

import static controller.command.ControllerUtil.writeMessage;

/**
 * This class allows the user to add a stock to an existing portfolio.
 */
public class AddStockToPortfolio implements ICommand{
  private final Appendable out;

  /**
   * constructor, takes in out from the controller.
   * @param out User interface output.
   */
  public AddStockToPortfolio(Appendable out) {
    this.out = out;
  }

  /**
   * Runs the command. Checks for any invalid inputs, which throws an exception into
   * the controller, which correctly outputs it as an error message.
   * @param sc Scanner used for user input.
   * @param model Model used for computation.
   */
  @Override
  public void run(Scanner sc, IModel model) {
    writeMessage("Which portfolio would you like to add a stock to? " + System.lineSeparator(), out);
    String name = sc.next();
    if (model.isValidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    writeMessage("Which stock would you like to add to " + name + "? " + System.lineSeparator(), out);
    String ticker = sc.next();
    if (model.isValidTicker(ticker)) {
      throw new IllegalArgumentException("Make sure to spell the ticker correctly and populate first.");
    }
    writeMessage("How many shares would you like to add? " + System.lineSeparator(), out);
    int share = sc.nextInt();
    model.addToPortfolio(name, ticker, share);
  }
}
