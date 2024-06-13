package Model.Performance;

import java.time.LocalDate;


public interface IPerformance {
  String getPerformance(LocalDate start, LocalDate end);

}
