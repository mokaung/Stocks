package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tester class for stock.
 */
public class StockTest {
  IStock stock;

  @Before
  public void setUp() throws Exception {
    LocalDate date = LocalDate.of(2024, 1, 1);
    stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");
  }

  @Test
  public void getDate() {
    LocalDate date = LocalDate.of(2024, 1, 1);
    assertEquals(stock.getDate(), date);
  }

  @Test
  public void getClose() {
    assertEquals(10, stock.getClose(), 0.0);
    assertNotEquals(0.0, stock.getClose(), 0.0);
  }

  @Test
  public void getTicker() {
    assertEquals(stock.getTicker(), "AMZN");
    assertNotEquals(stock.getTicker(), "BBBB");
  }

  @Test
  public void testEquals() {
    LocalDate date = LocalDate.of(2024, 1, 1);
    IStock stock1 = new Stock(date, 10, 10,
            10, 10, 10, "AMZN");
    assertEquals(stock1, stock);
  }
}