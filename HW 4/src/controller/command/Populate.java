package controller.command;

import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.AlphaVantageStreamReader;
import controller.CSVReader;
import controller.IReader;
import model.IModel;

import static controller.command.ControllerUtil.writeMessage;

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
      writeMessage("Your stock could not be loaded. Would you like to use a preloaded stock? "
              + System.lineSeparator(), out);
      String answer = sc.next();
      if (answer.equalsIgnoreCase("yes")) {
        csvGo(sc, model, ticker);
      } else {
        ControllerUtil.endMessage(this.out);
      }
    }
    writeMessage("Your stock has been populated. " + System.lineSeparator(), out);
  }

  private void csvGo(Scanner sc, IModel model, String ticker) {
    writeMessage("Which stock would you like to use? "
            + System.lineSeparator(), out);
    writeMessage("(1) FaceBook" + System.lineSeparator(), out);
    writeMessage("(2) Apple" + System.lineSeparator(), out);
    writeMessage("(3) Amazon" + System.lineSeparator(), out);
    writeMessage("(4) Google" + System.lineSeparator(), out);
    writeMessage("(5) Netflix" + System.lineSeparator(), out);

    try {
      IReader reader = new CSVReader(ticker);
      model.populate(reader.getReadable(), ticker);
    } catch (FileNotFoundException e) {
      writeMessage("Your stock cannot be loaded." + System.lineSeparator(), out);
    }
    writeMessage("Your stock has been populated. " + System.lineSeparator(), out);
  }
}
