package view;

import java.time.LocalDate;

import controller.command.ControllerUtil;
import model.IPortfolioV2;

public class View implements IViews {
  private final Appendable out;
  private final Readable in;


  public View(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public String getPerformance(IPortfolioV2 portfolio, LocalDate start, LocalDate end) {
    ControllerUtil.writeMessage("Performance of portfolio " +
            portfolio.getName() + " from " + start + " to " + end +
            System.lineSeparator() + System.lineSeparator(), out);

    String month = start.getMonth().toString();
    for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
      double b = 0.0;
      if (month.equals(date.getMonth().toString())) {
        b += portfolio.getValue(date);
      } else {
        String name = month.substring(0, 3);
        ControllerUtil.writeMessage(name + " "
                + date.getYear() + ": " + b + System.lineSeparator(), out);
        month = date.getMonth().toString();
      }
    }
    ControllerUtil.writeMessage("Scale: * = 1000", out);
    return "";
  }

  // changes the value into asterisks rounded to the nearest hundred
  // each * is worth 1000
  private String create(double value) {
    StringBuilder b = new StringBuilder();
    while (value >= 1000.0) {
      b.append("*");
      value -= 1000.0;
    }
    if (value >= 500.0) {
      b.append("*");
    }
    return b.toString();
  }

}
