package model;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import portfolio.Portfolio;
import portfolio.IPortfolio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    reader = new StringReader("timestamp,open,high,low,close,volume\n"
            + "2024-06-06,181.7450,185.0000,181.4900,185.0000,31371151\n"
            + "2024-06-05,180.1000,181.5000,178.7500,181.2800,32116394\n "
            + "2024-06-04,177.6400,179.8200,176.4400,179.3400,27198388\n"
            + "2024-06-03,177.7000,178.7000,175.9200,178.3400,30786640\n"
            + "2024-05-31,178.3000,179.2100,173.8700,176.4400,58903939\n");
    model.populate(reader, "AMZN");

    reader2 = new StringReader("timestamp,open,high,low,close,volume\n"
            + "2024-06-06,181.7450,185.0000,181.4900,185.0000,31371151\n"
            + "2024-06-05,180.1000,181.5000,178.7500,181.2800,32116394\n "
            + "2024-06-04,177.6400,179.8200,176.4400,179.3400,27198388\n"
            + "2024-06-03,177.7000,178.7000,175.9200,178.3400,30786640\n"
            + "2024-05-31,178.3000,179.2100,173.8700,176.4400,58903939\n");
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
    assertTrue(model.isInvalidLocalDate(d1, "AMZN"));
    LocalDate d2 = LocalDate.of(2023, 6, 6);
    assertFalse(model.isInvalidLocalDate(d2, "GOOG"));
  }

  @Test
  public void isValidPortfolio() {
    assertTrue(model.isInvalidPortfolio("GOOG"));
    assertFalse(model.isInvalidPortfolio("NFLX"));
  }

  @Test
  public void isValidTicker() {
    assertTrue(model.isInvalidTicker("AMZN"));
    assertTrue(model.isInvalidTicker("GOOG"));
    assertFalse(model.isInvalidTicker("NFLX"));
  }

  @Test
  public void createPortfolio() {
    Portfolio p = new Portfolio();

    LocalDate date = LocalDate.of(2024, 1, 1);
    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");
    Map<LocalDate, IStock> info = new HashMap<>();
    info.put(date, stock);
    p.setValue(info, 10, "AMZN");
    model.createPortfolio("AMZN", 10, "a");

    assertEquals(p.getStocks().containsKey("AMZN"), model.getStock().containsKey("AMZN"));
    assertEquals(p.getStocks().containsValue(stock), model.getStock().containsValue(stock));
  }

  @Test
  public void addToPortfolio() {
    Portfolio p = new Portfolio();

    LocalDate date = LocalDate.of(2024, 1, 1);

    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");

    Map<LocalDate, IStock> map = new HashMap<>();
    map.put(date, stock);

    p.setValue(map, 10, "AMZN");
    assertEquals(100, p.getValue(date), 0.0);
  }

  @Test
  public void getPortfolioValue() {
    IPortfolio portfolio = model.createPortfolio("AMZN", 10, "a");

    LocalDate date = LocalDate.of(2024, 1, 1);
    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");
    IStock stock1 = new Stock(date, 10, 10, 10, 10, 10, "GOOG");

    Map<LocalDate, IStock> map = new HashMap<>();
    map.put(date, stock);
    map.put(date, stock1);
    portfolio.setValue(map, 10, "AMZN");
    portfolio.setValue(map, 10, "GOOG");

    assertEquals(200, model.getPortfolioValue("a", date), 0.0);
  }
}