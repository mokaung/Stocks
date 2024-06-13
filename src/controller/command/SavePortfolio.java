package controller.command;

import java.io.IOException;
import java.util.Scanner;

import controller.ModelAdapter;
import Model.IModel;
import Model.IModel2;

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
  public void run(Scanner sc, IModel model)throws IllegalArgumentException {
    if (model instanceof IModel2) {
      model = new ModelAdapter((IModel2)model);
    }
    writeMessage("Which portfolio would you like to save? "
            + System.lineSeparator(), out);
    String name = sc.next();
    if (!model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    writeMessage("Saving Portfolio... "
            + System.lineSeparator(), out);
    try {
      if (model instanceof IModel2) {
        ((ModelAdapter) model).savePortfolio(name);
      } else {
        throw new IllegalArgumentException("Model does not support saving portfolios. " +
                "This is a program error. If you see this, the program is flawed.");
      }
    }
    catch (IOException e){
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
