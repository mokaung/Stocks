package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import model.MockModel;
import model.ModelImpl;

import static org.junit.Assert.*;

public class ControllerImplTest {
  /**
   * This tests the menu option in the user interface in controller. The indvidual commands
   * are tested in CommandsTest in the command package in the test directory, since
   * user input is implemented in individual commands.
   */
  @Test
  public void goMenuQuitTest() {
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