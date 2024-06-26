import java.io.InputStreamReader;

import controller.ControllerImpl;
import controller.ControllerImplGUI;
import controller.IController;
import model.IModel2;
import model.ModelImpl2;
import view.GUI;
import view.IView;

/**
 * Initializes the program with a GUI view.
 */
public class GUITest {
  /**
   * Initializes either the text or GUI view.
   *
   * @param args if there is an args that equals text, initialize text. If no args then GUI.
   *             If none, print an error.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      IView view = new GUI();
      IModel2 model = new ModelImpl2();
      IController controller = new ControllerImplGUI(model, view);
      controller.init(model);
    } else if (args[0].equals("-text")) {
      IModel2 model = new ModelImpl2();

      IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
      controller.init(model);
    } else {
      System.out.println("ERROR");
    }
  }
}
