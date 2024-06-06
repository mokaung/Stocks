package controller.command;

import java.io.IOException;
import java.util.Calendar;

/**
 * Util class for Controller and commands. Houses methods used for output and helper for getting
 * valid dates from input.
 */
public class ControllerUtil {

  /**
   * Method to write messages into the Appendable out, which is shown to the user.
   * @param message message that is to be shown.
   * @param out The appendable used for outputs in the program.
   * @throws IllegalArgumentException If there are any errors.
   */
  public static void writeMessage(String message, Appendable out) throws IllegalArgumentException {
    try {
      out.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Outputs the welcome message of the program. Used at the start of program.
   * @param out The appendable used for outputs in the program.
   */
  public static void welcomeMessage(Appendable out) {
    writeMessage("Welcome to Stock Simulation!" + System.lineSeparator(), out);
    printMenu(out);
  }

  /**
   * Outputs the menu of the program. Used when user wishes to see menu and at the start of
   * program.
   * @param out The appendable used for outputs in the program.
   */
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

  /**
   * Outputs the ending message when the program is quit.
   * @param out The appendable used for outputs in the program.
   */
  public static void endMessage(Appendable out) {
    writeMessage("Thank you for using this program!", out);
  }

  /**
   * Helper method that converts a user input into a valid Calendar object. Checks for invalid
   * date inputs as well.
   * @param input User input for a date.
   * @return Calendar object that is used to access stock information.
   * @throws IllegalArgumentException when input has too many arguments for Calendar.
   */
  public static Calendar getCalendar(String input) throws IllegalArgumentException {
    String[] result = input.split("-");
    /*
    Calendar has lenient parsing, so is exception for valid date necessary?
     */
    if (result.length > 3) {
      throw new IllegalArgumentException("Error: Date input has too many arguments.");
    }
    Calendar cal = Calendar.getInstance();
    cal.set(Integer.parseInt(result[2]), Integer.parseInt(result[0]), Integer.parseInt(result[1]));
    return cal;
  }
}
