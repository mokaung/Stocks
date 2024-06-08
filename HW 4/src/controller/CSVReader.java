package controller;

import java.io.BufferedReader;
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

  public CSVReader(String filename) {
    this.filename = filename;
  }

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
        System.out.println(filePath + filename + ".csv");
        String first = "";
        while (first != null) {
            System.out.println(first);
            builder.append(first + System.lineSeparator());
        Scanner scanner = new Scanner(new FileReader(filePath + filename + ".csv"));
        while (scanner.hasNext()) {
          first = scanner.next();
          builder.append(first + System.lineSeparator());
        }
        }
      } catch (IOException g) {
        throw new IllegalStateException("Could not return reader");
      }
    }
    return new StringReader(builder.toString());
  }

}