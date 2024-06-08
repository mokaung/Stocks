package controller.command;

import java.util.Scanner;

import model.IModel;

/**
 * Interface for commands in the command design pattern. Open for future
 * implementations of command.
 */
public interface ICommand {
  /**
   * Runs the given command in model form the controller input.
   * @param sc Scanner
   * @param model Model
   */
  void run(Scanner sc, IModel model);
}