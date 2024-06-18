package controller.command2;

import java.time.LocalDate;
import java.util.Scanner;

import model.IModel2;

import static controller.ControllerUtil.getLocalDate;
import static controller.ControllerUtil.writeMessage;

/**
 * Displays a portfolio and its information.
 */
public class PortFolioToString implements ICommand {
  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   *
   * @param out The appendable used for outputs in the program.
   */
  public PortFolioToString(Appendable out) {
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
  public void run(Scanner sc, IModel2 model) {
    writeMessage("Which portfolio do you want to see? " + System.lineSeparator(), out);
    String name = sc.next();
    if (model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio");
    }
    writeMessage("At which date do you want to see Portfolio at? " + System.lineSeparator(), out);
    String dateString = sc.next();
    LocalDate date = getLocalDate(dateString);
    try {
      writeMessage(model.portfolioToString(name, dateString, date), out);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }
}
