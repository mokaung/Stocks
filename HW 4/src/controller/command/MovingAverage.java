package controller.command;

import java.util.Calendar;
import java.util.Scanner;

import model.IModel;

import static controller.command.ControllerUtil.getCalendar;
import static controller.command.ControllerUtil.writeMessage;

public class MovingAverage implements ICommand {
  private final Appendable out;

  public MovingAverage(Appendable out) {
    this.out = out;
  }
  @Override
  public void run(Scanner sc, IModel model) {
    writeMessage("Which stock do you want to analyze? " + System.lineSeparator(), out);
    String ticker = sc.next();
    writeMessage("Please enter how many days to base the average "
            + "on (x in x-day moving average): " + System.lineSeparator(), out);
    int window = sc.nextInt();
    writeMessage("Please enter the day you want to see the moving average for: "
            + System.lineSeparator(), out);
    Calendar date1 = getCalendar(sc.next());
    writeMessage("The moving average of "+ ticker + " on "
            + date1 + " is: "
            + model.movingAverage(window, date1, ticker)
            + System.lineSeparator(), out);
  }
}
