package model;

import java.util.Calendar;

public interface IPortfolio {

  double getValue(Calendar date);

  void setValue(IStock stock, int share);

}
