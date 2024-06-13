package controller.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Model.IModel;
import Model.IModel2;
import controller.ModelAdapter;

import static controller.command.ControllerUtil.writeMessage;

public class GetPerformance implements ICommand {
  private final Appendable out;

  public GetPerformance(Appendable out) {
    this.out = out;
  }

  @Override
  public void run(Scanner sc, IModel model) {
    if (model instanceof IModel2) {
      model = new ModelAdapter((IModel2) model);

      writeMessage("Which portfolio do you want to analyze? " + System.lineSeparator(), out);

      String name = sc.nextLine();
      if (model.isInvalidPortfolio(name)) {
        throw new IllegalArgumentException("Invalid portfolio");
      }

      writeMessage("Please input the start date." + System.lineSeparator(), out);
      LocalDate startDate;
      try {
        startDate = LocalDate.parse(sc.nextLine());
      } catch (DateTimeParseException e) {
        throw new IllegalArgumentException("Invalid start date");
      }
      writeMessage("Please input the end date." + System.lineSeparator(), out);
      LocalDate endDate;
      try {
        endDate = LocalDate.parse(sc.nextLine());
      } catch (DateTimeParseException e) {
        throw new IllegalArgumentException("Invalid end date");
      }

//      writeMessage(model.getPerformance(name, startDate, endDate), out);
    }
  }
}
