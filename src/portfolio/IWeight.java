package portfolio;

/**
 * Interface for the weight class.
 */
public interface IWeight {
  /**
   * getter for the ticker.
   *
   * @return ticker.
   */
  String getTicker();

  /**
   * Getter for the percent of how much a stock is worth.
   *
   * @return percent.
   */
  double getPercent();
}
