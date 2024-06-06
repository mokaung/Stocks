package controller.command;

import java.util.Calendar;

import model.IModel;

public class MovingAverage implements ICommand {
  private final int averageWindow;
  private final Calendar date;
  private final String ticker;

  public MovingAverage(int averageWindow, Calendar date, String ticker) {
    this.averageWindow = averageWindow;
    this.date = date;
    this.ticker = ticker;
  }

  @Override
  public void run(IModel model) {
    model.movingAverage(averageWindow, date, ticker);
  }
}
