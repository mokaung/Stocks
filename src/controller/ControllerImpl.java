package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import controller.command.AddStockToPortfolio;
import controller.command.CreatePortfolio;
import controller.command.Crossover;
import controller.command.GainOrLoss;
import controller.command.GetPortfolioValue;
import controller.command.ICommand;
import controller.command.MovingAverage;
import controller.command.Populate;
import model.IModel;
import controller.command.ControllerUtil;

import static controller.command.ControllerUtil.writeMessage;

/**
 * Controller of the program. Oversees the running of the program and command organization.
 * <p>
 * This program directly uses the Appendable out for user interface, so there is no official view.
 * Stocks are stored in a map in the Model accessed by the controller when needed.
 * <p>
 * The user is introduced to a menu, which asks for inputs from the keyboard, in the form of
 * numbers. When the user wants to repeat the menu, then they can enter M/m and Q/q to quit.
 */
public class ControllerImpl implements IController {
  protected Appendable out;
  protected Readable in;
  protected final Map<String, Supplier<ICommand>> commands;

  /**
   * The constructor initializes all the variables needed in the go() method. Creates a map
   * for the commands.
   *
   * @param out output Appendable object, used by program to output to user.
   * @param in  Appendable object used for inputs by the user.
   * @throws IllegalArgumentException if in or out is null.
   */
  public ControllerImpl(Appendable out, Readable in) throws IllegalArgumentException {
    if ((in == null) || (out == null)) {
      throw new IllegalArgumentException("Readable or appendable is null");
    }
    this.out = out;
    this.in = in;

    this.commands = new HashMap<>();
    commands.put("1", () -> new Populate(this.out));
    commands.put("2", () -> new GainOrLoss(this.out));
    commands.put("3", () -> new MovingAverage(this.out));
    commands.put("4", () -> new Crossover(this.out));
    commands.put("5", () -> new CreatePortfolio(this.out));
    commands.put("6", () -> new AddStockToPortfolio(this.out));
    commands.put("7", () -> new GetPortfolioValue(this.out));
  }

  /**
   * The go method runs the program. It provides the user with a interface, allowing the user
   * to access commands. Gets helper methods from the CommandUtil class.
   *
   * @param model Model for the program, houses all the computation.
   */
  @Override
  public void go(IModel model) {
    Scanner sc = new Scanner(in);
    boolean quit = false;

    ControllerUtil.welcomeMessage(out);
    while (sc.hasNext()) {
      ICommand c;
      String in = sc.next();
      if (in.equalsIgnoreCase("q")) {
        ControllerUtil.endMessage(this.out);
        return;
      }
      if (in.equalsIgnoreCase("M")) {
        ControllerUtil.printMenu(this.out);
      }
      Supplier<ICommand> cmd = commands.getOrDefault(in, null);
      if (cmd != null) {
        ICommand runner = cmd.get();
        try {
          runner.run(sc, model);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator(), out);
        }
      } else {
        try {
          if (!(in.equalsIgnoreCase("M") || in.equalsIgnoreCase("m"))) {
            throw new IllegalArgumentException("Invalid command.");
          }
        } catch (IllegalArgumentException e) {
          writeMessage(e.getMessage() + System.lineSeparator(), out);
        }
      }
    }
  }
}
