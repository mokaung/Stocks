
import java.io.InputStreamReader;
import java.time.LocalDate;

import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.IPortfolioV2;
import model.ModelImpl;
import model.PortfolioV2;
import view.IViews;
import view.View;

public class Main {

  public static void main(String[] args) {
    IModel model = new ModelImpl();

    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go(model);
    IViews view = new View(new InputStreamReader(System.in), System.out);
    IPortfolioV2 p = new PortfolioV2(model, "a");
    view.getPerformance(p, LocalDate.of(2023,10,10), LocalDate.now());
  }
}