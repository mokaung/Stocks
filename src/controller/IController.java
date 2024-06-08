package controller;

import model.IModel;

/**
 * Interface for the controller. Allows future implementation of the controllers, that might
 * have different behaviors.
 */
public interface IController  {
  /**
   * runs the program.
   * @param model used for computation.
   */
  void go(IModel model);
}
