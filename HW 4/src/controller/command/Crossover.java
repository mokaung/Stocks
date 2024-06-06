package controller.command;

import java.util.ArrayList;
import java.util.Calendar;

import model.IModel;

/**
 * returns a list of dates that can be shown to the user through controller.
 */
public class Crossover implements ICommand {
  private final Calendar date1;
  private final Calendar date2;
  private final int window;
  private final String ticker;

  public Crossover(String ticker, int window, Calendar date1, Calendar date2) {
    this.date1 = date1;
    this.date2 = date2;
    this.window = window;
    this.ticker = ticker;
  }
  @Override
  public ArrayList<Calendar> run(IModel model) {
    return model.crossover(window, date1, date2, ticker);
  }
}
