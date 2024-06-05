package controller.command;

import java.util.Calendar;

import model.IModel;

public class GainOrLoss implements ICommand {
  private final Calendar date1;
  private final Calendar date2;
  private final String ticker;
  public GainOrLoss(Calendar date1, Calendar date2, String ticker) {
    this.date1 = date1;
    this.date2 = date2;
    this.ticker = ticker;
  }
  @Override
  public Double run(IModel model) {
    return model.gainOrLoss(date1, date2, ticker);
  }
}
