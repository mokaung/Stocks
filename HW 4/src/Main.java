
import java.io.InputStreamReader;

import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.ModelImpl;

public class Main {

  public static void main(String[] args) {
    IModel model = new ModelImpl();

    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go(model);
  }
}