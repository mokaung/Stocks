package model;

import java.time.LocalDate;
import java.util.Map;

public interface IPortfolio {

  double getValue(LocalDate date);

  void setValue(Map<LocalDate, IStock> stock, int share, String ticker);

  Map<String, Map<LocalDate, IStock>> getStocks();

}
