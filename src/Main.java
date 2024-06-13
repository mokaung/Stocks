
import java.io.InputStreamReader;

import controller.ControllerImpl;
import controller.IController;
import Model.IModel2;
import Model.ModelImpl2;

/**
 * Initiates the program.
 */
public class Main {

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