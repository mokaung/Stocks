
import java.io.InputStreamReader;

import controller.AlphaVantageStreamReader;
import controller.ControllerImpl;
import controller.IController;
import controller.IReader;
import model.IModel;
import model.ModelImpl;

public class Main {

  public static void main(String[] args) {
    String ticker = "AMZN";
    IReader streamReader = new AlphaVantageStreamReader(ticker);
    IModel model = new ModelImpl();
    model.populate(streamReader.getReadable(), ticker);
    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go(model);
  }
}