package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import View.IView;
import View.IViewListener;
import controller.command.ICommand;
import controller.command.ICommand2;
import model.IModel2;

public class ControllerImplGUI implements IController, IViewListener {
  private final Map<String, Supplier<ICommand2>> commands;
  private final IView view;
  private final IModel2 model;

  public ControllerImplGUI(Map<String, Supplier<ICommand>> commands, IView view, IModel2 model) {
    this.view = view;
    this.model = model;
    this.commands = new HashMap<>();

    commands.put("1", () -> new Populate2(view));
    commands.put("2", () -> new GainOrLoss2(view));
    commands.put("3", () -> new MovingAverage2(view));
    commands.put("4", () -> new Crossover2(view));
    commands.put("5", () -> new CreatePortfolio2(view));
    commands.put("6", () -> new AddStockToPortfolio2(view));
    commands.put("7", () -> new GetPortfolioValue2(view));
    commands.put("8", () -> new SavePortfolio2(view));
    commands.put("9", () -> new LoadPortfolio2(view));
    commands.put("10", () -> new Rebalance2(view));
    commands.put("11", () -> new GetPerformance2(view));
    commands.put("12", () -> new PortFolioToString2(view));
  }

  @Override
  public void init(IModel2 model) {


  }
}