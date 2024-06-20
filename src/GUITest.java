import java.io.InputStreamReader;
import java.util.Arrays;

import controller.ControllerImpl;
import controller.ControllerImplGUI;
import controller.IController;
import view.GUI;
import model.IModel2;
import model.ModelImpl2;

/**
 * Initializes the program with a GUI view.
 */
public class GUITest {
  public static void main(String[] args) {

    if (args.toString().equals("text")) {
      TextView.main(new String[0]);
    }

    GUI view = new GUI();
    IModel2 model = new ModelImpl2();
    IController controller = new ControllerImplGUI(view, model);
    controller.init(model);
  }
}
