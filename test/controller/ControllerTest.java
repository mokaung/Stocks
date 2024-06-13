package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import Model.ModelImpl;

import static org.junit.Assert.*;

/**
 * This class tests all the classes in the Controller package, including the ones in Command
 * package. Note: The class Populate is inherently tested everytime GOOG is loaded using
 * initialize GOOG.
 */
public class ControllerTest {
  StringBuilder sb;
  StringBuilder expected;


  /**
   * This setup method initializes the sb that will be the input, and the expected which
   * will be compared to the output of the program.
   */
  @Before
  public void setup() {
    sb = new StringBuilder();
    expected = new StringBuilder();
  }

  /**
   * This private helper method simply reduces clutter by having the program load up GOOG.
   *
   * @return String that is to be appended to expected.
   */
  private String initializeGOOG() {
    return "Welcome to Stock Simulation!"
            + System.lineSeparator()
            + "Please enter the character in the parentheses."
            + " Supported user instructions are: "
            + System.lineSeparator()
            + "(1) Populate the system with your desired stock."
            + System.lineSeparator()
            + "(2) Find out stock gain or lose in a time period."
            + System.lineSeparator()
            + "(3) Find out x-day moving average on a given day."
            + System.lineSeparator()
            + "(4) Find out which days are x-day crossovers in a time period."
            + System.lineSeparator()
            + "(5) Create a new portfolio."
            + System.lineSeparator()
            + "(6) Add new stocks to an existing portfolio."
            + System.lineSeparator()
            + "(7) Find out the value of an existing portfolio."
            + System.lineSeparator()
            + "(M)enu (show the menu again)"
            + System.lineSeparator()
            + "(Q)uit (closes the program)"
            + System.lineSeparator()
            + "Which stock do you want to load? "
            + System.lineSeparator()
            + "Your stock has been populated. "
            + System.lineSeparator();
  }

