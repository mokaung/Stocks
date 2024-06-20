package controller;

import java.time.LocalDate;

public interface IGuiController {
  public void onDateSelected(LocalDate date);
  public void onPortfolioSelected(String portfolio);
  public void onGetPortfolioValue();
  public void onLoadPortfolios();
  void updateView(String message);
  void updatePortfolioValue(double value);
}
