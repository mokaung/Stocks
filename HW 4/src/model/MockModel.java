//package model;
//import java.util.ArrayList;
//import java.util.Calendar;
//
//public class MockModel implements IModel {
//  private final StringBuilder log;
//
//  public MockModel() {
//    this.log = new StringBuilder();
//  }
//
//  @Override
//  public void populate(Readable readable, String ticker) {
//    log.append(readable);
//  }
//
//  @Override
//  public ArrayList<Calendar> crossover() {
//    log.append("Crossover");
//    return null;
//  }
//
//  @Override
//  public Double gainOrLoss() {
//    log.append("Gain or Loss");
//    return null;
//  }
//
//  @Override
//  public Double movingAverage() {
//    log.append("Moving Average");
//    return null;
//  }
//
//  @Override
//  public IStock getStock() {
//    log.append("getStock");
//    return null;
//  }
//
//  @Override
//  public IPortfolio createPortfolio(String ticker, int share) {
//    log.append("createPortfolio");
//    return null;
//  }
//}
