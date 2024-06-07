
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.AlphaVantageStreamReader;
import controller.CSVReader;
import controller.ControllerImpl;
import controller.IController;
import controller.IReader;
import model.IModel;
import model.ModelImpl;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    String ticker = "NFLX";
//    IReader reader = new AlphaVantageStreamReader(ticker);
    IReader reader = new CSVReader("googleStock");

    IModel model = new ModelImpl();
    model.populate(reader.getReadable(), ticker);

    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go(model);
  }
}