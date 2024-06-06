package controller.command;

import model.IModel;

public interface ICommand {
  void run(IModel model);
}
