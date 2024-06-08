package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

/**
 * Class that reads from the stored CSV files.
 */
public class CSVReader implements IReader {
  private final String filename;

  /**
   * Takes in a filename.
   * @param filename name of file.
   */
  public CSVReader(String filename) {
    this.filename = filename;
  }

  /**
   * gets a readable from the file, formatting it to work with the rest of the program. The
   * output is just like if you were to get it from AlphaVantage directly.
   * @return Readable for program to use.
   */
  @Override
  public Readable getReadable() {
    StringBuilder builder = new StringBuilder();
    try {
      String filePath = new File("").getAbsolutePath() + "\\HW 4\\src\\backupCSV\\";
      Scanner scanner = new Scanner(new FileReader(filePath + filename + ".csv"));
      while (scanner.hasNext()) {
        String first = scanner.next();
        builder.append(first + System.lineSeparator());
      }
    } catch (IOException e) {
      try {
        String filePath = new File("").getAbsolutePath() + "/src/backupCSV/";
        Scanner scanner = new Scanner(new FileReader(filePath + filename + ".csv"));
        while (scanner.hasNext()) {
          String first = scanner.next();
          builder.append(first + System.lineSeparator());
        }
      } catch (IOException g) {
        throw new IllegalStateException("Could not return reader");
      }
    }
    return new StringReader(builder.toString());
  }

}