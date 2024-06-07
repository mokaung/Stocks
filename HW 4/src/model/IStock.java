package model;

import java.time.LocalDate;

public interface IStock {
  LocalDate getDate();

  double getClose();

  String getTicker();
}
