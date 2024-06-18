package controller.command2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.IModel;
import model.IModel2;
import portfolio.Weight;

import static controller.ControllerUtil.getLocalDate;
import static controller.ControllerUtil.writeMessage;

/**
 * Rebalance a portfolio on the given day with the given weights.
 */
public class Rebalance implements ICommand {
  private final Appendable out;


  public Rebalance(Appendable out) {
    this.out = out;
  }

  @Override
  public void run(Scanner sc, IModel2 model) {
    writeMessage("Which portfolio would you like to rebalance? "
            + System.lineSeparator(), out);
    String portfolio = sc.next();
    if (model.isInvalidPortfolio(portfolio)) {
      throw new IllegalArgumentException("Invalid portfolio");
    }

    writeMessage("Please input the stock's ticker and percentage in that order."
            + System.lineSeparator(), out);
    ArrayList<Weight> list = makeWeight(sc, model);

    writeMessage("Please input the date you would like to rebalance."
            + System.lineSeparator(), out);
    LocalDate date = getLocalDate(sc.next());

    model.rebalance(date, list, portfolio);
    writeMessage("portfolio " + portfolio + " was rebalanced." + System.lineSeparator(), out);
  }

  // create the individual weights and put them in a list.
  private ArrayList<Weight> makeWeight(Scanner sc, IModel model) {
    ArrayList<Weight> list = new ArrayList<>();
    double percent = 0;
    while (percent < 100.0) {
      String ticker = sc.next();
      if (model.isInvalidTicker(ticker)) {
        throw new IllegalArgumentException("Invalid ticker");
      }
      double weight = sc.nextDouble();
      Weight w = new Weight(weight, ticker);
      percent += weight;
      list.add(w);
    }
    return list;
  }

}
