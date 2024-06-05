
import java.io.InputStreamReader;

import controller.AlphaVantageStreamReader;
import controller.ControllerImpl;
import controller.IController;
import controller.IReader;
import model.IModel;

public class Main {

  public static void main(String[] args) {
    IModel model = new model.ModeImpl();
    IReader streamReader = new AlphaVantageStreamReader("AMZN");
//  IReader fileReader = new FileReader();
////    model.populate(fileReader.getReadable());

    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go();


    // add stock w/ ticker ** stock creator needs readble
    // create stockcreator  -> getReadable -> create stock
  }
}
