package controller.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Model.IModel2;

import static controller.command.ControllerUtil.writeMessage;

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
    LocalDate startDate;
    try {
      startDate = LocalDate.parse(sc.next());
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Invalid start date");
    }
    writeMessage("Please input the end date." + System.lineSeparator(), out);
    LocalDate endDate;
    try {
      endDate = LocalDate.parse(sc.next());
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Invalid end date");
    }

    writeMessage(model.getPerformance(b, name, startDate, endDate), out);
  }
}
