package controller.command2;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.IModel2;

import static controller.ControllerUtil.calToString;
import static controller.ControllerUtil.getLocalDate;
import static controller.ControllerUtil.writeMessage;

/**
 * Shows x-crossover days in a specified time period. Utilizes MovingAverage to get the moving
 * average of each day in the specified time period. X-crossover days are days when the
 * closing price is higher than the x-day moving average.
 */
public class Crossover implements ICommand {

  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   *
   * @param out The appendable used for outputs in the program.
   */
  public Crossover(Appendable out) {
    this.out = out;
  }

  /**
   * Macro to find crossover days. Asks the user for required fields, and outputs an easy-to-read
   * message that shows the of crossover days.
   *
   * @param sc    Scanner used for user input.
   * @param model Model that houses the calculating for this command.
   */
  @Override
  public void run(Scanner sc, IModel2 model) {
    writeMessage("Which stock do you want to analyze? " + System.lineSeparator(), out);
    String ticker = sc.next();
    if (model.isInvalidTicker(ticker)) {
      throw new IllegalArgumentException(
              "Make sure to spell the ticker correctly and populate first.");
    }
    writeMessage("Please enter how many days to base the averages "
            + "on (x in x-day moving average): " + System.lineSeparator(), out);
    int window;
    try {
      window = sc.nextInt();
    } catch (Exception e) {
      throw new IllegalArgumentException("Please input a valid number.");
    }
    writeMessage("Please enter the starting date: " + System.lineSeparator(), out);
    LocalDate date1 = getLocalDate(sc.next());
    if (model.isInvalidLocalDate(date1, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date1)
              + " doesn't exist.");
    }
    writeMessage("Please enter the ending date: " + System.lineSeparator(), out);
    LocalDate date2 = getLocalDate(sc.next());
    if (model.isInvalidLocalDate(date2, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date2)
              + " doesn't exist.");
    }
    try {
      List<LocalDate> result = model.crossover(window, date1, date2, ticker);
      writeMessage("From "
              + date1
              + " to "
              + date2
              + ", the "
              + window
              + "-day crossovers for "
              + ticker
              + " were on the following dates: ", out);
      for (int i = 0; i < result.size(); i++) {
        if (i == result.size() - 1) {
          writeMessage(calToString(result.get(i)) + System.lineSeparator(), out);
        } else {
          writeMessage(calToString(result.get(i)) + ", ", out);
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
