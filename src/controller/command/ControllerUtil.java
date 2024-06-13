package controller.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Util class for Controller and commands. Houses methods used for output and helper for getting
 * valid dates from input.
 */
public class ControllerUtil {

  /**
   * Method to write messages into the Appendable out, which is shown to the user.
   *
   * @param message message that is to be shown.
   * @param out     The appendable used for outputs in the program.
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
   *
   * @param out The appendable used for outputs in the program.
   */
  public static void welcomeMessage(Appendable out) {
    writeMessage("Welcome to Stock Simulation!" + System.lineSeparator(), out);
    printMenu(out);
  }

  /**
   * Outputs the menu of the program. Used when user wishes to see menu and at the start of
   * program.
   *
   * @param out The appendable used for outputs in the program.
   */
  public static void printMenu(Appendable out) {
    writeMessage("Please enter the character in the parentheses." + " Supported user instructions are: " + System.lineSeparator(), out);
    writeMessage("(1) Populate the system with your desired stock." + System.lineSeparator(), out);
    writeMessage("(2) Find out stock gain or lose in a time period." + System.lineSeparator(), out);
    writeMessage("(3) Find out x-day moving average on a given day." + System.lineSeparator(), out);
    writeMessage("(4) Find out which days are x-day crossovers in a time period." + System.lineSeparator(), out);
    writeMessage("(5) Create a new portfolio." + System.lineSeparator(), out);
    writeMessage("(6) Add new stocks to an existing portfolio." + System.lineSeparator(), out);
    writeMessage("(7) Find out the value of an existing portfolio." + System.lineSeparator(), out);
    writeMessage("(8) Save a portfolio." + System.lineSeparator(), out);
    writeMessage("(9) Load a portfolio." + System.lineSeparator(), out);
    writeMessage("(M)enu (show the menu again)" + System.lineSeparator(), out);
    writeMessage("(Q)uit (closes the program)" + System.lineSeparator(), out);
  }

  /**
   * Outputs the ending message when the program is quit.
   *
   * @param out The appendable used for outputs in the program.
   */
  public static void endMessage(Appendable out) {
    writeMessage("Thank you for using this program!", out);
  }

  /**
   * Helper method that converts a user input into a valid LocalDate object. Checks for invalid
   * date inputs as well.
   *
   * @param input User input for a date.
   * @return LocalDate object that is used to access stock information.
   * @throws IllegalArgumentException when input has too many arguments for LocalDate.
   */
  public static LocalDate getLocalDate(String input) throws IllegalArgumentException {
    String[] result = input.split("-");
    if (result.length != 3) {
      throw new IllegalArgumentException("Date should be written as: YYYY-MM-DD");
    }
    int year = Integer.parseInt(result[0]);
    int month = Integer.parseInt(result[1]);
    int day = Integer.parseInt(result[2]);
    try {
      return LocalDate.of(year, month, day);
    } catch (Exception e) {
      throw new IllegalArgumentException("Please enter valid months and days.");
    }
  }

  /**
   * Formats the LocalDate in the year - month - date format
   *
   * @param cal date
   * @return formatted date
   */
  public static String calToString(LocalDate cal) {
    return cal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
