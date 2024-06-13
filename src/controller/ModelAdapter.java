package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import Portfolio.IPortfolio;
import Portfolio.IPortfolioV2;
import model.IModel2;
import model.IStock;

public class ModelAdapter implements IModel2 {
  private final IModel2 model2;

  public ModelAdapter(IModel2 model2) {
    this.model2 = Objects.requireNonNull(model2);
  }

  @Override
  public ArrayList<LocalDate> crossover(int avg, LocalDate date1, LocalDate date2, String ticker) {
    return model2.crossover(avg, date1, date2, ticker);
  }

  @Override
  public double gainOrLoss(LocalDate start, LocalDate close, String ticker) {
    return model2.gainOrLoss(start,close,ticker);
  }

  @Override
  public double movingAverage(int movingWindow, LocalDate date, String ticker) {
    return model2.movingAverage(movingWindow,date,ticker);
  }

  @Override
  public IPortfolio createPortfolio(String ticker, int share, String name) {
    return model2.createPortfolio(ticker,share,name);
  }

  @Override
  public boolean isInvalidLocalDate(LocalDate cal, String ticker) {
    return model2.isInvalidLocalDate(cal,ticker);
  }

  @Override
  public boolean isInvalidPortfolio(String name) {
    return model2.isInvalidPortfolio(name);
  }

  @Override
  public boolean isInvalidTicker(String ticker) {
    return model2.isInvalidTicker(ticker);
  }

  @Override
  public void addToPortfolio(String s, String ticker, int share) {
    model2.addToPortfolio(s,ticker,share);
  }

  @Override
  public Double getPortfolioValue(String s, LocalDate cal) {
    return model2.getPortfolioValue(s,cal);
  }

  @Override
  public Map<String, Map<LocalDate, IStock>> getStock() {
    return model2.getStock();
  }

  @Override
  public void populate(Readable readable, String ticker) {
    model2.populate(readable, ticker);
  }

  @Override
  public void savePortfolio(String portfolioName) throws IOException {
    model2.savePortfolio(portfolioName);
  }

  @Override
  public IPortfolioV2 createPortfolioV2(String ticker, int share, String name, LocalDate date){
    return model2.createPortfolioV2(ticker,share,name,date);
  }

  @Override
  public void addToPortfolioV2(String portFolioName, String ticker, int share, LocalDate date){
    model2.addToPortfolioV2(portFolioName,ticker,share,date);
  }

}
