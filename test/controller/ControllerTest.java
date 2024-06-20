package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.ModelImpl2;

import static org.junit.Assert.assertEquals;


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

  private String printMenu() {
    return "Please enter the character in the parentheses."
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
            + "(8) Save a portfolio."
            + System.lineSeparator()
            + "(9) Load a portfolio."
            + System.lineSeparator()
            + "(10) Rebalance a portfolio."
            + System.lineSeparator()
            + "(11) Get Portfolio Performance."
            + System.lineSeparator()
            + "(12) Show a Portfolio at a date."
            + System.lineSeparator()
            + "(M)enu (show the menu again)"
            + System.lineSeparator()
            + "(Q)uit (closes the program)"
            + System.lineSeparator();
  }

  /**
   * This private helper method simply reduces clutter by having the program load up GOOG.
   *
   * @return String that is to be appended to expected.
   */
  private String initializeSTOCK() {
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
            + "(8) Save a portfolio."
            + System.lineSeparator()
            + "(9) Load a portfolio."
            + System.lineSeparator()
            + "(10) Rebalance a portfolio."
            + System.lineSeparator()
            + "(11) Get Portfolio Performance."
            + System.lineSeparator()
            + "(12) Show a Portfolio at a date."
            + System.lineSeparator()
            + "(M)enu (show the menu again)"
            + System.lineSeparator()
            + "(Q)uit (closes the program)"
            + System.lineSeparator()
            + "Which stock do you want to load? "
            + System.lineSeparator()
            + "Your stock has been populated. "
            + System.lineSeparator()
            + printMenu();
  }

  private String initializeSTOCKNoWelcome() {
    return "Please enter the character in the parentheses."
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
            + "(8) Save a portfolio."
            + System.lineSeparator()
            + "(9) Load a portfolio."
            + System.lineSeparator()
            + "(10) Rebalance a portfolio."
            + System.lineSeparator()
            + "(11) Get Portfolio Performance."
            + System.lineSeparator()
            + "(12) Show a Portfolio at a date."
            + System.lineSeparator()
            + "(M)enu (show the menu again)"
            + System.lineSeparator()
            + "(Q)uit (closes the program)"
            + System.lineSeparator()
            + "Which stock do you want to load? "
            + System.lineSeparator()
            + "Your stock has been populated. "
            + System.lineSeparator()
            + printMenu();
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
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Error: Make sure to spell the ticker correctly and populate first."
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
    input.append("5" + System.lineSeparator());
    input.append("Invalid!" + System.lineSeparator());
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Error: Make sure to spell the ticker correctly and populate first."
            + System.lineSeparator()
            + printMenu()
            + "Creating new portfolio... "
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "Error: Make sure to spell the ticker correctly and populate first."
            + System.lineSeparator()
            + printMenu());
    Readable inputter2 = new StringReader(input.toString());
    Appendable output2 = new StringBuilder().append(output);
    ControllerImpl tester2 = new ControllerImpl(output2, inputter2);
    tester2.init(new ModelImpl2());
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
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Error: Date should be written as: YYYY-MM-DD"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
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
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Error: Please enter valid months and days."
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
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
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Error: Sorry, stock information for 0001-01-01 doesn't exist."
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
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
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Please enter a ending date: "
            + System.lineSeparator()
            + "The gain/loss of GOOG is: 3.140000000000015"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
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
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter a starting date: "
            + System.lineSeparator()
            + "Please enter a ending date: "
            + System.lineSeparator()
            + "Error: Ending date should not be before starting date."
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
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
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter how many days to base the average on (x in x-day moving average): "
            + System.lineSeparator()
            + "Please enter the day you want to see the moving average for: "
            + System.lineSeparator()
            + "The moving average of GOOG on 2024-01-05 is: 137.9885"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
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
    expected.append(initializeSTOCK()
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
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
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
    expected.append(initializeSTOCK()
            + "Which stock do you want to analyze? "
            + System.lineSeparator()
            + "Please enter how many days to base the averages on (x in x-day moving average): "
            + System.lineSeparator()
            + "Please enter the starting date: "
            + System.lineSeparator()
            + "Please enter the ending date: "
            + System.lineSeparator()
            + "Error: At least one day in the time window doesn't have information about $GOOG"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
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
    input.append("2024-5-15" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("N" + System.lineSeparator());
    input.append("6" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("AMZN" + System.lineSeparator());
    input.append("30" + System.lineSeparator());
    input.append("2024-5-15" + System.lineSeparator());
    input.append("7" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("2024-5-15" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeSTOCK()
            + "Which stock do you want to load? "
            + System.lineSeparator()
            + "Your stock has been populated. "
            + System.lineSeparator()
            + printMenu()
            + "Creating new portfolio... "
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "How many shares of GOOG would you like? "
            + System.lineSeparator()
            + "At what date would you like to buy these shares? "
            + System.lineSeparator()
            + "Adding 30 shares of GOOG to this portfolio..."
            + System.lineSeparator()
            + "Enter a name for this portfolio: "
            + System.lineSeparator()
            + "Would you like to add one more stock to this portfolio? Y/N"
            + System.lineSeparator()
            + "Success! Created new portfolio."
            + System.lineSeparator()
            + printMenu()
            + "Which portfolio would you like to add a stock to? "
            + System.lineSeparator()
            + "Which stock would you like to add to Test? "
            + System.lineSeparator()
            + "How many shares would you like to add? "
            + System.lineSeparator()
            + "At what date would you like to buy these shares? "
            + System.lineSeparator()
            + printMenu()
            + "Which portfolio do you want to analyze? "
            + System.lineSeparator()
            + "Enter a date to calculate the value of Test at that date."
            + System.lineSeparator()
            + "The value of Test on 2024-05-15 is: 10796.1"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This verifies that if a date before shares is inputted, then the price will obviously be 0.
   */
  @Test
  public void testInvalidGetPortfolioValueDate() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("5" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("30" + System.lineSeparator());
    input.append("2024-5-10" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("N" + System.lineSeparator());
    input.append("7" + System.lineSeparator());
    input.append("Test" + System.lineSeparator());
    input.append("0-1-1" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeSTOCK()
            + "Creating new portfolio... "
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "How many shares of GOOG would you like? "
            + System.lineSeparator()
            + "At what date would you like to buy these shares? "
            + System.lineSeparator()
            + "Adding 30 shares of GOOG to this portfolio..."
            + System.lineSeparator()
            + "Enter a name for this portfolio: "
            + System.lineSeparator()
            + "Would you like to add one more stock to this portfolio? Y/N"
            + System.lineSeparator()
            + "Success! Created new portfolio."
            + System.lineSeparator()
            + printMenu()
            + "Which portfolio do you want to analyze? "
            + System.lineSeparator()
            + "Enter a date to calculate the value of Test at that date."
            + System.lineSeparator()
            + "The value of Test on 0001-01-01 is: 0.0"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * This tests the menu option in the user interface in controller. Note, the repeated menu
   * is an intended functionality.
   */
  @Test
  public void goMenuTest() {
    StringBuilder sb = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to Stock Simulation!"
            + System.lineSeparator()
            + printMenu()
            + printMenu());
    sb.append("M" + System.lineSeparator());
    expected.append(printMenu());
    Readable input = new StringReader(sb.toString());
    Appendable output = new StringBuilder();
    ControllerImpl test = new ControllerImpl(output, input);
    test.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
  }

  private void appendStockSetup(StringBuilder input) {
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("1" + System.lineSeparator());
    input.append("META" + System.lineSeparator());
    input.append("1" + System.lineSeparator());
    input.append("AMZN" + System.lineSeparator());
    input.append("5" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("10" + System.lineSeparator());
    input.append("2024-5-10" + System.lineSeparator());
    input.append("IHATEOOD" + System.lineSeparator());
    input.append("Y" + System.lineSeparator());
    input.append("AMZN" + System.lineSeparator());
    input.append("10" + System.lineSeparator());
    input.append("2024-5-10" + System.lineSeparator());
    input.append("Y" + System.lineSeparator());
    input.append("META" + System.lineSeparator());
    input.append("10" + System.lineSeparator());
    input.append("2024-5-10" + System.lineSeparator());
    input.append("N" + System.lineSeparator());
    input.append("8" + System.lineSeparator());
  }

  private String forPortfolioV2Append() {
    return initializeSTOCK()
            + "Which stock do you want to load? "
            + System.lineSeparator()
            + "Your stock has been populated. "
            + System.lineSeparator()
            + initializeSTOCKNoWelcome()
            + "Creating new portfolio... "
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "How many shares of GOOG would you like? "
            + System.lineSeparator()
            + "At what date would you like to buy these shares? "
            + System.lineSeparator()
            + "Adding 10 shares of GOOG to this portfolio..."
            + System.lineSeparator()
            + "Enter a name for this portfolio: "
            + System.lineSeparator()
            + "Would you like to add one more stock to this portfolio? Y/N"
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "How many shares of AMZN would you like? "
            + System.lineSeparator()
            + "At what date would you like to buy these shares? "
            + System.lineSeparator()
            + "Adding 10 shares of AMZN to this portfolio..."
            + System.lineSeparator()
            + "Would you like to add one more stock to this portfolio? Y/N"
            + System.lineSeparator()
            + "Which stock would you like to add into this portfolio? "
            + System.lineSeparator()
            + "How many shares of META would you like? "
            + System.lineSeparator()
            + "At what date would you like to buy these shares? "
            + System.lineSeparator()
            + "Adding 10 shares of META to this portfolio..."
            + System.lineSeparator()
            + "Would you like to add one more stock to this portfolio? Y/N"
            + System.lineSeparator()
            + "Success! Created new portfolio."
            + System.lineSeparator()
            + printMenu();
  }

  /**
   * Tests the SavePortfolio command, and the PortfolioToString command.
   */
  @Test
  public void saveToStringPortfolioTest() {
    StringBuilder input = new StringBuilder();
    appendStockSetup(input);
    input.append("IHATEOOD" + System.lineSeparator());
    input.append("12" + System.lineSeparator());
    input.append("IHATEOOD" + System.lineSeparator());
    input.append("2024-5-10" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(forPortfolioV2Append()
            + "Which portfolio would you like to save? "
            + System.lineSeparator()
            + "Saving Portfolio... "
            + System.lineSeparator()
            + printMenu()
            + "Which portfolio do you want to see? "
            + System.lineSeparator()
            + "At which date do you want to see Portfolio at? "
            + System.lineSeparator()
            + "**** Portfolio Name: IHATEOOD ****"
            + System.lineSeparator()
            + "Stocks: "
            + System.lineSeparator()
            + "$GOOG"
            + System.lineSeparator()
            + "- Stock's value at 2024-5-10: 170.29"
            + System.lineSeparator()
            + "- Shares owned: 10.0"
            + System.lineSeparator()
            + "- Valuation Percentage: 20.419199731405207%"
            + System.lineSeparator()
            + "$META"
            + System.lineSeparator()
            + "- Stock's value at 2024-5-10: 476.2"
            + System.lineSeparator()
            + "- Shares owned: 10.0"
            + System.lineSeparator()
            + "- Valuation Percentage: 57.10037531326068%"
            + System.lineSeparator()
            + "$AMZN"
            + System.lineSeparator()
            + "- Stock's value at 2024-5-10: 187.48"
            + System.lineSeparator()
            + "- Shares owned: 10.0"
            + System.lineSeparator()
            + "- Valuation Percentage: 22.480424955334126%"
            + System.lineSeparator()
            + "**** Total Portfolio Valuation at 2024-5-10: 8339.699999999999 ****"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * Checks that the program gives an error if you put in an invalid portfolio when trying
   * to save a portfolio.
   */
  @Test
  public void saveToStringPortfolioWrongPortfolioTest() {
    StringBuilder input = new StringBuilder();
    appendStockSetup(input);
    input.append("lul" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(forPortfolioV2Append()
            + "Which portfolio would you like to save? "
            + System.lineSeparator()
            + "Error: Invalid portfolio."
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * Checks if the program produces an error if you type in an invalid (not existent) portfolio
   * when you try to show a portfolio (PortfolioToString).
   */
  @Test
  public void saveToStringMissingPortfolioTest() {
    StringBuilder input = new StringBuilder();
    appendStockSetup(input);
    input.append("IHATEOOD" + System.lineSeparator());
    input.append("12" + System.lineSeparator());
    input.append("pleasefreemefromthisassignment" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(forPortfolioV2Append()
            + "Which portfolio would you like to save? "
            + System.lineSeparator()
            + "Saving Portfolio... "
            + System.lineSeparator()
            + printMenu()
            + "Which portfolio do you want to see? "
            + System.lineSeparator()
            + "Error: Invalid portfolio"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
  }


  /**
   * Tests the LoadPortfolio command.
   */
  @Test
  public void loadPortfolioTest() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("1" + System.lineSeparator());
    input.append("META" + System.lineSeparator());
    input.append("1" + System.lineSeparator());
    input.append("AMZN" + System.lineSeparator());
    input.append("9" + System.lineSeparator());
    input.append("IHATEOOD.xml" + System.lineSeparator());
    input.append("12" + System.lineSeparator());
    input.append("IHATEOOD" + System.lineSeparator());
    input.append("2024-5-10" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeSTOCK()
            + "Which stock do you want to load? "
            + System.lineSeparator()
            + "Your stock has been populated. "
            + System.lineSeparator()
            + initializeSTOCKNoWelcome()
            + "Which portfolio would you like to load?"
            + " Please type the entire file name. (example.xml)"
            + System.lineSeparator()
            + "- Dog.xml"
            + System.lineSeparator()
            + "- IHATEOOD.xml"
            + System.lineSeparator()
            + "- TestPort.xml"
            + System.lineSeparator()
            + "- testXml.xml"
            + System.lineSeparator()
            + "Loading Portfolio... "
            + System.lineSeparator()
            + printMenu()
            + "Which portfolio do you want to see? "
            + System.lineSeparator()
            + "At which date do you want to see Portfolio at? "
            + System.lineSeparator()
            + "**** Portfolio Name: IHATEOOD ****"
            + System.lineSeparator()
            + "Stocks: "
            + System.lineSeparator()
            + "$GOOG"
            + System.lineSeparator()
            + "- Stock's value at 2024-5-10: 170.29"
            + System.lineSeparator()
            + "- Shares owned: 10.0"
            + System.lineSeparator()
            + "- Valuation Percentage: 20.419199731405207%"
            + System.lineSeparator()
            + "$META"
            + System.lineSeparator()
            + "- Stock's value at 2024-5-10: 476.2"
            + System.lineSeparator()
            + "- Shares owned: 10.0"
            + System.lineSeparator()
            + "- Valuation Percentage: 57.10037531326068%"
            + System.lineSeparator()
            + "$AMZN"
            + System.lineSeparator()
            + "- Stock's value at 2024-5-10: 187.48"
            + System.lineSeparator()
            + "- Shares owned: 10.0"
            + System.lineSeparator()
            + "- Valuation Percentage: 22.480424955334126%"
            + System.lineSeparator()
            + "**** Total Portfolio Valuation at 2024-5-10: 8339.699999999999 ****"
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
  }

  /**
   * Tests that the program correctly returns an error, if when asked which portfolio to load,
   * the input is not found as a file in the savedPortfolios directory.
   */
  @Test
  public void loadPortfolioInvalidPortfolioTest() {
    StringBuilder input = new StringBuilder();
    input.append("1" + System.lineSeparator());
    input.append("GOOG" + System.lineSeparator());
    input.append("1" + System.lineSeparator());
    input.append("META" + System.lineSeparator());
    input.append("1" + System.lineSeparator());
    input.append("AMZN" + System.lineSeparator());
    input.append("9" + System.lineSeparator());
    input.append("nope" + System.lineSeparator());
    Readable inputter = new StringReader(input.toString());
    StringBuilder expected = new StringBuilder();
    expected.append(initializeSTOCK()
            + "Which stock do you want to load? "
            + System.lineSeparator()
            + "Your stock has been populated. "
            + System.lineSeparator()
            + initializeSTOCKNoWelcome()
            + "Which portfolio would you like to load?"
            + " Please type the entire file name. (example.xml)"
            + System.lineSeparator()
            + "- Dog.xml"
            + System.lineSeparator()
            + "- IHATEOOD.xml"
            + System.lineSeparator()
            + "- TestPort.xml"
            + System.lineSeparator()
            + "- testXml.xml"
            + System.lineSeparator()
            + "Error: Are you sure you entered the full file name?"
            + " If so, this portfolio doesn't exist or isn't saved."
            + System.lineSeparator()
            + printMenu());
    Appendable output = new StringBuilder();
    ControllerImpl tester = new ControllerImpl(output, inputter);
    tester.init(new ModelImpl2());
    assertEquals(expected.toString(), output.toString());
  }


}