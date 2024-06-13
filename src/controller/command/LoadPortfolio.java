package controller.command;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import controller.ModelAdapter;
import controller.xmlToPortfolio;
import model.IModel;
import model.IModel2;

import static controller.command.ControllerUtil.writeMessage;

public class LoadPortfolio implements ICommand {

  private final Appendable out;

  /**
   * Constructor takes in the Appendable used for output.
   *
   * @param out The appendable used for outputs in the program.
   */
  public LoadPortfolio(Appendable out) {
    this.out = out;
  }

  @Override
  public void run(Scanner sc, IModel model) throws IllegalArgumentException {
    if (model instanceof IModel2) {
      model = new ModelAdapter((IModel2) model);
    }
    writeMessage("Which portfolio would you like to load? Please type the entire file name."
            + " (example.xml)"
            + System.lineSeparator(), out);
    File directory = getDirectory();
    if (!directory.exists() || !directory.isDirectory()) {
      throw new IllegalArgumentException("Please make sure the folder 'savedPortfolios' exists.");
    }
    File[] files = directory.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isFile() && file.getName().endsWith(".xml")) {
          writeMessage("- " + file.getName() + System.lineSeparator(), out);
        }
      }
    } else {
      throw new IllegalArgumentException("Please make sure you have saved portfolios."
              + " The directory is empty.");
    }
    String name = sc.next();
    File fileCheck = new File(directory, name);
    if (!fileCheck.exists() || !fileCheck.isFile()) {
      throw new IllegalArgumentException("Are you sure you entered the full file name? If so, " +
              "this portfolio doesn't exist or isn't saved.");
    }
    writeMessage("Loading Portfolio... "
            + System.lineSeparator(), out);
    if (model instanceof IModel2) {
      xmlToPortfolio converter = new xmlToPortfolio(model);
      try {
        converter.convertXmlToPortfolio(fileCheck);
      }
      catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    } else {
      throw new IllegalArgumentException("Model does not support saving portfolios. " +
              "This is a program error. If you see this, the program is flawed.");
    }
  }

  private File getDirectory() {
    File directory = new File(new File("").getAbsolutePath() + "\\src\\savedPortfolios\\");
    if (!directory.exists() || !directory.isDirectory()) {
      directory = new File(new File("").getAbsolutePath() + "/src/savedPortfolios/");
    }
    return directory;
  }

}
