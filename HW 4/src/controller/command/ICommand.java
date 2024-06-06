package controller.command;

import model.IModel;

public interface ICommand<T> {
  T run(IModel model);
}
