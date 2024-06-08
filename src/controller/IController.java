package controller;

import model.IModel;

/**
 * Interface for the controller. Allows future implementation of the controllers, that might
 * have different behaviors.
 */
public interface IController  {
  void go(IModel model);
}
