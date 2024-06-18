package controller.command2;

import java.time.LocalDate;
import java.util.Scanner;

import model.IModel2;

import static controller.ControllerUtil.getLocalDate;
import static controller.ControllerUtil.writeMessage;

/**
 * Generates a graph of the overall performance of either a stock
 * or portfolio over time.
 */
public class GetPerformance implements ICommand {
  private final Appendable out;

  public GetPerformance(Appendable out) {
    this.out = out;
  }

  @Override
  public void run(Scanner sc, IModel2 model) {
    writeMessage("Would you like the performance of a stock or portfolio? "
            + System.lineSeparator(), out);

    String option = sc.next();
    boolean b;
    if (option.equalsIgnoreCase("portfolio")
            || option.equalsIgnoreCase("stock")) {
      b = option.equals("portfolio");
    } else {
      throw new IllegalArgumentException("Invalid command." + System.lineSeparator());
    }


    writeMessage("Which " + option + " would you want to analyze? "
            + System.lineSeparator(), out);

    String name = sc.next();
    if (model.isInvalidPortfolio(name) && b) {
      throw new IllegalArgumentException("Invalid portfolio");
    }
    if (model.isInvalidTicker(name) && !b) {
      throw new IllegalArgumentException("Invalid ticker");
    }

    writeMessage("Please input the start date." + System.lineSeparator(), out);
    LocalDate startDate = getLocalDate(sc.next());

    writeMessage("Please input the end date." + System.lineSeparator(), out);
    LocalDate endDate = getLocalDate(sc.next());

    writeMessage(model.getPerformance(b, name, startDate, endDate), out);
  }
}
