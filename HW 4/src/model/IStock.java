package model;

import java.util.Calendar;

public interface IStock {
  Calendar getDate();

  double getOpen();

  double getHigh();

  double getLow();

  double getClose();

  int getVolume();

  String getTicker();

}
