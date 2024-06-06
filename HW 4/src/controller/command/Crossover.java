package controller.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import model.IModel;

import static controller.command.ControllerUtil.getCalendar;
import static controller.command.ControllerUtil.writeMessage;

/**
 * Shows x-crossover days in a specified time period. Utilizes MovingAverage to get the moving
 * average of each day in the specified time period. X-crossover days are days when the
 * closing price is higher than the x-day moving average.
 */
public class Crossover implements ICommand {

  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   * @param out The appendable used for outputs in the program.
   */
  public Crossover(Appendable out) {
    this.out = out;
  }

  /**
   * Macro to find crossover days. Asks the user for required fields, and outputs an easy-to-read
   * message that shows the of crossover days.
   * @param sc Scanner used for user input.
   * @param model Model that houses the calculating for this command.
   */
  @Override
  public void run(Scanner sc, IModel model) {
    writeMessage("Which stock do you want to analyze? " + System.lineSeparator(), out);
    String ticker = sc.next();
    writeMessage("Please enter how many days to base the averages "
            + "on (x in x-day moving average): " + System.lineSeparator(), out);
    int window = sc.nextInt();
    writeMessage("Please enter the starting date: " + System.lineSeparator(), out);
    Calendar date1 = getCalendar(sc.next());
    writeMessage("Please enter the ending date: " + System.lineSeparator(), out);
    Calendar date2 = getCalendar(sc.next());
    writeMessage("From "
            + date1
            + " to "
            + date2
            + ", the "
            + window
            + "-day crossovers for "
            + ticker
            + " were on the following dates: ", out);
    ArrayList<Calendar> result = model.crossover(window, date1, date2, ticker);
    for (int i = 0; i < result.size(); i++) {
      if (i == result.size() - 1) {
        writeMessage(result.get(i).getTime() + System.lineSeparator(), out);
      } else {
        writeMessage(result.get(i).getTime() + ", ", out);
      }
    }
  }
}
