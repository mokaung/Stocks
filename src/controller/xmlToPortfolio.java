package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.IModel;
import model.IStock;
import model.Stock;

public class xmlToPortfolio implements IParseXml {
  private final IModel model;

  public xmlToPortfolio(IModel model) {
    this.model = model;
  }

  public void convertXmlToPortfolio(File file) {
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(file);

      doc.getDocumentElement().normalize();

      NodeList portfolioList = doc.getElementsByTagName("portfolio");

      for (int i = 0; i < portfolioList.getLength(); i++) {
        Node portfolioNode = portfolioList.item(i);

        if (portfolioNode.getNodeType() == Node.ELEMENT_NODE) {
          Element portfolioElement = (Element) portfolioNode;
          String portfolioName = portfolioElement.getAttribute("name");

          NodeList stockList = portfolioElement.getElementsByTagName("stock");

          for (int j = 0; j < stockList.getLength(); j++) {
            Node stockNode = stockList.item(j);

            if (stockNode.getNodeType() == Node.ELEMENT_NODE) {
              Element stockElement = (Element) stockNode;

              String ticker = stockElement.getElementsByTagName("ticker").item(0).getTextContent();
              LocalDate date = LocalDate.parse(stockElement.getElementsByTagName("date").item(0).getTextContent());
              double open = Double.parseDouble(stockElement.getElementsByTagName("open").item(0).getTextContent());
              double high = Double.parseDouble(stockElement.getElementsByTagName("high").item(0).getTextContent());
              double low = Double.parseDouble(stockElement.getElementsByTagName("low").item(0).getTextContent());
              double close = Double.parseDouble(stockElement.getElementsByTagName("close").item(0).getTextContent());
              int volume = Integer.parseInt(stockElement.getElementsByTagName("volume").item(0).getTextContent());

              IStock stock = new Stock(date, open, high, low, close, volume, ticker);

              if (!model.portfolios.containsKey(portfolioName)) {
                Map<LocalDate, IStock> stockMap = new HashMap<>();
                stockMap.put(date, stock);
                model.createPortfolio(ticker, 10, portfolioName);
              } else {
                model.addToPortfolio(portfolioName, ticker, 10);
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
