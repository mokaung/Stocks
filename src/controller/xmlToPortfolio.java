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

import Model.IModel2;
import Model.IStock;
import Model.Stock;

public class xmlToPortfolio implements IParseXml {
  private IModel2 model;

  public xmlToPortfolio(IModel2 model) {
    this.model = model;
  }

  public void convertXmlToPortfolio(File file) throws IOException {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(file);

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
              Element dateElement = (Element) dateNodes.item(j);
              String dateString = dateElement.getAttribute("value");
              String ticker = stockElement.getElementsByTagName("ticker").item(0).getTextContent();
              LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
              double open = Double.parseDouble(stockElement.getElementsByTagName("open").item(0).getTextContent());
              double high = Double.parseDouble(stockElement.getElementsByTagName("high").item(0).getTextContent());
              double low = Double.parseDouble(stockElement.getElementsByTagName("low").item(0).getTextContent());
              double close = Double.parseDouble(stockElement.getElementsByTagName("close").item(0).getTextContent());
              int volume = Integer.parseInt(stockElement.getElementsByTagName("volume").item(0).getTextContent());

              IStock stock = new Stock(date, open, high, low, close, volume, ticker);

              // Find the corresponding share element
              NodeList shareList = portfolioElement.getElementsByTagName("share");
              double shares = 0;
              for (int k = 0; k < shareList.getLength(); k++) {
                Element shareElement = (Element) shareList.item(k);
                if (shareElement.getAttribute("ticker").equals(ticker)) {
                  shares = Double.parseDouble(shareElement.getElementsByTagName("date").item(0).getTextContent());
                  break;
                }
              }

              if (!model.isInvalidPortfolio(portfolioName)) {
                if (!model.isInvalidTicker(ticker)) {
                  model.addToPortfolioV2(portfolioName, ticker, shares, date);
                } else {
                  throw new IllegalArgumentException("Please load $" + ticker + " first.");
                }
              } else {
                if (!model.isInvalidTicker(ticker)) {
                  System.out.println(portfolioName);
                  model.createPortfolioV2(ticker, shares, portfolioName, date);
                } else {
                  throw new IllegalArgumentException("Please load $" + ticker + " first.");
                }
              }
            }
          }
        }
      }
      //TODO exception
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
