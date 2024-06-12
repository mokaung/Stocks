package controller.command;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import model.IModel;

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
    writeMessage("Which portfolio would you like to load? "
            + System.lineSeparator(), out);
    File directory = getDirectory();
    if (!directory.exists() || !directory.isDirectory()) {
      throw new IllegalArgumentException("Please make sure the folder 'savedPortfolios' exists.");
    }
    File[] files = directory.listFiles();
    if (files != null) {
      for (File file: files) {
        if (file.isFile() && file.getName().endsWith(".txt")) {
          writeMessage("- " + file.getName(), out);
        }
      }
    }
    else {
      throw new IllegalArgumentException("Please make sure you have saved portfolios."
              + " The directory is empty.");
    }
    String name = sc.next();
    File fileCheck = new File(directory, name);
    if (!fileCheck.exists() || !fileCheck.isFile()) {
      throw new IllegalArgumentException("This portfolio doesn't exist or isn't saved.");
    }
    writeMessage("Loading Portfolio... "
            + System.lineSeparator(), out);
    try {
      model.loadPortfolio(fileCheck);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  private File getDirectory() {
    File directory = new File(new File("").getAbsolutePath() + "\\HW 4\\src\\savedPortfolios\\");
    if (!directory.exists() || !directory.isDirectory()) {
      directory = new File(new File("").getAbsolutePath() + "/src/savedPortfolios/");
    }
    return directory;
  }

}
