//package controller;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.function.Supplier;
//
//import View.IView;
//import controller.command.AddStockToPortfolio;
//import controller.command.ControllerUtil;
//import controller.command.CreatePortfolio;
//import controller.command.Crossover;
//import controller.command.GainOrLoss;
//import controller.command.GetPerformance;
//import controller.command.GetPortfolioValue;
//import controller.command.ICommand;
//import controller.command.LoadPortfolio;
//import controller.command.MovingAverage;
//import controller.command.Populate;
//import controller.command.PortFolioToString;
//import controller.command.Rebalance;
//import controller.command.SavePortfolio;
//import model.IModel2;
//
//import static controller.command.ControllerUtil.writeMessage;
//import static java.lang.System.in;
//import static java.lang.System.out;
//
//public class GUIController implements IController{
//
//  private IView view;
//  protected final Map<String, Supplier<ICommand>> commands;
//
//  public GUIController(IView view) {
//    this.view = view;
//    this.commands = new HashMap<>();
//    commands.put("1", () -> new Populate(view));
//    commands.put("2", () -> new GainOrLoss(view));
//    commands.put("3", () -> new MovingAverage(view));
//    commands.put("4", () -> new Crossover(view));
//    commands.put("5", () -> new CreatePortfolio(view));
//    commands.put("6", () -> new AddStockToPortfolio(view));
//    commands.put("7", () -> new GetPortfolioValue(view));
//    commands.put("8", () -> new SavePortfolio(view));
//    commands.put("9", () -> new LoadPortfolio(view));
//    commands.put("10", () -> new Rebalance(view));
//    commands.put("11", () -> new GetPerformance(view));
//    commands.put("12", () -> new PortFolioToString(view));
//  }
////
////  /**
////   * The init method runs the program. It provides the user with a interface, allowing the user
////   * to access commands. Gets helper methods from the CommandUtil class.
////   *
////   * @param model Model for the program, houses all the computation.
////   */
////  @Override
////  public void init(IModel2 model) {
////    Scanner sc = new Scanner(in);
////
////    ControllerUtil.welcomeMessage(out);
////    while (sc.hasNext()) {
////      ICommand c;
////      String in = sc.next();
////      if (in.equalsIgnoreCase("q")) {
////        ControllerUtil.endMessage(this.out);
////        return;
////      }
////      if (in.equalsIgnoreCase("M")) {
////        ControllerUtil.printMenu(this.out);
////      }
////      Supplier<ICommand> cmd = commands.getOrDefault(in, null);
////      if (cmd != null) {
////        ICommand runner = cmd.get();
////        try {
////          runner.run(sc, model);
////        } catch (IllegalArgumentException e) {
////          writeMessage("Error: " + e.getMessage() + System.lineSeparator(), out);
////        }
////      } else {
////        try {
////          if (!(in.equalsIgnoreCase("M") || in.equalsIgnoreCase("m"))) {
////            throw new IllegalArgumentException("Invalid command.");
////          }
////        } catch (IllegalArgumentException e) {
////          writeMessage(e.getMessage() + System.lineSeparator(), out);
////        }
////      }
////      ControllerUtil.printMenu(this.out);
////    }
////  }
////}
//
//}
