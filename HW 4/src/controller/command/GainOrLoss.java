package controller.command;

import model.IModel;

public class GainOrLoss implements ICommand {
  private final int date1;
  private final int date2;
  public GainOrLoss(int date1, int date2) {
    this.date1 = date1;
    this.date2 = date2;
  }
  @Override
  public Double run(IModel model) {
//    double startingClosePrice = model.tempGetClose(date1);
//    double endingClosePrice = model.tempGetClose(date2);
//    double diff = endingClosePrice - startingClosePrice;
//    return diff;
    Double result = model.gainOrLoss();
    return result;
  }
}
