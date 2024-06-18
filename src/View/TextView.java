package View;

import java.io.IOException;
import java.util.Scanner;

public class TextView implements IViewText {
  private final Appendable out;
  private final Readable in;

  public TextView(Appendable out, Readable in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public String readMessage() {
    Scanner sc = new Scanner(in);
    return sc.next();
  }

  @Override
  public void writeMessage(String message) throws IllegalArgumentException {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void welcomeMessage(Appendable out) {
    writeMessage("Welcome to Stock Simulation!" + System.lineSeparator());
    printMenu();
  }

  @Override
  public void printMenu() {
    writeMessage("Please enter the character in the parentheses."
            + " Supported user instructions are: " + System.lineSeparator());
    writeMessage("(1) Populate the system with your desired stock."
            + System.lineSeparator());
    writeMessage("(2) Find out stock gain or lose in a time period."
            + System.lineSeparator());
    writeMessage("(3) Find out x-day moving average on a given day."
            + System.lineSeparator());
    writeMessage("(4) Find out which days are x-day crossovers in a time period."
            + System.lineSeparator());
    writeMessage("(5) Create a new portfolio." + System.lineSeparator());
    writeMessage("(6) Add new stocks to an existing portfolio."
            + System.lineSeparator());
    writeMessage("(7) Find out the value of an existing portfolio."
            + System.lineSeparator());
    writeMessage("(8) Save a portfolio." + System.lineSeparator());
    writeMessage("(9) Load a portfolio." + System.lineSeparator());
    writeMessage("(10) Rebalance a portfolio." + System.lineSeparator());
    writeMessage("(11) Get Portfolio Performance." + System.lineSeparator());
    writeMessage("(12) Show a Portfolio at a date." + System.lineSeparator());
    writeMessage("(M)enu (show the menu again)" + System.lineSeparator());
    writeMessage("(Q)uit (closes the program)" + System.lineSeparator());
  }


  @Override
  public void endMessage() {
    writeMessage("Thank you for using this program!");
  }

  @Override
  public void render() {
    this.welcomeMessage(out);
  }
}
