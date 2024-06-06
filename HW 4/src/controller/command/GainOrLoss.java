package controller.command;

import java.util.Calendar;
import java.util.Scanner;

import model.IModel;
import static controller.command.ControllerUtil.getCalendar;
import static controller.command.ControllerUtil.writeMessage;


public class GainOrLoss implements ICommand {
  private final Appendable out;
  public GainOrLoss(Appendable out) {
    this.out = out;
  }
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
