package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import Portfolio.IPortfolio;
import Portfolio.IPortfolioV2;

public interface IModel2 extends IModel {
  void savePortfolio(String portFolioName) throws IOException;

  IPortfolioV2 createPortfolioV2(String ticker, int share, String name, LocalDate date);

  void addToPortfolioV2(String portFolioName, String ticker, int share, LocalDate date);

  Double getPortfolioValueV2(String name, LocalDate cal) throws IllegalArgumentException;
}
