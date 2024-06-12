package Portfolio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that records all transactions made.
 */
public class Transaction implements ITransaction {
  private final Map<String, LocalDate> data;

  public Transaction() {
    data = new HashMap<>();
  }

  public void record(String ticker, LocalDate date) {
    data.put(ticker, date);
  }

  public boolean check(String ticker, LocalDate date) {
    return data.containsKey(ticker) && data.get(ticker).equals(date);
  }
}
