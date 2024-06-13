package controller.command;

import java.time.LocalDate;
import java.util.Scanner;

import controller.ModelAdapter;
import Model.IModel;
import Model.IModel2;

import static controller.command.ControllerUtil.calToString;
import static controller.command.ControllerUtil.getLocalDate;
import static controller.command.ControllerUtil.writeMessage;

public class GetPortfolioValue implements ICommand {
  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   *
   * @param out The appendable used for outputs in the program.
   */
  public GetPortfolioValue(Appendable out) {
    this.out = out;
  }

  /**
   * Runs the command. Checks for invalid inputs, which are given to controller.
   *
   * @param sc    Scanner taken from Controller, for user input.
   * @param model Model taken from controller.
   * @throws IllegalArgumentException when there is any errors with input.
   */
  @Override
  public void run(Scanner sc, IModel model) throws IllegalArgumentException {
    if (model instanceof IModel2) {
      model = new ModelAdapter((IModel2) model);
    }
    writeMessage("Which portfolio do you want to analyze? " + System.lineSeparator(), out);
    String name = sc.next();
    if (model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    writeMessage("Enter a date to calculate the value of "
            + name + " at that date." + System.lineSeparator(), out);
    LocalDate date1 = getLocalDate(sc.next());
    try {
      if (model instanceof IModel2) {
        writeMessage("The value of " + name + " on " + calToString(date1) + " is: "
                + ((ModelAdapter) model).getPortfolioValueV2(name, date1) + System.lineSeparator(), out);
      }
      else {
        writeMessage("The value of " + name + " on " + calToString(date1) + " is: "
                + model.getPortfolioValue(name, date1) + System.lineSeparator(), out);
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid date.");
    }
  }
}
