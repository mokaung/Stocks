package controller.command;

import java.util.ArrayList;
import java.util.Calendar;

import model.IModel;

/**
 * Returns a list of dates that are "buy" opportunities for users.
 * Buy opportunities are when the closing price on that day is greater than the moving average.
 */
public class Crossover implements ICommand {
  private final Calendar date1;
  private final Calendar date2;
  private final int averageWindow;

  public Crossover(int averageWindow, Calendar date1, Calendar date2) {
    this.date1 = date1;
    this.date2 = date2;
    this.averageWindow = averageWindow;
  }

  @Override
  public void run(IModel model) {
    model.crossover(averageWindow, date1, date2);
  }
}
