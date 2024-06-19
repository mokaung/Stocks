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
  public void oneDay() {
    LocalDate firstDay = LocalDate.of(2023, 6, 1);
    String expected = "Performance of stock A from 2023-06-01 to 2023-06-01"
            + System.lineSeparator() + System.lineSeparator() + "JUN 2023: *"
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, stockPerformance.getPerformance(firstDay, firstDay));
  }

  @Test
  public void BetweenYears() {
    LocalDate endOfYear = LocalDate.of(2023, 12, 31);
    LocalDate startOfNextYear = LocalDate.of(2024, 1, 1);
    IStock s1 = new Stock(endOfYear, 10.0, 10.0, 10.0,
            1000.0, 10, "A");
    stocks.put(endOfYear, s1);
    stocks.put(startOfNextYear, s1);
    String expected = "Performance of stock A from 2023-12-31 to 2024-01-01"
            + System.lineSeparator() + System.lineSeparator() + "DEC 2023: **"
            + System.lineSeparator() + "JAN 2024: *"
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, stockPerformance.getPerformance(endOfYear, startOfNextYear));
  }
}