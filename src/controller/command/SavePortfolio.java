package controller.command;

import java.io.IOException;
import java.util.Scanner;

import model.IModel2;

import static controller.command.ControllerUtil.writeMessage;

public class SavePortfolio implements ICommand {
  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   *
   * @param out The appendable used for outputs in the program.
   */
  public SavePortfolio(Appendable out) {
    this.out = out;
  }

  @Override
  public void run(Scanner sc, IModel2 model) throws IllegalArgumentException {
    writeMessage("Which portfolio would you like to save? "
            + System.lineSeparator(), out);
    String name = sc.next();
    if (model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    writeMessage("Saving Portfolio... "
            + System.lineSeparator(), out);
    try {
      model.savePortfolio(name);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
