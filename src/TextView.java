
import java.io.InputStreamReader;

import controller.ControllerImpl;
import controller.IController;
import model.IModel2;
import model.ModelImpl2;

/**
 * Initiates the program.
 */
public class TextView {

  /**
   * Initiates the program.
   * @param args args.
   */
  public static void main(String[] args) {
    IModel2 model = new ModelImpl2();

    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.init(model);
  }
}