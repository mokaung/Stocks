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
    String actual = pp.getPerformance(LocalDate.of(2024, 5, 10),
            LocalDate.of(2024, 5, 15));
    String expected = "Performance of portfolio test from 2024-05-10 to 2024-05-15"
            + System.lineSeparator() + System.lineSeparator() + "MAY 2024: "
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void NoDate() {
    String actual = pp.getPerformance(LocalDate.of(2023, 1, 1),
            LocalDate.of(2023, 1, 10));
    String expected = "Performance of portfolio test from 2023-01-01 to 2023-01-10"
            + System.lineSeparator() + System.lineSeparator() + "JAN 2023: "
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void NullValues() {
    test.setValueV2(null, null, "A");
    String actual = pp.getPerformance(LocalDate.of(2024, 5, 10),
            LocalDate.of(2024, 5, 15));
    String expected = "Performance of portfolio test from 2024-05-10 to 2024-05-15"
            + System.lineSeparator() + System.lineSeparator() + "MAY 2024: "
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void NoTicker() {
    String actual = pp.getPerformance(LocalDate.of(2024, 5, 10),
            LocalDate.of(2024, 5, 15));
    String expected = "Performance of portfolio test from 2024-05-10 to 2024-05-15"
            + System.lineSeparator() + System.lineSeparator() + "MAY 2024: "
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void MultiStocks() {
    IStock s1 = new Stock(LocalDate.of(2023, 1, 1),
            10.0, 20.0, 5.0, 15.0, 100, "A");
    IStock s2 = new Stock(LocalDate.of(2023, 1, 1),
            10.0, 20.0, 5.0, 15.0, 100, "B");

    Map<LocalDate, IStock> stockMap = new HashMap<>();
    stockMap.put(LocalDate.of(2023, 1, 1), s1);
    stockMap.put(LocalDate.of(2023, 1, 1), s2);

    Map<LocalDate, Double> shareMap = new HashMap<>();
    shareMap.put(LocalDate.of(2023, 1, 1), 100.0);

    test.setValueV2(stockMap, shareMap, "A");
    test.setValueV2(stockMap, shareMap, "B");

    String actual = pp.getPerformance(LocalDate.of(2023, 1, 1),
            LocalDate.of(2023, 1, 1));
    String expected = "Performance of portfolio test from 2023-01-01 to 2023-01-01"
            + System.lineSeparator() + System.lineSeparator() + "JAN 2023: ***"
            + System.lineSeparator() + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void testPerformanceAcrossMultipleMonths() {
    IStock s1 = new Stock(LocalDate.of(2023, 1, 1),
            10.0, 20.0, 5.0, 15.0, 100, "A");
    Map<LocalDate, IStock> stock = new HashMap<>();
    Map<LocalDate, Double> share = new HashMap<>();

    // Adding stock data for March
    stock.put(LocalDate.of(2024, 3, 31), s1);
    share.put(LocalDate.of(2024, 3, 31), 100.0);

    // Adding stock data for April
    stock.put(LocalDate.of(2024, 4, 1), s1);
    share.put(LocalDate.of(2024, 4, 1), 100.0);

    stock.put(LocalDate.of(2024, 4, 30), s1);
    share.put(LocalDate.of(2024, 4, 30), 100.0);

    // Adding stock data for May
    stock.put(LocalDate.of(2024, 5, 1), s1);
    share.put(LocalDate.of(2024, 5, 1), 100.0);

    test.setValueV2(stock, share, "A");

    String actual = pp.getPerformance(LocalDate.of(2024, 3, 31),
            LocalDate.of(2024, 5, 1));
    String expected = "Performance of portfolio test from 2024-03-31 to 2024-05-01"
            + System.lineSeparator() + System.lineSeparator()
            + "MAR 2024: " + System.lineSeparator()
            + "APR 2024: " + System.lineSeparator()
            + "MAY 2024: " + System.lineSeparator()
            + System.lineSeparator() + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }
}