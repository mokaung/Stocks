package controller.command;

import View.IView;
import model.IModel2;

public interface ICommand2 {

  void run(IModel2 model, IView view);
}
