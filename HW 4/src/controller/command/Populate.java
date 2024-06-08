package controller.command;

import java.util.Scanner;

import model.IModel;

import static controller.command.ControllerUtil.writeMessage;

import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.AlphaVantageStreamReader;
import controller.CSVReader;
import controller.IReader;

public class Populate implements ICommand {
  private final Appendable out;

  public Populate(Appendable out) {
    this.out = out;
  }

  @Override
  public void run(Scanner sc, IModel model) {
    writeMessage("Which stock do you want to load? " + System.lineSeparator(), out);
    String ticker = sc.next();
    if (!model.isValidTicker(ticker)) {
      throw new IllegalArgumentException("Invalid ticker.");
    }
    IReader alpha = new AlphaVantageStreamReader(ticker);

    try {
      model.populate(alpha.getReadable(), ticker);
    } catch (Exception e) {
      writeMessage("Your stock could not be loaded. Would you like to use a preloaded stock? Please type 'yes' if so. "
              + System.lineSeparator(), out);
      String answer = sc.next();
      if (answer.equalsIgnoreCase("yes")) {
        csvGo(sc, model);
      } else {
        throw new IllegalArgumentException("Sorry, your stock could not be loaded and you chose to not use a preloaded stock.");
      }
    }
    writeMessage("Your stock has been populated. " + System.lineSeparator(), out);
  }

  private void csvGo(Scanner sc, IModel model) {
    writeMessage("Which stock would you like to use? "
            + System.lineSeparator(), out);
    writeMessage("(1) FaceBook" + System.lineSeparator(), out);
    writeMessage("(2) Apple" + System.lineSeparator(), out);
    writeMessage("(3) Amazon" + System.lineSeparator(), out);
    writeMessage("(4) Google" + System.lineSeparator(), out);
    writeMessage("(5) Netflix" + System.lineSeparator(), out);
    String input = sc.next();
    String ticker = "";
    switch (input) {
      case "1":
        ticker = "META";
        break;
      case "2":
        ticker = "APPL";
        break;
      case "3":
        ticker = "AMZN";
        break;
      case "4":
        ticker = "GOOG";
        break;
      case "5":
        ticker = "NFLX";
        break;
      default:
        throw new IllegalArgumentException("Please type a valid input(1-5).");
    }
    try {
      IReader reader = new CSVReader(ticker);

      model.populate(reader.getReadable(), ticker);

    } catch (Exception e) {
      System.out.println(e);
      throw new IllegalArgumentException("Your stock cannot be loaded.");
    }
    writeMessage("Your stock has been populated. " + System.lineSeparator(), out);
  }
}
