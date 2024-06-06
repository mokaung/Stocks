
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
    String ticker = "AMZN";
//    IReader streamReader = new AlphaVantageStreamReader(ticker);
    IReader CSVReader = new CSVReader("googleStock");

    IModel model = new ModelImpl();
    model.populate(CSVReader.getReadable(), ticker);
    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go(model);
  }
}