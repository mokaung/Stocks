package controller;


import view.GUI;
import model.IModel2;
import model.ModelImpl2;

/**
 * Initializes the program with a GUI view.
 */
public class GUITest {
  public static void main(String[] args) {
    GUI view = new GUI();
    IModel2 model = new ModelImpl2();
    IController controller = new ControllerImplGUI(view, model);
    controller.init(model);
  }
}
