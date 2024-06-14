package model.performance;

import org.junit.Test;

import java.time.LocalDate;

import portfolio.IPortfolioV2;
import portfolio.PortfolioV2;

import static org.junit.Assert.*;

public class PortfolioPerformanceTest {

  @Test
  public void getPerformance() {
    IPortfolioV2 test = new PortfolioV2("new");
    PortfolioPerformance yup = new PortfolioPerformance(test);
    String actual = yup.getPerformance(LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 15));
    String expected = "Performance of portfolio new from 2024-05-10 to 2024-05-15"
            + System.lineSeparator()
            + System.lineSeparator()
            + "MAY 2024: "
            + System.lineSeparator()
            + System.lineSeparator()
            + "Scale: * = 1000"
            + System.lineSeparator();
    assertEquals(expected, actual);
  }


}