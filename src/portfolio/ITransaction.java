package portfolio;

import java.time.LocalDate;

/**
 * Interface for recording transactions.
 */
public interface ITransaction {
  /**
   * Records the transaction.
   *
   * @param ticker the stock being exchanged.
   * @param date   the date of exchange.
   */
  void record(String ticker, LocalDate date);

  /**
   * Checks if a transaction can occur for a stock on a given date.
   *
   * @param ticker the stock wanting to exchange.
   * @param date   the date of exchange.
   * @return
   */
  boolean check(String ticker, LocalDate date);
}
