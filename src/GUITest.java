import java.io.InputStreamReader;

import controller.ControllerImpl;
import controller.ControllerImplGUI;
import controller.IController;
import model.IModel2;
import model.ModelImpl2;
import view.IView;

/**
 * Initializes the program with a GUI view.
 */
public class GUITest {
  public static void main(String[] args) {
    if (args.length == 0) {
      IView view = new GUI();
      IModel2 model = new ModelImpl2();
      IController controller = new ControllerImplGUI(view, model);
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
