package model.performance;

import java.time.LocalDate;

/**
 * Abstract class for performances.
 */
public abstract class APerformance implements IPerformance {
  public abstract String getPerformance(LocalDate start, LocalDate end);

  protected void appendMonthSummary(StringBuilder out, String month,
                                    LocalDate date, double total) {
    String nameMonth = month.substring(0, 3);
    String scale = create(total);
    out.append(nameMonth + " ");
    out.append(date.getYear() + ": ");
    out.append(scale);
    out.append(System.lineSeparator());
  }

  // changes the value into asterisks rounded to the nearest hundred
  // each * is worth 1000
  private String create(double value) {
    StringBuilder b = new StringBuilder();
    while (value >= 1000.0) {
      b.append("*");
      value -= 1000.0;
    }
    if (value >= 500.0) {
      b.append("*");
    }
    return b.toString();
  }
}
