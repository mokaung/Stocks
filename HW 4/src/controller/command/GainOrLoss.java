package controller.command;

import model.IModel;

// command should take view instead of Appendable in
// view class has write message
// stra pattern:
/*
    iStock stock = this.stocks.get(ticker)
    for (itn i=0; i < stock.getOpen().size() ; i++) {
    double sum = 0;

    opens sotck.getOpens()
    sum += opens.getOpens()
    r
 */

public class GainOrLoss implements ICommand {


  @Override
  public void run(IModel model) {

  }
}
