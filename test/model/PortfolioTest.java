package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PortfolioTest {
  IPortfolio portfolio;

  @Before
  public void setUp() throws Exception {
    portfolio = new Portfolio();
  }

  @Test
  public void getValue() {
    LocalDate date = LocalDate.of(2024, 1, 1);
    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");
    IStock stock1 = new Stock(date, 10, 10, 10, 10, 10, "GOOG");

    Map<LocalDate, IStock> map = new HashMap<>();
    map.put(date, stock);
    map.put(date, stock1);
    portfolio.setValue(map, 10, "AMZN");
    portfolio.setValue(map, 10, "GOOG");

    assertEquals(200, portfolio.getValue(date), 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getValueException() {
    LocalDate date = LocalDate.of(2024, 1, 1);
    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");

    Map<LocalDate, IStock> map = new HashMap<>();
    map.put(date, stock);


    portfolio.setValue(map, 10, "AMZN");
    LocalDate d = LocalDate.of(2024, 6, 10);
    portfolio.getValue(d);
  }

  @Test
  public void setValue() {
    LocalDate date = LocalDate.of(2024, 1, 1);
    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");

    Map<LocalDate, IStock> map = new HashMap<>();
    map.put(date, stock);


    portfolio.setValue(map, 10, "AMZN");
    assertEquals(100, portfolio.getValue(date), 0.0);
  }


  @Test
  public void getStocks() {
    LocalDate date = LocalDate.of(2024, 1, 1);
    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");
    IStock stock1 = new Stock(date, 10, 10, 10, 10, 10, "GOOG");
    IStock stock2 = new Stock(date, 10, 10, 10, 10, 10, "NFLX");

    Map<LocalDate, IStock> map1 = new HashMap<>();
    Map<LocalDate, IStock> map2 = new HashMap<>();
    Map<LocalDate, IStock> map3 = new HashMap<>();

    map1.put(date, stock);
    map2.put(date, stock1);
    map3.put(date, stock2);

    Map<String, Map<LocalDate, IStock>> stocks = new HashMap<>();
    stocks.put("AMZN", map1);
    stocks.put("GOOG", map2);
    stocks.put("NFLX", map3);

    portfolio.setValue(map1, 10, "AMZN");
    portfolio.setValue(map2, 10, "GOOG");
    portfolio.setValue(map3, 10, "NFLX");

    assertEquals(stocks, portfolio.getStocks());
  }

  @Test
  public void toJson() {
    IPortfolioV2 portfolioV2;
    portfolioV2 = new PortfolioV2(new ModelImpl(), "testXml");
    LocalDate date = LocalDate.of(2024, 1, 1);
    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");
    IStock stock1 = new Stock(date, 10, 10, 10, 10, 10, "GOOG");
    IStock stock2 = new Stock(date, 10, 10, 10, 10, 10, "NFLX");

    Map<LocalDate, IStock> map1 = new HashMap<>();
    Map<LocalDate, IStock> map2 = new HashMap<>();
    Map<LocalDate, IStock> map3 = new HashMap<>();

    map1.put(date, stock);
    map2.put(date, stock1);
    map3.put(date, stock2);

    Map<LocalDate, Double> share1 = new HashMap<>();
    Map<LocalDate, Double> share2 = new HashMap<>();
    Map<LocalDate, Double> share3 = new HashMap<>();

    share1.put(date, 10.0);
    share2.put(date, 10.0);
    share3.put(date, 10.0);

    portfolioV2.setValue(map1, share1, "AMZN");
    portfolioV2.setValue(map2, share2, "GOOG");
    portfolioV2.setValue(map3, share3, "NFLX");

    System.out.println(portfolioV2.toXml());
    try {
      portfolioV2.saveXml("testXml");
    }
    catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}