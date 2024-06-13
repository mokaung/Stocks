package view;

import java.time.LocalDate;

import controller.command.ControllerUtil;
import Portfolio.IPortfolioV2;

public class View implements IViews {
  private final Appendable out;
  private final Readable in;


  public View(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

}
