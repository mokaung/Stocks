package controller;

import java.time.LocalDate;

/**
 * Interface for a controller that uses GUI.
 */
public interface IGuiController {
  void onDateSelected(LocalDate date);

  void onPortfolioSelected(String portfolio);

  void onGetPortfolioValue();

  void onLoadPortfolios();

  void updateView(String message);

  void updatePortfolioValue(double value);
}
