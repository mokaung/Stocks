
import java.io.InputStreamReader;
import java.time.LocalDate;

import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import Portfolio.IPortfolioV2;
import model.IModel2;
import model.ModelImpl;
import Portfolio.PortfolioV2;
import model.ModelImpl2;
import view.IViews;
import view.View;

public class Main {

  public static void main(String[] args) {
    IModel2 model = new ModelImpl2();

    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go(model);
    IViews view = new View(new InputStreamReader(System.in), System.out);
    IPortfolioV2 p = new PortfolioV2("a");
    view.getPerformance(p, LocalDate.of(2023,10,10), LocalDate.now());
  }
}