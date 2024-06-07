package model;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ModelImplTest {
  IModel model;
  Reader reader;

  @Before
  public void setUp() {
    model = new ModelImpl();
    reader = new StringReader("timestamp,open,high,low,close,volume\n" +
            "2024-06-06,181.7450,185.0000,181.4900,185.0000,31371151\n" +
            "2024-06-05,180.1000,181.5000,178.7500,181.2800,32116394\n");
    model.populate(reader, "AMZN");
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

  }

  @Test
  public void isValidPortfolio() {
  }

  @Test
  public void isValidTicker() {
  }


}