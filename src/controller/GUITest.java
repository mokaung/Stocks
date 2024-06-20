package controller;

import java.lang.ModuleLayer.Controller;

import View.GUI;
import model.IModel2;
import model.ModelImpl2;
//import View.GUI;

public class GUITest {
  public static void main(String[] args) {
//     Create the view
    GUI view = new GUI();
    IModel2 model = new ModelImpl2();
    IController controller = new ControllerImplGUI(view, model);
    controller.init(model);
  }
}
