package Model.Performance;

import java.time.LocalDate;

import Portfolio.IPortfolioV2;

public class PortfolioPerformance extends APerformance implements IPerformance {
  private final IPortfolioV2 portfolio;

  public PortfolioPerformance(IPortfolioV2 portfolio) {
    this.portfolio = portfolio;
  }

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
    for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
      double value = portfolio.getValue(date);
      total += value;
      if (!currentMonth.equals(date.getMonth().toString())) {
        appendMonthSummary(out, currentMonth, date.minusDays(1), total);
        currentMonth = date.getMonth().toString();
        total = value;
      }
    }
    appendMonthSummary(out, currentMonth, end, total);

    out.append(System.lineSeparator());
    out.append("Scale: * = 1000");
    out.append(System.lineSeparator());

    return out.toString();
  }
}
