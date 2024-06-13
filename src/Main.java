
import java.io.InputStreamReader;
import java.time.LocalDate;

import controller.ControllerImpl;
import controller.IController;
import Portfolio.IPortfolioV2;
import Model.IModel2;
import Portfolio.PortfolioV2;
import Model.ModelImpl2;
import view.IViews;
import view.View;

public class Main {

  public static void main(String[] args) {
    IModel2 model = new ModelImpl2();

    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go(model);
    IPortfolioV2 p = new PortfolioV2("a");
  }
}