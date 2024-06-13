package Model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import Portfolio.IPortfolioV2;
import Portfolio.Weight;

public interface IModel2 extends IModel {
  //TODO make list
  void rebalance(LocalDate date, ArrayList<Weight> weightArrayList, String name);

  String getPerformance(String name, LocalDate start, LocalDate end);

  void savePortfolio(String portFolioName) throws IOException;

  IPortfolioV2 createPortfolioV2(String ticker, double share, String name, LocalDate date);

  void addToPortfolioV2(String portFolioName, String ticker, double share, LocalDate date);

  Double getPortfolioValueV2(String name, LocalDate cal) throws IllegalArgumentException;
}
