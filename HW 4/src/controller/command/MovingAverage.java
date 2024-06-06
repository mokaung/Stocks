package controller.command;

import java.util.Calendar;

import model.IModel;

public class MovingAverage implements ICommand {
  private final int window;
  private final Calendar date;
  private final String ticker;

  public MovingAverage(String ticker, int window, Calendar date) {
    this.window = window;
    this.date = date;
    this.ticker = ticker;
  }
  @Override
  public Double run(IModel model) {
    return model.movingAverage(window, date, ticker);
  }
}
