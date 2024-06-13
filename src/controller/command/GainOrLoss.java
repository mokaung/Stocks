package controller.command;

import java.time.LocalDate;
import java.util.Scanner;

import Model.IModel2;

import static controller.command.ControllerUtil.calToString;
import static controller.command.ControllerUtil.getLocalDate;
import static controller.command.ControllerUtil.writeMessage;

/**
 * Shows the gain or loss of a requested stock. Uses the gainOrLoss method in the model.
 */
public class GainOrLoss implements ICommand {
  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   *
   * @param out The appendable used for outputs in the program.
   */
  public GainOrLoss(Appendable out) {
    this.out = out;
  }

  /**
   * Macro to find gain or loss of a stock. Asks user for the required fields, and uses the
   * gainOrLoss method in the inputted model. Outputs a readable message for user.
   *
   * @param sc    Scanner used to store user input.
   * @param model Model of the program, has gainOrLoss() method.
   * @throws IllegalArgumentException if the ending date is earlier than the starting date.
   */
  @Override
  public void run(Scanner sc, IModel2 model) throws IllegalArgumentException {
    writeMessage("Which stock do you want to analyze? " + System.lineSeparator(), out);
    String ticker = sc.next();
    if (model.isInvalidTicker(ticker)) {
      throw new IllegalArgumentException("Make sure to spell the ticker correctly and populate first.");
    }
    writeMessage("Please enter a starting date: " + System.lineSeparator(), out);
    LocalDate date1 = getLocalDate(sc.next());
    if (model.isInvalidLocalDate(date1, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date1) + " doesn't exist.");
    }
    writeMessage("Please enter a ending date: " + System.lineSeparator(), out);
    LocalDate date2 = getLocalDate(sc.next());
    if (model.isInvalidLocalDate(date2, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date2) + " doesn't exist.");
    }
    if (date2.isBefore(date1)) {
      throw new IllegalArgumentException("Ending date should not be before starting date.");
    }
    writeMessage("The gain/loss of " + ticker + " is: "
            + model.gainOrLoss(date1, date2, ticker) + System.lineSeparator(), out);
  }
}
