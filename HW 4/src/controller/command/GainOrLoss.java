package controller.command;

import java.util.Calendar;
import java.util.Scanner;

import model.IModel;
import static controller.command.ControllerUtil.getCalendar;
import static controller.command.ControllerUtil.writeMessage;

/**
 * Shows the gain or loss of a requested stock. Uses the gainOrLoss method in the model.
 */
public class GainOrLoss implements ICommand {
  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   * @param out The appendable used for outputs in the program.
   */
  public GainOrLoss(Appendable out) {
    this.out = out;
  }

  /**
   * Macro to find gain or loss of a stock. Asks user for the required fields, and uses the
   * gainOrLoss method in the inputted model. Outputs a readable message for user.
   * @param sc Scanner used to store user input.
   * @param model Model of the program, has gainOrLoss() method.
   * @throws IllegalArgumentException if the ending date is earlier than the starting date.
   */
  @Override
  public void run(Scanner sc, IModel model)throws IllegalArgumentException {
    writeMessage("Which stock do you want to analyze? " + System.lineSeparator(), out);
    String ticker = sc.next();
    writeMessage("Please enter a starting date: " + System.lineSeparator(), out);
    Calendar date1 = getCalendar(sc.next());
    writeMessage("Please enter a ending date: " + System.lineSeparator(), out);
    Calendar date2 = getCalendar(sc.next());
    if (date2.before(date1)) {
      throw new IllegalArgumentException("Ending date should not be before starting date.");
    }
    writeMessage("The gain/loss of "+ ticker + " is: "
            +  model.gainOrLoss(date1, date2, ticker) + System.lineSeparator(), out);
  }
}
