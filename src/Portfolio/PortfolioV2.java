package Portfolio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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

  public PortfolioV2(String name) {
    this.name = name;
    stocks = new HashMap<>();
    share = new HashMap<>();
    this.transaction = new Transaction();
  }

  @Override
  public double getValue(LocalDate date) {
    double answer = 0;
    for (Map.Entry<String, Map<LocalDate, IStock>> entry : stocks.entrySet()) {
      IStock stock = entry.getValue().get(date);
      if (stock == null) {
        throw new IllegalArgumentException("Sorry, information for the portfolio at " + date + " is unavailable. Please try another date.");
      }
      if (stock.getDate().equals(date)) {
        System.out.println(share.get(entry.getKey()).containsKey(date));
        double stockShare = share.get(entry.getKey()).get(date);
        answer += stock.getClose() * stockShare;
      }
    }
    return answer;
  }

  //TODO: SOLID principal here
  @Override
  public void setValue(Map<LocalDate, IStock> stock, int share, String ticker) {

  }

  @Override
  public void rebalance(LocalDate date, ArrayList<Weight> weights) {
    double totalVal = getValue(date);

    for (Weight weight : weights) {
      String ticker = weight.getTicker();
      double targetPercent = weight.getPercent();

      double stockShare = share.get(ticker).get(date);
      double stockVal = stocks.get(ticker).get(date).getClose();
      double currentVal = stockShare * stockVal;

      double targetVal = totalVal * targetPercent;
      double newShare = targetVal / stockVal;

      // checks to see if the difference between the 1%
      if (Math.abs(targetPercent - (currentVal / totalVal)) > 0.01) {
        share.get(ticker).put(date, newShare);
      }
    }
  }

  public void buyStock(double share, String ticker, LocalDate date) {
    if (!transaction.check(ticker, date)) {
      double owned = this.share.get(ticker).get(date);
      this.share.get(ticker).put(date, owned + share);
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
    fillStocks(xmlBuilder);
    fillShares(xmlBuilder);
    xmlBuilder.append("</portfolio>");
    return xmlBuilder.toString();
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

  private void fillStocks(StringBuilder xmlBuilder) {
    xmlBuilder.append("<stocks>");
    for (Map.Entry<String, Map<LocalDate, IStock>> stockEntry : stocks.entrySet()) {
      xmlBuilder.append("<stock ticker=\"").append(stockEntry.getKey()).append("\">");
      for (Map.Entry<LocalDate, IStock> dateEntry : stockEntry.getValue().entrySet()) {
        xmlBuilder.append("<date value=\"").append(dateEntry.getKey().toString()).append("\">");
        xmlBuilder.append(dateEntry.getValue().toXml());
        xmlBuilder.append("</date>");
      }
      xmlBuilder.append("</stock>");
    }
    xmlBuilder.append("</stocks>");
  }

  @Override
  public void saveXml(String fileName) throws IOException {
    String xml = toXml();
    try {
      File directory = new File(new File("").getAbsolutePath() + "\\src\\savedPortfolios\\");
      File file = new File(directory, fileName + ".xml");
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(xml);
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

//  @Override
//  public String toJson() {
//    StringBuilder jsonBuilder = new StringBuilder();
//    fillStocks(jsonBuilder);
//    fillShares(jsonBuilder);
//    jsonBuilder.append("}");
//
//    jsonBuilder.append("}");
//    return jsonBuilder.toString();
//  }
//
//  private void fillShares(StringBuilder jsonBuilder) {
//    jsonBuilder.append("},");
//    jsonBuilder.append("\"share\":{");
//    int shareCount = 0;
//    for (Map.Entry<String, Map<LocalDate, Double>> shareEntry : share.entrySet()) {
//      jsonBuilder.append("\"").append(shareEntry.getKey()).append("\":{");
//      int dateCount = 0;
//      for (Map.Entry<LocalDate, Double> dateEntry : shareEntry.getValue().entrySet()) {
//        jsonBuilder.append("\"").append(dateEntry.getKey().toString()).append("\":");
//        jsonBuilder.append(dateEntry.getValue());
//        dateCount++;
//        if (dateCount < shareEntry.getValue().size()) {
//          jsonBuilder.append(",");
//        }
//      }
//      jsonBuilder.append("}");
//      shareCount++;
//      if (shareCount < share.size()) {
//        jsonBuilder.append(",");
//      }
//    }
//  }
//
//  private void fillStocks(StringBuilder jsonBuilder) {
//    jsonBuilder.append("{");
//    jsonBuilder.append("\"stocks\":{");
//    int stockCount = 0;
//    for (Map.Entry<String, Map<LocalDate, IStock>> stockEntry : stocks.entrySet()) {
//      jsonBuilder.append("\"").append(stockEntry.getKey()).append("\":{");
//      int dateCount = 0;
//      for (Map.Entry<LocalDate, IStock> dateEntry : stockEntry.getValue().entrySet()) {
//        jsonBuilder.append("\"").append(dateEntry.getKey().toString()).append("\":");
//        jsonBuilder.append(dateEntry.getValue().toJson());
//        dateCount++;
//        if (dateCount < stockEntry.getValue().size()) {
//          jsonBuilder.append(",");
//        }
//      }
//      jsonBuilder.append("}");
//      stockCount++;
//      if (stockCount < stocks.size()) {
//        jsonBuilder.append(",");
//      }
//    }
//  }
//
//  @Override
//  public void saveJson(String fileName) throws IOException {
//    try {
//      String json = toJson();
//      File directory = new File(new File("").getAbsolutePath() + "\\HW 4\\src\\savedPortfolios\\");
//      File file = new File(directory, fileName + ".txt");
//      FileWriter fileWriter = new FileWriter(file);
//      fileWriter.write(json);
//    } catch (IOException e) {
//      String json = toJson();
//      File directory = new File(new File("").getAbsolutePath() + "/src/savedPortfolios/");
//      File file = new File(directory, fileName + ".txt");
//      try {
//        FileWriter fileWriter = new FileWriter(file);
//        fileWriter.write(json);
//      } catch (IOException g) {
//        throw g;
//      }
//    }
//  }
}
// new pro and model . change controller command
