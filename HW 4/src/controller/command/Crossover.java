package controller.command;

import java.util.ArrayList;

import model.IModel;

/**
 * returns a list of dates that can be shown to the user through controller.
 */
public class Crossover implements ICommand {
  private final int date1;
  private final int date2;
  private final int averageWindow;

  public Crossover(int averageWindow, int date1, int date2) {
    this.date1 = date1;
    this.date2 = date2;
    this.averageWindow = averageWindow;
  }
  @Override
  public ArrayList<Integer> run(IModel model) {
    ArrayList<Integer> results = new ArrayList<Integer>();
    //assuming we can subtract dates
    for (int i=date1;i<(date2);i++) {
      MovingAverage response = new MovingAverage(averageWindow, i);
      //assuming MovingAverage's run will return a double
      double movingAverageResult = response.run(model);
      if (movingAverageResult >= model.tempGetClose(i));
      results.add(i);
    }
    return results;
  }
}
