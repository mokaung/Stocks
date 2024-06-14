package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import portfolio.IPortfolioV2;
import portfolio.PortfolioV2;
import portfolio.Weight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * The class tests PortfolioV2.
 */
public class PortfolioV2Test {
  IPortfolioV2 portfolio;

  /**
   * setup inherently tests setValueV2.
   *
   * @throws Exception if somehow there's a problem with filling a portfolio.
   */
  @Before
  public void setUp() throws Exception {
    portfolio = new PortfolioV2("test");
    LocalDate date = LocalDate.of(2024, 5, 10);
    IStock stock = new Stock(date, 10, 10, 10, 10, 10, "AMZN");
    IStock stock1 = new Stock(date, 10, 10, 10, 20, 10, "GOOG");
    IStock stock2 = new Stock(date, 10, 10, 10, 10, 10, "NFLX");

    Map<LocalDate, IStock> map1 = new HashMap<>();
    Map<LocalDate, IStock> map2 = new HashMap<>();
    Map<LocalDate, IStock> map3 = new HashMap<>();

    map1.put(date, stock);
    map2.put(date, stock1);
    map3.put(date, stock2);

    Map<LocalDate, Double> shares = new HashMap<>();
    shares.put(LocalDate.of(2024, 5, 10), 10.0);
    Map<LocalDate, Double> sharesG = new HashMap<>();
    sharesG.put(LocalDate.of(2024, 5, 10), 20.0);

    portfolio.setValueV2(map1, shares, "AMZN");
    portfolio.setValueV2(map2, sharesG, "GOOG");
    portfolio.setValueV2(map3, shares, "NFLX");
  }

  @Test
  public void getValue() {
    Double actual = portfolio.getValue(LocalDate.of(2024, 5, 10));
    Double expected = 600.0;
    assertEquals(expected, actual);
  }

  @Test
  public void buyStock() {
    portfolio.buyStock(10, "GOOG",LocalDate.of(2024, 5, 10) );
    String actual = portfolio.portfolioToString("2024-5-10",
            LocalDate.of(2024, 5, 10));
    String expected = "**** Portfolio Name: test ****"
            + System.lineSeparator() + "Stocks: "
            + System.lineSeparator() + "$NFLX"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 10.0"
            + System.lineSeparator() + "- Shares owned: 10.0"
            + System.lineSeparator() + "- Valuation Percentage: 12.5%"
            + System.lineSeparator() + "$GOOG"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 20.0"
            + System.lineSeparator() + "- Shares owned: 30.0"
            + System.lineSeparator() + "- Valuation Percentage: 75.0%"
            + System.lineSeparator() + "$AMZN"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 10.0"
            + System.lineSeparator() + "- Shares owned: 10.0"
            + System.lineSeparator() + "- Valuation Percentage: 12.5%"
            + System.lineSeparator() + "**** Total Portfolio Valuation at 2024-5-10: 800.0 ****"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void rebalance() {
    Weight weightAMZN = new Weight(0.4, "AMZN");
    Weight weightGOOG = new Weight(0.5, "GOOG");
    Weight weightNFLX = new Weight(0.1, "NFLX");
    List<Weight> listF = new ArrayList<Weight>();
    listF.add(weightAMZN);
    listF.add(weightGOOG);
    listF.add(weightNFLX);
    portfolio.rebalance(LocalDate.of(2024, 5, 10), listF);
    String actual = portfolio.portfolioToString("2024-5-10",
            LocalDate.of(2024, 5, 10));
    String expected = "**** Portfolio Name: test ****"
            + System.lineSeparator() + "Stocks: "
            + System.lineSeparator() + "$NFLX"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 10.0"
            + System.lineSeparator() + "- Shares owned: 6.0"
            + System.lineSeparator() + "- Valuation Percentage: 14.285714285714285%"
            + System.lineSeparator() + "$GOOG"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 20.0"
            + System.lineSeparator() + "- Shares owned: 15.0"
            + System.lineSeparator() + "- Valuation Percentage: 71.42857142857143%"
            + System.lineSeparator() + "$AMZN"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 10.0"
            + System.lineSeparator() + "- Shares owned: 6.0"
            + System.lineSeparator() + "- Valuation Percentage: 14.285714285714285%"
            + System.lineSeparator() + "**** Total Portfolio Valuation at 2024-5-10: 420.0 ****"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void portfolioToString() {
    String actual = portfolio.portfolioToString("2024-5-10",
            LocalDate.of(2024, 5, 10));
    String expected = "**** Portfolio Name: test ****"
            + System.lineSeparator() + "Stocks: "
            + System.lineSeparator() + "$NFLX"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 10.0"
            + System.lineSeparator() + "- Shares owned: 10.0"
            + System.lineSeparator() + "- Valuation Percentage: 16.666666666666664%"
            + System.lineSeparator() + "$GOOG"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 20.0"
            + System.lineSeparator() + "- Shares owned: 20.0"
            + System.lineSeparator() + "- Valuation Percentage: 66.66666666666666%"
            + System.lineSeparator() + "$AMZN"
            + System.lineSeparator() + "- Stock's value at 2024-5-10: 10.0"
            + System.lineSeparator() + "- Shares owned: 10.0"
            + System.lineSeparator() + "- Valuation Percentage: 16.666666666666664%"
            + System.lineSeparator() + "**** Total Portfolio Valuation at 2024-5-10: 600.0 ****"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }

  @Test
  public void toXml() {
    String actual = portfolio.toXml();
    String expected = "<portfolio><tickers><ticker>NFLX</ticker>"
            + "<ticker>GOOG</ticker><ticker>AMZN</ticker>"
            + "</tickers><shares><share ticker=\"NFLX\">"
            + "<date value=\"2024-05-10\">10.0</date></share><share ticker="
            + "\"GOOG\"><date value=\"2024-05-10\">20.0</date></share><share"
            + " ticker=\"AMZN\"><date value=\"2024-05-10\">10.0</date></share"
            + "></shares></portfolio>";
    assertEquals(expected, actual);
  }
}
