package model.performance;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import model.IStock;
import model.Stock;
import portfolio.IPortfolioV2;
import portfolio.PortfolioV2;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for portfolio performance.
 */
public class PortfolioPerformanceTest {
  private IPortfolioV2 test;
  private PortfolioPerformance pp;

  @Before
  public void setUp() {
    test = new PortfolioV2("test");
    pp = new PortfolioPerformance(test);
  }

  @Test
  public void testEmptyPortfolio() {
    String actual = pp.getPerformance(LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 15));
    String expected = "Performance of portfolio new from 2024-05-10 to 2024-05-15"
            + System.lineSeparator() + System.lineSeparator() + "MAY 2024: "
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void testDateRangeWithNoData() {
    String actual = pp.getPerformance(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 10));
    String expected = "Performance of portfolio new from 2023-01-01 to 2023-01-10"
            + System.lineSeparator() + System.lineSeparator() + "JAN 2023: "
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void testPerformanceWithNullValues() {
    test.setValueV2(null, null, "A");
    String actual = pp.getPerformance(LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 15));
    String expected = "Performance of portfolio new from 2024-05-10 to 2024-05-15"
            + System.lineSeparator() + System.lineSeparator() + "MAY 2024: "
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void testNonexistentTickerPerformance() {
    String actual = pp.getPerformance(LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 15));
    String expected = "Performance of portfolio new from 2024-05-10 to 2024-05-15"
            + System.lineSeparator() + System.lineSeparator() + "MAY 2024: "
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void testBoundaryDates() {
    LocalDate date = LocalDate.of(2023, 1, 5);
    IStock s1 = new Stock(date, 10.0, 20.0, 5.0, 15.0, 100, "A");
    Map<LocalDate, IStock> stockMap = new HashMap<>();
    stockMap.put(LocalDate.of(2023, 1, 1), s1);
    Map<LocalDate, Double> shareMap = new HashMap<>();
    shareMap.put(LocalDate.of(2023, 1, 1), 100.0);
    test.setValueV2(stockMap, shareMap, "A");

    String actual = pp.getPerformance(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1));
    String expected = "Performance of portfolio new from 2023-01-01 to 2023-01-01"
            + System.lineSeparator() + System.lineSeparator() + "JAN 2023: *"
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }
}