package controller.command;

import java.util.Scanner;

import model.IModel;

public interface ICommand {
  void run(Scanner sc, IModel model);
}
