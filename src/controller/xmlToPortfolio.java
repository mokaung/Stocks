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

public class xmlToPortfolio implements IParseXml {
  private IModel2 model;

  public xmlToPortfolio(IModel2 model) {
    this.model = model;
  }

  public void convertXmlToPortfolio(File file) throws IllegalArgumentException {
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

          NodeList tickerList = portfolioElement.getElementsByTagName("ticker");
          NodeList shareList = portfolioElement.getElementsByTagName("share");

          for (int j = 0; j < tickerList.getLength(); j++) {
            String ticker = tickerList.item(j).getTextContent();

            Node shareNode = shareList.item(j);
            if (shareNode.getNodeType() == Node.ELEMENT_NODE) {
              Element shareElement = (Element) shareNode;
              NodeList dateNodes = shareElement.getElementsByTagName("date");

              for (int k = 0; k < dateNodes.getLength(); k++) {
                Element dateElement = (Element) dateNodes.item(k);
                String dateString = dateElement.getAttribute("value");
                LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
                double shares = Double.parseDouble(dateElement.getTextContent());

                if (!model.isInvalidPortfolio(portfolioName)) {
                  if (!model.isInvalidTicker(ticker)) {
                    System.out.println("BEGINNING ADDING PROCESS");
                    System.out.println("Portfolioname: " + portfolioName);
                    System.out.println("ticker: " + ticker);
                    System.out.println("shares: " + shares);
                    System.out.println("date: " + date);
                    System.out.println("DONE ADDING");
                    model.addToPortfolioV2(portfolioName, ticker, shares, date);
                  } else {
                    throw new IllegalArgumentException("Please load $" + ticker + " first.");
                  }
                } else {
                  if (!model.isInvalidTicker(ticker)) {
                    System.out.println("BEGINNING CREATION PROCESS");
                    System.out.println("Portfolioname: " + portfolioName);
                    System.out.println("ticker: " + ticker);
                    System.out.println("shares: " + shares);
                    System.out.println("date: " + date);
                    System.out.println("DONE CREATING");
                    model.createPortfolioV2(ticker, shares, portfolioName, date);
                  } else {
                    throw new IllegalArgumentException("Please load $" + ticker + " first.");
                  }
                }
              }
            }
          }
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
