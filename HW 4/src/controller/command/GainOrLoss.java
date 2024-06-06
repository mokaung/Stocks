package controller.command;

import java.util.Calendar;

import model.IModel;

public class GainOrLoss implements ICommand {
  private final Calendar start;
  private final Calendar close;
  private final String ticker;

  public GainOrLoss(String ticker, Calendar start, Calendar close) {
    this.ticker = ticker;
    this.start = start;
    this.close = close;
  }

  @Override
  public void run(IModel model) {
    model.gainOrLoss(ticker, start, close);
  }
}
