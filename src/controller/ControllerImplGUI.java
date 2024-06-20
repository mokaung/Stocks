package controller;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import View.IView;
import View.IViewListener;
import controller.command.XMLToPortfolio;
import model.IModel2;

import static controller.command.ControllerUtil.writeMessage;

public class ControllerImplGUI implements IController {
//  private final IView view;
  private final IModel2 model;

  public ControllerImplGUI( IModel2 model) {
//    this.view = view;
    this.model = model;
//    view.addViewListener(this);
  }

  @Override
  public void init(IModel2 model) {
//   view.render();
  }

  public ArrayList<String> listPortfolios() {
    return model.getPortfolioNames();
  }

  public void handleSavePortfolio(String name)throws IllegalArgumentException {
    if (model.isInvalidPortfolio(name)) {
      throw new IllegalArgumentException("Invalid portfolio.");
    }
    try {
      model.savePortfolio(name);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public ArrayList<String> listLoadablePortfolios() {
    ArrayList<String> listPort = new ArrayList<String>();
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

  public void handleLoadPortfolio(String name)throws IllegalArgumentException {
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

  private File getDirectory() {
    File directory = new File(new File("").getAbsolutePath() + "\\src\\savedPortfolios\\");
    if (!directory.exists() || !directory.isDirectory()) {
      directory = new File(new File("").getAbsolutePath() + "/src/savedPortfolios/");
    }
    return directory;
  }

//  @Override
  public void handleCreatePortfolio(String ticker, double share, String name, LocalDate date) {
    // TODO try catch
    model.createPortfolioV2(ticker, share, name, date);
//    view.requestFocus();
  }

//  @Override
  public void handleGetPortfolioValue(String name, LocalDate date) {
    model.getPortfolioValue(name, date);
//    view.requestFocus();
  }

//  @Override
  public void handleSellStock(String ticker, double share, String name, LocalDate date) {
    model.addToPortfolioV2(name, ticker, share, date);
//    view.requestFocus();
  }

  public void handleLoadPortfolio() {

  }
}