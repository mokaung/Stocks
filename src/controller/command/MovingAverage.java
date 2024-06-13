package controller.command;

import java.time.LocalDate;
import java.util.Scanner;

import Model.IModel2;

import static controller.command.ControllerUtil.calToString;
import static controller.command.ControllerUtil.getLocalDate;
import static controller.command.ControllerUtil.writeMessage;

/**
 * Shows the x-day moving average for a specified stock at a specified date. The moving average is
 * the average closing prices of the past x days.
 */
public class MovingAverage implements ICommand {
  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   *
   * @param out The appendable used for outputs in the program.
   */
  public MovingAverage(Appendable out) {
    this.out = out;
  }

  /**
   * Macro to run MovingAverage. Uses the model's movingAverage method to calculate the x-day
   * moving average. Asks user for input and outputs a readable message.
   *
   * @param sc    Scanner used to store user input.
   * @param model Model includes movingAverage().
   */
  @Override
  public void run(Scanner sc, IModel2 model) {
    writeMessage("Which stock do you want to analyze? " + System.lineSeparator(), out);
    String ticker = sc.next();
    if (model.isInvalidTicker(ticker)) {
      throw new IllegalArgumentException(
              "Make sure to spell the ticker correctly and populate first.");
    }
    writeMessage("Please enter how many days to base the average "
            + "on (x in x-day moving average): " + System.lineSeparator(), out);
    int window;
    try {
      window = sc.nextInt();
    } catch (Exception e) {
      throw new IllegalArgumentException("Please input a valid number.");
    }
    writeMessage("Please enter the day you want to see the moving average for: "
            + System.lineSeparator(), out);
    LocalDate date1 = getLocalDate(sc.next());
    if (model.isInvalidLocalDate(date1, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for "
              + calToString(date1) + " doesn't exist.");
    }
    writeMessage("The moving average of " + ticker + " on "
            + date1 + " is: "
            + model.movingAverage(window, date1, ticker)
            + System.lineSeparator(), out);
  }
}
