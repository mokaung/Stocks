package controller.command;

import model.IModel;

public class MovingAverage implements ICommand {
  private final int averageWindow;
  private final int date;

  public MovingAverage(int averageWindow, int date) {
    this.averageWindow = averageWindow;
    this.date = date;
  }
  @Override
  public Double run(IModel model) {
    double movingSum = 0;
    double movingAverage;
    for (int i=averageWindow;i>0;i--) {
      //apparently you can calculate moving averages on closing, opening, high, low
      //currently average is based on closing prices
      //currently uses date-i, assumes that date will be an int
      movingSum = movingSum + model.tempGetClose(date-i);
    }
    movingAverage = movingSum / averageWindow;
    return movingAverage;
  }
}
