package controller.command;

import java.io.IOException;
import java.util.Calendar;

public class ControllerUtil {
  public static void writeMessage(String message, Appendable out) throws IllegalArgumentException {
    try {
      out.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  public static void welcomeMessage(Appendable out) throws IllegalStateException {
    writeMessage("Welcome to Stock Simulation!" + System.lineSeparator(), out);
    printMenu(out);
  }

  public static void printMenu(Appendable out) {
    writeMessage("Please enter the character in the parentheses."
            + " Supported user instructions are: " + System.lineSeparator(), out);
    writeMessage("(1) Find out stock gain or lose in a time period."
            + System.lineSeparator(), out);
    writeMessage("(2) Find out x-day moving average on a given day."
            + System.lineSeparator(), out);
    writeMessage("(3) Find out which days are x-day crossovers in a time period."
            + System.lineSeparator(), out);
    writeMessage("(M)enu (show the menu again)" + System.lineSeparator(), out);
    writeMessage("(Q)uit (closes the program)" + System.lineSeparator(), out);
  }

  public static void endMessage(Appendable out) {
    writeMessage("Thank you for using this program!", out);
  }

  public static Calendar getCalendar(String input) throws IllegalArgumentException {
    String[] result = input.split("-");
    if (result.length > 3) {
      throw new IllegalArgumentException("Error: Date input has too many arguments.");
    }
    Calendar cal = Calendar.getInstance();
    cal.set(Integer.parseInt(result[2]), Integer.parseInt(result[0]), Integer.parseInt(result[1]));
    return cal;
  }
}
