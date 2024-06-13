package Model.Performance;

import java.time.LocalDate;
import java.util.Map;

import Model.IStock;

/**
 * Display the performance of a stock over a period of time.
 */
public class StockPerformance extends APerformance implements IPerformance {
  private final Map<LocalDate, IStock> stocks;

  public StockPerformance(Map<LocalDate, IStock> stocks) {
    this.stocks = stocks;
  }

  @Override
  public String getPerformance(LocalDate start, LocalDate end) {
    StringBuilder out = new StringBuilder();
    out.append("Performance of portfolio ")
            .append(stocks.get(start).getTicker())
            .append(" from ")
            .append(start)
            .append(" to ")
            .append(end)
            .append(System.lineSeparator())
            .append(System.lineSeparator());

    double total = 0.0;
    String currentMonth = start.getMonth().toString();
    for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
      double value = stocks.get(date).getClose();
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
