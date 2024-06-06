package controller.command;

import java.util.Scanner;

import model.IModel;

/**
 * Interface for commands in the command design pattern. Open for future
 * implementations of command.
 */
public interface ICommand {
  void run(Scanner sc, IModel model);
}
