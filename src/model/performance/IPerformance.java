package model.performance;

import java.time.LocalDate;

/**
 * Interface for generating performances.
 */
public interface IPerformance {
  /**
   * Generate the given performance.
   *
   * @param start start date.
   * @param end   end date.
   * @return graph of performance.
   */
  String getPerformance(LocalDate start, LocalDate end);
}
