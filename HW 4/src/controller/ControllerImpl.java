package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import controller.command.Crossover;
import controller.command.GainOrLoss;
import controller.command.ICommand;
import controller.command.MovingAverage;
import model.IModel;
import controller.command.ControllerUtil;

public class ControllerImpl implements IController {
  protected Appendable out;
  protected Readable in;
  protected final Map<String, Supplier<ICommand>> commands;

  public ControllerImpl(Appendable out, Readable in) throws IllegalArgumentException {
    if ((in == null) || (out == null)) {
      throw new IllegalArgumentException("Sheet, readable or appendable is null");
    }
    this.out = out;
    this.in = in;

    this.commands = new HashMap<>();
    commands.put("GainOrLoss", () -> new GainOrLoss(this.out));
    commands.put("Crossover", () -> new Crossover(this.out));
    commands.put("MovingAverage", () -> new MovingAverage(this.out));
  }

  @Override
  public void go(IModel model) {
    Scanner sc = new Scanner(in);
    boolean quit = false;

    ControllerUtil.welcomeMessage(out);
    while (sc.hasNext()) {
      ICommand c;
      String in = sc.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("Q")) {
        ControllerUtil.endMessage(this.out);
        return;
      }
      if (in.equalsIgnoreCase("M") || in.equalsIgnoreCase("m")) {
        ControllerUtil.printMenu(this.out);
        return;
      }
      Supplier<ICommand> cmd = commands.getOrDefault(in, null);
      if (cmd != null) {
        ICommand runner = cmd.get();
        runner.run(sc, model);
      } else {
        throw new IllegalArgumentException("Invalid command");

      }
    }
  }
}