  /**
   * This test verifies that when an invalid ticker is inputted, it will show user an error.
   * To be extensive, this test goes through the MovingAverage command and CreatePortfolio
   * command. Ensures that any wrong ticker will not be accepted.
   */
  @Test
  public void testInvalidTickerSymbol() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("3" + System.lineSeparator());
    input.append("Invalid!" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Error: Make sure to spell the ticker correctly and populate first."
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
    input.append("5" + System.lineSeparator());
    input.append("Invalid!" + System.lineSeparator());
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Error: Make sure to spell the ticker correctly and populate first."
            + System.lineSeparator()
            + "Creating new portfolio... "
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "Error: Make sure to spell the ticker correctly and populate first."
            + System.lineSeparator());
    Readable inputter2 = new StringReader(input.toString());
    Appendable output2 = new StringBuilder().append(output);
    ControllerImpl tester2 = new ControllerImpl(output2, inputter2);
    tester2.go(new ModelImpl());
    assertEquals(expected.toString(), output2.toString());
  }

  /**
   * This test verifies that the date cannot in an invalid format. Dates must be YYYY-MM-DD.
   */
  @Test
  public void testInvalidDateFormat() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("2" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("WRONG" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Error: Date should be written as: YYYY-MM-DD"
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This test verifies that if a user put in the correct format, but entered dates that
   * don't exist, that the program will give them an error.
   */
  @Test
  public void testInvalidMonthDay() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("2" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("0-0-0" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Error: Please enter valid months and days."
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This test verifies that if the user inputted a date in the correct format, if the stock
   * does not have information at the date, that the program gives the user an error.
   */
  @Test
  public void testInvalidDate() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("2" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("0-1-1" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Error: Sorry, stock information for 0001-01-01 doesn't exist."
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This test verifies that GainOrLoss works properly.
   */
  @Test
  public void testGainOrLoss() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("2" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("2024-1-5" + System.lineSeparator());
    input.append("2024-1-8" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Please enter a ending date: "
            + System.lineSeparator()
            + "The gain/loss of GOOG is: 3.140000000000015"
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This test verifies that if the second date in any of the commands that require 2 dates is
   * earlier than the first date, that the program returns an error.
   */
  @Test
  public void testDate2BeforeDate1() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("2" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("2024-5-17" + System.lineSeparator());
    input.append("2024-5-13" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Please enter a ending date: "
            + System.lineSeparator()
            + "Error: Ending date should not be before starting date."
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This verifies that MovingAverage is working.
   */
  @Test
  public void testMovingAverage() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("3" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("30" + System.lineSeparator());
    input.append("2024-1-5" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter how many days to base the average on (x in x-day moving average): "
            + System.lineSeparator()
            + "Please enter the day you want to see the moving average for: "
            + System.lineSeparator()
            + "The moving average of GOOG on 2024-01-05 is: 137.9885"
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This verifies that Crossover is working.
   */
  @Test
  public void testCrossover() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("4" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("30" + System.lineSeparator());
    input.append("2024-5-13" + System.lineSeparator());
    input.append("2024-5-17" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter how many days to base the averages on (x in x-day moving average): "
            + System.lineSeparator()
            + "Please enter the starting date: "
            + System.lineSeparator()
            + "Please enter the ending date: "
            + System.lineSeparator()
            + "From 2024-05-13 to 2024-05-17, the 30-day crossovers for GOOG were on the following"
            + " dates: 2024-05-13, 2024-05-14, 2024-05-15, 2024-05-16"
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This verifies that if a user puts invalid dates when finding Crossover days that the program
   * will return an error.
   */
  @Test
  public void testCrossoverInvalidDates() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("4" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("30" + System.lineSeparator());
    input.append("2024-1-5" + System.lineSeparator());
    input.append("2024-1-8" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter how many days to base the averages on (x in x-day moving average): "
            + System.lineSeparator()
            + "Please enter the starting date: "
            + System.lineSeparator()
            + "Please enter the ending date: "
            + System.lineSeparator()
            + "Error: At least one day in the time window doesn't have information about $GOOG"
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This test verifies that all the commands regarding the portfolio work.
   */
  @Test
  public void testPortfolioCommands() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("1" + System.lineSeparator());
    input.append("AMZN" + System.lineSeparator());
    input.append("5" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("30" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("N" + System.lineSeparator());
    input.append("6" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("AMZN" + System.lineSeparator());
    input.append("30" + System.lineSeparator());
    input.append("7" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("2024-5-13" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Which stock do you want to load? "
            + System.lineSeparator()
            + "Your stock has been populated. "
            + System.lineSeparator()
            + "Creating new portfolio... "
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "How many shares of GOOG would you like? "
            + System.lineSeparator()
            + "Adding 30 shares of GOOG to this portfolio..."
            + System.lineSeparator()
            + "Enter a name for this portfolio: "
            + System.lineSeparator()
            + "Would you like to add one more stock to this portfolio? Y/N"
            + System.lineSeparator()
            + "Success! Created new portfolio."
            + System.lineSeparator()
            + "Which portfolio would you like to add a stock to? "
            + System.lineSeparator()
            + "Which stock would you like to add to Test? "
            + System.lineSeparator()
            + "How many shares would you like to add? "
            + System.lineSeparator()
            + "Which portfolio do you want to analyze? "
            + System.lineSeparator()
            + "Enter a date to calculate the value of Test at that date."
            + System.lineSeparator()
            + "The value of Test on 2024-05-13 is: 10724.099999999999"
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This verifies that if an invalid date is inputted when trying to get the value of the
   * portfolio, that the program will give an error.
   */
  @Test
  public void testInvalidGetPortfolioValueDate() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("5" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("30" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("N" + System.lineSeparator());
    input.append("7" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("0-1-1" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeGOOG()
            + "Creating new portfolio... "
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "How many shares of GOOG would you like? "
            + System.lineSeparator()
            + "Adding 30 shares of GOOG to this portfolio..."
            + System.lineSeparator()
            + "Enter a name for this portfolio: "
            + System.lineSeparator()
            + "Would you like to add one more stock to this portfolio? Y/N"
            + System.lineSeparator()
            + "Success! Created new portfolio."
            + System.lineSeparator()
            + "Which portfolio do you want to analyze? "
            + System.lineSeparator()
            + "Enter a date to calculate the value of Test at that date."
            + System.lineSeparator()
            + "Error: Sorry, information for the portfolio at "
            + "0000-01-01 is unavailable. Please try another date."
            + System.lineSeparator());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This tests the menu option in the user interface in controller.
   */
  @Test
  public void goMenuTest() {
    StringBuilder sb = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to Stock Simulation!"
            + System.lineSeparator()
            + "Please enter the character in the parentheses."
            + " Supported user instructions are: "
            + System.lineSeparator()
            + "(1) Populate the system with your desired stock."
            + System.lineSeparator()
            + "(2) Find out stock gain or lose in a time period."
            + System.lineSeparator()
            + "(3) Find out x-day moving average on a given day."
            + System.lineSeparator()
            + "(4) Find out which days are x-day crossovers in a time period."
            + System.lineSeparator()
            + "(5) Create a new portfolio."
            + System.lineSeparator()
            + "(6) Add new stocks to an existing portfolio."
            + System.lineSeparator()
            + "(7) Find out the value of an existing portfolio."
            + System.lineSeparator()
            + "(M)enu (show the menu again)"
            + System.lineSeparator()
            + "(Q)uit (closes the program)"
            + System.lineSeparator());
    sb.append("M" + System.lineSeparator());
    expected.append("Please enter the character in the parentheses."
            + " Supported user instructions are: "
            + System.lineSeparator()
            + "(1) Populate the system with your desired stock."
            + System.lineSeparator()
            + "(2) Find out stock gain or lose in a time period."
            + System.lineSeparator()
            + "(3) Find out x-day moving average on a given day."
            + System.lineSeparator()
            + "(4) Find out which days are x-day crossovers in a time period."
            + System.lineSeparator()
            + "(5) Create a new portfolio."
            + System.lineSeparator()
            + "(6) Add new stocks to an existing portfolio."
            + System.lineSeparator()
            + "(7) Find out the value of an existing portfolio."
            + System.lineSeparator()
            + "(M)enu (show the menu again)"
            + System.lineSeparator()
            + "(Q)uit (closes the program)"
            + System.lineSeparator());
    Readable input = new StringReader(sb.toString());
    Appendable output = new StringBuilder();
    ControllerImpl test = new ControllerImpl(output, input);
    test.go(new ModelImpl());
    assertEquals(expected.toString(), output.toString());
  }
}