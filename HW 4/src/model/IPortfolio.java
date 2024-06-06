package model;

import java.util.Calendar;
import java.util.Map;

public interface IPortfolio {

  double getValue(Calendar date);

  void setValue(Map<Calendar, IStock> stock, int share, String ticker);

}
