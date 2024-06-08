package model;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tester class for Model.
 */
public class ModelImplTest {
  IModel model;
  Reader reader;
  Reader reader2;

  @Before
  public void setUp() {
    model = new ModelImpl();
    reader = new StringReader("timestamp,open,high,low,close,volume\n" +
            "2024-06-06,181.7450,185.0000,181.4900,185.0000,31371151\n" +
            "2024-06-05,180.1000,181.5000,178.7500,181.2800,32116394\n + " +
            "F2024-06-04,177.6400,179.8200,176.4400,179.3400,27198388\n" +
            "2024-06-03,177.7000,178.7000,175.9200,178.3400,30786640\n" +
            "2024-05-31,178.3000,179.2100,173.8700,176.4400,58903939\n");
    model.populate(reader, "AMZN");

    reader2 = new StringReader("timestamp,open,high,low,close,volume\n" +
            "2024-06-06,177.4300,178.7100,177.2100,178.3500,14255818\n" +
            "2024-06-05,176.5350,177.9700,175.2900,177.0700,15233861\n" +
            "2024-06-04,174.4500,175.1900,173.2200,175.1300,14066602\n" +
            "2024-06-03,173.8800,175.8600,172.4500,174.4200,20742798\n" +
            "2024-05-31,173.4000,174.4200,170.9700,173.9600,28085151");
    model.populate(reader2, "GOOG");
  }

  @Test
  public void populate() {
    Map<LocalDate, IStock> inner = new HashMap<>();
    Map<String, Map<LocalDate, IStock>> stocks = new HashMap<>();
    LocalDate d1 = LocalDate.of(2024, 6, 6);
    LocalDate d2 = LocalDate.of(2024, 6, 5);
    IStock stock1 = new Stock(d1, 181.7450, 185.0000,
            181.4900, 185.0000, 31371151, "AMZN");
    IStock stock2 = new Stock(d2, 180.1000, 181.5000,
            178.7500, 181.2800, 32116394, "AMZN");
    inner.put(d1, stock1);
    inner.put(d2, stock2);
    stocks.put("AMZN", inner);
    assertEquals(stocks, model.getStock());
  }

  @Test
  public void isValidLocalDate() {
    LocalDate d1 = LocalDate.of(2024, 6, 6);
    assertTrue(model.isValidLocalDate(d1, "AMZN"));
    LocalDate d2 = LocalDate.of(2023, 6, 6);
    assertFalse(model.isValidLocalDate(d2, "GOOG"));
  }

  @Test
  public void isValidPortfolio() {
    assertTrue(model.isValidPortfolio("GOOG"));
    assertFalse(model.isValidPortfolio("NFLX"));
  }

  @Test
  public void isValidTicker() {
    assertTrue(model.isValidTicker("AMZN"));
    assertTrue(model.isValidTicker("GOOG"));
    assertFalse(model.isValidTicker("NFLX"));
  }
}