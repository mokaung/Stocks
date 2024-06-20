package controller;

import View.GUI;
import model.ModelImpl2;

public class GUITest {
  public static void main(String[] args) {
    // Create the view
    GUI view = new GUI("Portfolio Manager Test", new ControllerImplGUI(new ModelImpl2()));
    // Show the view
    view.render();
  }
}
