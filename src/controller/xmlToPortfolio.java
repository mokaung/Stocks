package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.time.format.DateTimeFormatter;

import model.IModel;
import model.IModel2;
import model.IStock;
import model.Stock;

public class xmlToPortfolio implements IParseXml {
  private IModel model;

  public xmlToPortfolio(IModel model) {
    this.model = model;
  }

  public void convertXmlToPortfolio(File file) throws IOException {
    if (model instanceof IModel2) {
      model = new ModelAdapter((IModel2) model);
    }
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
          String portfolioName = file.getName().substring(0, file.getName().length() - 4);

          NodeList stockList = portfolioElement.getElementsByTagName("stock");
          NodeList dateNodes = doc.getElementsByTagName("date");
          for (int j = 0; j < stockList.getLength(); j++) {
            Node stockNode = stockList.item(j);
            if (stockNode.getNodeType() == Node.ELEMENT_NODE) {
              Element stockElement = (Element) stockNode;
              Element dateElement = (Element) dateNodes.item(i);
              String dateString = dateElement.getAttribute("value");
              String ticker = stockElement.getElementsByTagName("ticker").item(0).getTextContent();
//              LocalDate date = LocalDate.parse(stockElement.getElementsByTagName("date").item(0).getTextContent());
              LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
              double open = Double.parseDouble(stockElement.getElementsByTagName("open").item(0).getTextContent());
              double high = Double.parseDouble(stockElement.getElementsByTagName("high").item(0).getTextContent());
              double low = Double.parseDouble(stockElement.getElementsByTagName("low").item(0).getTextContent());
              double close = Double.parseDouble(stockElement.getElementsByTagName("close").item(0).getTextContent());
              int volume = Integer.parseInt(stockElement.getElementsByTagName("volume").item(0).getTextContent());

              IStock stock = new Stock(date, open, high, low, close, volume, ticker);

              if (!model.isInvalidPortfolio(portfolioName)) {
                if (!model.isInvalidTicker(ticker)) {
                  ((ModelAdapter) model).addToPortfolioV2(portfolioName, ticker, 10, date);
                } else {
                  throw new IllegalArgumentException("Please load $" + ticker + " first.");
                }
              } else {
                if (!model.isInvalidTicker(ticker)) {
                  System.out.println(portfolioName);
                  ((ModelAdapter) model).createPortfolioV2(ticker, 10, portfolioName, date);
                } else {
                  throw new IllegalArgumentException("Please load $" + ticker + " first.");
                }
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
