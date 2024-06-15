package model.performance;

import java.time.LocalDate;

import portfolio.IPortfolioV2;

/**
 * Generates the performance of a portfolio.
 */
public class PortfolioPerformance extends APerformance implements IPerformance {
  private final IPortfolioV2 portfolio;

  public PortfolioPerformance(IPortfolioV2 portfolio) {
    this.portfolio = portfolio;
  }

  @Override
  public String getPerformance(LocalDate start, LocalDate end) {
    StringBuilder out = new StringBuilder();
    out.append("Performance of portfolio ")
            .append(portfolio.getName())
            .append(" from ")
            .append(start)
            .append(" to ")
            .append(end)
            .append(System.lineSeparator())
            .append(System.lineSeparator());

    double total = 0.0;
    String currentMonth = start.getMonth().toString();
    double value = 0;

    for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
      if (currentMonth.equals(date.getMonth().toString())) {
        value = portfolio.getValue(date);
        total += value;
      } else {
        appendMonthSummary(out, currentMonth, date, total);
        total = 0.0;
        currentMonth = date.getMonth().toString();
      }
      if (date.equals(end)) {
        appendMonthSummary(out, date.getMonth().toString(), date, total);
      }
    }

    out.append(System.lineSeparator());
    out.append("Scale: * = 1000");
    out.append(System.lineSeparator());

    return out.toString();
  }
}
