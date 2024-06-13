package model;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ControllerImpl;
import controller.IController;

import static org.junit.Assert.assertEquals;

/**
 * Tester class to see if model is taking in the right controller commands.
 */
public class MockModelTest {
  StringReader in;
  IModel2 model;
  Readable reader;
  StringBuilder out;
  StringBuilder received;

  @Before
  public void setUp() throws Exception {
    received = new StringBuilder();
    model = new MockModel(received);
    reader = new StringReader("timestamp,open,high,low,close,volume\n"
            + "2024-06-06,181.7450,185.0000,181.4900,185.0000,31371151\n"
            + "2024-06-05,180.1000,181.5000,178.7500,181.2800,32116394\n "
            + "2024-06-04,177.6400,179.8200,176.4400,179.3400,27198388\n"
            + "2024-06-03,177.7000,178.7000,175.9200,178.3400,30786640\n"
            + "2024-05-31,178.3000,179.2100,173.8700,176.4400,58903939\n");

    model.populate(reader, "AMZN");
  }

  @Test
  public void crossover() {
    out = new StringBuilder();
    in = new StringReader("4 AMZN 3 2024-06-05 2024-06-06");
    IController controller = new ControllerImpl(out, in);
//    controller.init(model);
    assertEquals("populateisValidTickerisValidLocalDateisValidLocalDatecrossover",
            received.toString());
  }

  @Test
  public void gainOrLoss() {
    out = new StringBuilder();
    in = new StringReader("2 AMZN 2024-06-05 2024-06-06");
    IController controller = new ControllerImpl(out, in);
    controller.init(model);
    assertEquals("populateisValidTickerisValidLocalDateisValidLocalDategainOrLoss",
            received.toString());
  }

  @Test
  public void movingAverage() {
    out = new StringBuilder();
    in = new StringReader("3 AMZN 3 2024-06-05");
    IController controller = new ControllerImpl(out, in);
    controller.init(model);
    assertEquals("populateisValidTickerisValidLocalDatemovingAverage", received.toString());
  }

  @Test
  public void createPortfolio() {
    out = new StringBuilder();
    in = new StringReader("5 y y");
    IController controller = new ControllerImpl(out, in);
    controller.init(model);
    assertEquals("populateisValidTicker", received.toString());
  }

  @Test
  public void addToPortfolio() {
    out = new StringBuilder();
    in = new StringReader("6 A AMZN 10");
    IController controller = new ControllerImpl(out, in);
    controller.init(model);
    assertEquals("populateisValidPortfolioisValidTickeraddToPortfolio", received.toString());
  }

  @Test
  public void getPortfolioValue() {
    out = new StringBuilder();
    in = new StringReader("7 A 2024-06-05");
    IController controller = new ControllerImpl(out, in);
    controller.init(model);
    assertEquals("populateisValidPortfoliogetPortfolioValue", received.toString());
  }
}