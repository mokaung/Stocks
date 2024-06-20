package controller;


import View.GUI;
import model.IModel2;
import model.ModelImpl2;

public class GUITest {
  public static void main(String[] args) {
//     Create the view
    GUI view = new GUI();
    IModel2 model = new ModelImpl2();
    IController controller = new ControllerImplGUI(view, model);
    controller.init(model);
  }
}
