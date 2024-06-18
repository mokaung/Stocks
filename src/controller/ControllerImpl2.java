package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import View.IViewText;
import controller.command.ICommand;
import controller.command.ICommand2;
import controller.command2.AddStockToPortfolio2;
import model.IModel2;

public class ControllerImpl2 implements IController2 {
  private final Map<String, Supplier<ICommand2>> commands;
  private final IViewText view;
  private final IModel2 model;

  public ControllerImpl2(Map<String, Supplier<ICommand>> commands, IViewText view, IModel2 model) {
    this.view = view;
    this.model = model;
    this.commands = new HashMap<>();

    commands.put("1", () -> new Populate2(model, view));
    commands.put("2", () -> new GainOrLoss2(model, view));
    commands.put("3", () -> new MovingAverage2(model, view));
    commands.put("4", () -> new Crossover2(model, view));
    commands.put("5", () -> new CreatePortfolio2(model, view));
    commands.put("6", () -> new AddStockToPortfolio2(model, view));
    commands.put("7", () -> new GetPortfolioValue2(model, view));
    commands.put("8", () -> new SavePortfolio2(model, view));
    commands.put("9", () -> new LoadPortfolio2(model, view));
    commands.put("10", () -> new Rebalance2(model, view));
    commands.put("11", () -> new GetPerformance2(model, view));
    commands.put("12", () -> new PortFolioToString2(model, view));
  }

  @Override
  public void init() {
    view.render();
    String msg = view.readMessage();

    while (!msg.isEmpty()) {
      ICommand c;
      if (msg.equalsIgnoreCase("q")) {
        view.endMessage();
        return;
      }
      if (msg.equalsIgnoreCase("M")) {
        view.printMenu();
      }
      Supplier<ICommand2> cmd = commands.getOrDefault(msg, null);
      if (cmd != null) {
        ICommand2 runner = cmd.get();
        try {

          runner.run(model);
        } catch (IllegalArgumentException e) {
          view.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
      } else {
        try {
          if (!(msg.equalsIgnoreCase("m"))) {
            throw new IllegalArgumentException("Invalid command.");
          }
        } catch (IllegalArgumentException e) {
          view.writeMessage(e.getMessage() + System.lineSeparator());
        }
      }
      view.printMenu();
    }
  }
}
