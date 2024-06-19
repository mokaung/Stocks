package controller;


import java.time.LocalDate;

import javax.xml.namespace.QName;

import View.IView;
import View.IViewListener;
import model.IModel2;
import model.ModelImpl2;

public class ControllerImplGUI implements IController, IViewListener {
  private final IView view;
  private final IModel2 model;

  public ControllerImplGUI(IView view, IModel2 model) {
    this.view = view;
    this.model = model;
    view.addViewListener(this);
  }

  @Override
  public void init(IModel2 model) {
    // make visible?
  }

  @Override
  public void handleCreatePortfolio(String ticker, double share, String name, LocalDate date) {
    model.createPortfolioV2(ticker, share, name, date);
    view.requestFocus();
  }
}