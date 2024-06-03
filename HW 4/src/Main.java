
import controller.AlphaVantageStreamReader;
import controller.ControllerImpl;
import controller.FileReader;
import controller.IController;
import model.IModel;
import controller.IReader;

public class Main {


  public static void main(String[] args) {
    IModel model = new Modelmpl();

    IReader streamReader = new AlphaVantageStreamReader();
    IReader fileReader = new FileReader();
    model.populate(fileReader.getReadable());

    IController controller = new ControllerImpl();
    controller.go();
  }
}
