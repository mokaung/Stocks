package model.performance;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import model.IStock;
import model.Stock;

import static org.junit.Assert.assertEquals;


/**
 * Tester class for stock performance.
 */
public class StockPerformanceTest {
  StockPerformance stockPerformance;
  Map<LocalDate, IStock> stocks;

  @Before
  public void setUp() {
    stocks = new HashMap<>();
    LocalDate today = LocalDate.of(2024, 6, 19);
    IStock stock1 = new Stock(today, 10.0, 10.0, 10.0,
            1000.0, 10, "A");

    stocks.put(LocalDate.of(2023, 6, 1), stock1);
    stocks.put(LocalDate.of(2023, 6, 3), stock1);

    stockPerformance = new StockPerformance(stocks);
  }

  @Test
  public void testGetPerformance() {
    String expected = "Performance of stock A from 2023-06-01 to 2023-06-01"
            + System.lineSeparator() + System.lineSeparator() + "JUN 2023: *"
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, stockPerformance.getPerformance(
            LocalDate.of(2023, 6, 1),
            LocalDate.of(2023, 6, 1)));
  }

  @Test
  public void testGetPerformanceMulti() {
    String expected = "Performance of stock A from 2023-06-01 to 2023-06-03"
            + System.lineSeparator() + System.lineSeparator() + "JUN 2023: **"
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, stockPerformance.getPerformance(
            LocalDate.of(2023, 6, 1),
            LocalDate.of(2023, 6, 3)));
  }

  @Test
  public void testNoStock() {
    IStock s1 = new Stock(LocalDate.of(2023, 6, 1),
            5.0, 10.0, 10.0, 1000.0, 10, "A");
    stocks.put(LocalDate.of(2023, 6, 1), s1);
    stocks.put(LocalDate.of(2023, 6, 2), s1);
    stocks.put(LocalDate.of(2023, 6, 3), s1);
    stocks.put(LocalDate.of(2023, 6, 4), null);

    String expected = "Performance of stock A from 2023-06-01 to 2023-06-04"
            + System.lineSeparator() + System.lineSeparator() + "JUN 2023: ***"
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, stockPerformance.getPerformance(
            LocalDate.of(2023, 6, 1),
            LocalDate.of(2023, 6, 4)));
  }
}