package view;

import java.time.LocalDate;

import Portfolio.IPortfolioV2;

/**
 * Interface for the views of the program.
 */
public interface IViews {
  String getPerformance(IPortfolioV2 portfolio, LocalDate start, LocalDate end);
}
