package portfolio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IStock;

/**
 * Modified design of Portfolio that allows for additional commands.
 */
public class PortfolioV2 implements IPortfolioV2 {
  private final String name;
  private final Map<String, Map<LocalDate, IStock>> stocks;
  // from string to int to:
  private final Map<String, Map<LocalDate, Double>> share;
  private final ITransaction transaction;

  /**
   * Constructor for PortfolioV2.
   *
   * @param name name of the portfolio.
   */
  public PortfolioV2(String name) {
    this.name = name;
    stocks = new HashMap<>();
    share = new HashMap<>();
    this.transaction = new Transaction();
  }

  @Override
  public String portfolioToString(String dateString, LocalDate date) {
    StringBuilder sb = new StringBuilder();
    sb.append("**** Portfolio Name: " + name + " ****"
            + System.lineSeparator()
            + "Stocks: "
            + System.lineSeparator());
    for (Map.Entry<String, Map<LocalDate, IStock>> entry : stocks.entrySet()) {
      if (!share.get(entry.getKey()).containsKey(date)) {
        throw new IllegalArgumentException("Sorry, your portfolio does not include shares of "
                + entry.getKey() + " at " + dateString + ".");
      }
      double percentage = ((entry.getValue().get(date).getClose()
              * share.get(entry.getKey()).get(date))
              / getValue(date)) * 100;
      sb.append("$" + entry.getKey()
              + System.lineSeparator()
              + "- Stock's value at " + dateString + ": " + entry.getValue().get(date).getClose()
              + System.lineSeparator()
              + "- Shares owned: " + share.get(entry.getKey()).get(date)
              + System.lineSeparator()
              + "- Valuation Percentage: " + percentage + "%"
              + System.lineSeparator());
    }
    sb.append("**** Total Portfolio Valuation at "
            + dateString + ": " + getValue(date) + " ****"
            + System.lineSeparator());
    return sb.toString();
  }

  @Override
  public double getValue(LocalDate date) {
    double answer = 0;
    for (Map<LocalDate, IStock> entry : stocks.values()) {
      IStock stock = entry.get(date);
      if (stock == null) {
        return 0.0;
      }
      if (stock.getDate().equals(date)) {
        double stockShare;
        try {
          stockShare = share.get(stock.getTicker()).get(date);
        } catch (NullPointerException e) {
          stockShare = 0.0;
        }
        answer += stock.getClose() * stockShare;
      }
    }
    return answer;
  }

  @Override
  public void setValue(Map<LocalDate, IStock> stock, int share, String ticker) {
    stocks.put(ticker, stock);
  }

  @Override
  public void rebalance(LocalDate date, List<Weight> weights) {
    double totalVal = getValue(date);

    for (Weight weight : weights) {
      String ticker = weight.getTicker();
      double targetPercent = weight.getPercent();

      double stockShare = share.get(ticker).get(date);
      double stockVal = stocks.get(ticker).get(date).getClose();
      double currentVal = stockShare * stockVal;

      double targetVal = totalVal * targetPercent;
      double newShare = targetVal / stockVal;

      // checks to see if the difference within the 1%
      if (Math.abs(targetPercent - (currentVal / totalVal)) > 0.01) {
        share.get(ticker).put(date, newShare);
      }
    }
  }

  @Override
  public void buyStock(double share, String ticker, LocalDate date) {
    if (!transaction.check(ticker, date)) {
      double owned = this.share.get(ticker).get(date);
        this.share.get(ticker).put(date, owned + share);
      transaction.record(ticker, date);
    }
  }

  @Override
  public void buyStockGUI(double share,
                       String ticker,
                       LocalDate date,
                       Map<LocalDate, IStock> stockMap,
                       Map<LocalDate, Double> shareMap) {
    if (!transaction.check(ticker, date)) {
      if (!this.share.containsKey(ticker)){
        setValueV2(stockMap, shareMap, ticker);
      }
      else if (!this.share.get(ticker).containsKey(date)){
        LocalDate mostRecentDate = Collections.max(this.share.get(ticker).keySet());
        double owned = this.share.get(ticker).get(mostRecentDate);
        this.share.get(ticker).put(date, owned+share);
      } else {
        double owned = this.share.get(ticker).get(date);
        this.share.get(ticker).put(date, owned + share);
      }
      transaction.record(ticker, date);
    }
  }

  @Override
  public void sellStock(double share, String ticker, LocalDate date) {
    if (!transaction.check(ticker, date)) {
      if (this.share.get(ticker).get(date) - share <= 0) {
        this.share.remove(ticker);
        stocks.remove(ticker);
      }
//      else if (!this.share.get(ticker).containsKey(date)) {
//        this.share.get(ticker)
//      }
      else {
        double owned = this.share.get(ticker).get(date);
        this.share.get(ticker).put(date, owned-share);
      }
      transaction.record(ticker, date);
    }
  }

  @Override
  public void setValueV2(Map<LocalDate, IStock> stock, Map<LocalDate, Double> share, String
          ticker) {
    stocks.put(ticker, stock);
    this.share.put(ticker, share);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Map<String, Map<LocalDate, IStock>> getStocks() {
    return stocks;
  }

  @Override
  public String toXml() {
    StringBuilder xmlBuilder = new StringBuilder();
    xmlBuilder.append("<portfolio>");
    fillTickers(xmlBuilder);
    fillShares(xmlBuilder);
    xmlBuilder.append("</portfolio>");
    return xmlBuilder.toString();
  }

  private void fillTickers(StringBuilder xmlBuilder) {
    xmlBuilder.append("<tickers>");
    for (String ticker : stocks.keySet()) {
      xmlBuilder.append("<ticker>").append(ticker).append("</ticker>");
    }
    xmlBuilder.append("</tickers>");
  }

  private void fillShares(StringBuilder xmlBuilder) {
    xmlBuilder.append("<shares>");
    for (Map.Entry<String, Map<LocalDate, Double>> shareEntry : share.entrySet()) {
      xmlBuilder.append("<share ticker=\"").append(shareEntry.getKey()).append("\">");
      for (Map.Entry<LocalDate, Double> dateEntry : shareEntry.getValue().entrySet()) {
        xmlBuilder.append("<date value=\"").append(dateEntry.getKey().toString()).append("\">");
        xmlBuilder.append(dateEntry.getValue());
        xmlBuilder.append("</date>");
      }
      xmlBuilder.append("</share>");
    }
    xmlBuilder.append("</shares>");
  }

  @Override
  public void saveXml(String fileName) throws IOException {
    String xml = toXml();
    try {
      File directory = new File(new File("").getAbsolutePath() + "\\src\\savedPortfolios\\");
      File file = new File(directory, fileName + ".xml");
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(xml);
      fileWriter.close();
    } catch (IOException e) {
      File directory = new File(new File("").getAbsolutePath() + "/src/savedPortfolios/");
      File file = new File(directory, fileName + ".xml");
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write(xml);
      } catch (IOException g) {
        throw g;
      }
    }
  }
}
// new pro and model . change controller command
