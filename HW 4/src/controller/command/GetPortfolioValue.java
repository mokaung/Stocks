package controller.command;

import java.util.Scanner;

import model.IModel;

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

  @Override
  public void run(Scanner sc, IModel model) throws IllegalArgumentException {
    writeMessage("Which portfolio do you want to analyze? " + System.lineSeparator(), out);
    String name = sc.next();
    if (model.isValidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    writeMessage("Enter a date to calculate the value of " + name + " at that date." + System.lineSeparator(), out);
  }

}
