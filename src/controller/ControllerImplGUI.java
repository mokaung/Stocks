package controller;


import View.IView;
import View.IViewListener;
import model.IModel2;

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

  }
}