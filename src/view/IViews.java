package view;

import java.time.LocalDate;

import model.IPortfolio;
import model.IPortfolioV2;

/**
 * Interface for the views of the program.
 */
public interface IViews {
  String getPerformance(IPortfolioV2 portfolio, LocalDate start, LocalDate end);
}
