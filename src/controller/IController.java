package controller;

import model.IModel2;

/**
 * Interface for the controller. Allows future implementation of the controllers, that might
 * have different behaviors.
 */
public interface IController {
  /**
   * runs the program.
   *
   * @param model used for computation.
   */
  void init(IModel2 model);
}
