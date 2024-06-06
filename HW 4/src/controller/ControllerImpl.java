package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import controller.command.Crossover;
import controller.command.GainOrLoss;
import controller.command.MovingAverage;
import model.IModel;

public class ControllerImpl implements IController {
  private final Appendable out;
  private final Readable in;

  public ControllerImpl(Appendable out, Readable in) throws IllegalArgumentException {
    if ((in == null) || (out == null)) {
      throw new IllegalArgumentException("Sheet, readable or appendable is null");
    }
    this.out = out;
    this.in = in;
  }

  @Override
  public void go(IModel model) {
    Scanner sc = new Scanner(in);
    boolean quit = false;

    this.welcomeMessage();

    while (!quit && sc.hasNext()) {
//      writeMessage("Please enter an instruction: ");
      String userInstruction = sc.next();
      if (userInstruction.equals("Q") || userInstruction.equals("q")) {
        quit = true;
      } else {
        processCommand(userInstruction, sc, model);
      }
    }

    this.endMessage();
  }

  protected void processCommand(String userInstruction, Scanner sc, IModel model) {
    int date1;
    int date2;
    int averageWindow;
    switch (userInstruction) {
      case "1":
        try {
          writeMessage("Please enter a starting date: " + System.lineSeparator());
          date1 = sc.nextInt();
          writeMessage("Please enter a ending date: " + System.lineSeparator());
          date2 = sc.nextInt();
          GainOrLoss gainOrLoss = new GainOrLoss(date1, date2);
          //$GOOG to be replaced with AlphaVantage info
          writeMessage("The gain/loss of " + "$GOOG" + " is: "
                  + gainOrLoss.run(model) + System.lineSeparator());
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "2":
        try {
          writeMessage("Please enter how many days to base the average "
                  + "on (x in x-day moving average): " + System.lineSeparator());
          averageWindow = sc.nextInt();
          writeMessage("Please enter the day you want to see the moving average for: " + System.lineSeparator());
          //date as int
          date1 = sc.nextInt();
          MovingAverage movingAverage = new MovingAverage(averageWindow, date1);
          //$GOOG to be replaced with AlphaVantage info
          //<PLACEHOLDER DATE> to be replaced with dates
          writeMessage("The moving average of " + "$GOOG" + " on "
                  + "<PLACEHOLDER DATE>" + " is: " + movingAverage.run(model) + System.lineSeparator());
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "3":
        try {
          writeMessage("Please enter how many days to base the averages "
                  + "on (x in x-day moving average): " + System.lineSeparator());
          averageWindow = sc.nextInt();
          writeMessage("Please enter the starting date: " + System.lineSeparator());
          //date as int
          date1 = sc.nextInt();
          writeMessage("Please enter the ending date: " + System.lineSeparator());
          //date as int
          date2 = sc.nextInt();
          Crossover crossover = new Crossover(averageWindow, date1, date2);
          //$GOOG to be replaced with AlphaVantage info
          //<PLACEHOLDER DATE> to be replaced with dates
          writeMessage("From "
                  + "<PLACEHOLDER DATE 1>"
                  + " to "
                  + "<PLACEHOLDER DATE 2>"
                  + ", the "
                  + averageWindow
                  + "-day crossovers for "
                  + "$GOOG"
                  + " were on the following dates: ");
          //temporarily an arraylist of integers for the dates
          ArrayList<Calendar> result = crossover.run(model);
          for (int i = 0; i < result.size(); i++) {
            if (i == result.size() - 1) {
              writeMessage(result.get(i) + System.lineSeparator());
            } else {
              writeMessage(result.get(i) + ", ");
            }
          }
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "M":
      case "m":
        printMenu();
        break;
      default:
        writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
    }
  }

  protected void writeMessage(String message) throws IllegalStateException {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  protected void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to Stock Simulation!" + System.lineSeparator());
    printMenu();
  }

  protected void printMenu() {
    writeMessage("Please enter the character in the parentheses. Supported user instructions are: " + System.lineSeparator());
    writeMessage("(1) Find out stock gain or lose in a time period."
            + System.lineSeparator());
    writeMessage("(2) Find out x-day moving average on a given day."
            + System.lineSeparator());
    writeMessage("(3) Find out which days are x-day crossovers in a time period."
            + System.lineSeparator());
    writeMessage("(M)enu (show the menu again)" + System.lineSeparator());
    writeMessage("(Q)uit (closes the program)" + System.lineSeparator());
  }

  protected void endMessage() {
    writeMessage("Thank you for using this program!");
  }
}
