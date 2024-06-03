package controller;

import model.IModel;

public interface ICommand {
  void run(IModel model);
}
