package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import controller.command.AlphaVantageStreamReader;
import controller.command.CSVReader;
import controller.command.IReader;
import controller.command.XMLToPortfolio;
import model.IModel2;
import view.IView;
import view.IViewListener;

import static controller.command.ControllerUtil.calToString;

public class ControllerImplGUI implements IController, IViewListener {
  private final IView view;
  private final IModel2 model;

  public ControllerImplGUI(IModel2 model, IView view) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void init(IModel2 model) {
    view.render(true);
  }

  @Override
  public ArrayList<String> listPortfolios() throws IllegalArgumentException {
    return model.getPortfolioNames();
  }

  @Override
  public ArrayList<String> listStocks() throws IllegalArgumentException {
    return model.getStockNames();
  }

  @Override
  public String handleGetPortfolioValue(String name, LocalDate date) throws IllegalArgumentException {
    if (model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      model.portfolioToString(name, date.format(formatter), date);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      throw e;
    }
    return name;
  }

  @Override
  public void handleBuyStock(String name, String ticker, int share, LocalDate date1) throws IllegalArgumentException {
    if (model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    if (model.isInvalidTicker(ticker)) {
      throw new IllegalArgumentException("Make sure to spell the ticker correctly and populate first.");
    }
    if (model.isInvalidLocalDate(date1, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date1) + " doesn't exist.");
    }
    model.buyStock(name, ticker, share, date1);
  }

  @Override
  public void handleSellStock(String name, String ticker, double share, LocalDate date1) throws IllegalArgumentException {
    if (model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    if (model.isInvalidTicker(ticker)) {
      throw new IllegalArgumentException("Make sure to spell the ticker correctly and populate first.");
    }
    if (model.isInvalidLocalDate(date1, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date1) + " doesn't exist.");
    }
    model.sellStock(name, ticker, share, date1);
  }

  @Override
  public void handlePopulateStock(String ticker) throws IllegalArgumentException {
    IReader alpha = new AlphaVantageStreamReader(ticker);
    try {
      model.populate(alpha.getReadable(), ticker);
    } catch (Exception e) {
      throw new IllegalArgumentException("Sorry, your stock could not be loaded.");
    }
  }

  @Override
  public void handleCSVStock(String ticker) throws IllegalArgumentException {
    try {
      IReader reader = new CSVReader(ticker);
      model.populate(reader.getReadable(), ticker);
    } catch (Exception e) {
      throw new IllegalArgumentException("Your stock cannot be loaded.");
    }
  }

  @Override
  public void handleSavePortfolio(String name) throws IllegalArgumentException {
    if (model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    try {
      model.savePortfolio(name);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public ArrayList<String> listLoadablePortfolios() throws IllegalArgumentException {
    ArrayList<String> listPort = new ArrayList<>();
    File directory = getDirectory();
    if (!directory.exists() || !directory.isDirectory()) {
      throw new IllegalArgumentException("Please make sure the folder 'savedPortfolios' exists.");
    }
    File[] files = directory.listFiles();
    for (File file : files) {
      if (file.isFile() && file.getName().endsWith(".xml")) {
        listPort.add(file.getName().substring(0, file.getName().length() - 4));
      }
    }
    return listPort;
  }

  @Override
  public void handleLoadPortfolio(String name) throws IllegalArgumentException {
    String nameWithExtension = name + ".xml";
    File directory = getDirectory();
    File fileCheck = new File(directory, nameWithExtension);
    XMLToPortfolio converter = new XMLToPortfolio(model);
    try {
      converter.convertXmlToPortfolio(fileCheck);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  private File getDirectory() throws IllegalArgumentException {
    File directory = new File(new File("").getAbsolutePath() + "\\src\\savedPortfolios\\");
    if (!directory.exists() || !directory.isDirectory()) {
      directory = new File(new File("").getAbsolutePath() + "/src/savedPortfolios/");
    }
    return directory;
  }

  @Override
  public void handleCreatePortfolio(String ticker, double share, String name, LocalDate date1) throws IllegalArgumentException {
    if (model.isInvalidTicker(ticker)) {
      throw new IllegalArgumentException("Make sure to spell the ticker correctly and populate first.");
    }
    if (model.isInvalidLocalDate(date1, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date1) + " doesn't exist.");
    }
    model.createPortfolioV2(ticker, share, name, date1);
  }

  @Override
  public void handleAddToPortfolio(String ticker, double share, String name, LocalDate date1) throws IllegalArgumentException {
    if (model.isInvalidTicker(ticker)) {
      throw new IllegalArgumentException("Make sure to spell the ticker correctly and populate first.");
    }
    if (model.isInvalidLocalDate(date1, ticker)) {
      throw new IllegalArgumentException("Sorry, stock information for " + calToString(date1) + " doesn't exist.");
    }
    model.addToPortfolioV2(name, ticker, share, date1);
  }
}
