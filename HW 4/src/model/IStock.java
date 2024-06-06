package model;

import java.util.Calendar;

public interface IStock {
  Calendar getDate();

  double getClose();

  String getTicker();
}
