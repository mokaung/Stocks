package controller;

import View.GUI;
import model.ModelImpl2;
//import View.GUI;

public class GUITest {
  public static void main(String[] args) {
    // Create the view
    GUI view = new GUI("Portfolio Manager Test", new ControllerImplGUI(new ModelImpl2()));
//    GUI view = new GUI("Portfolio Manager Test");

    // Show the view
//    view.render();
  }
}
