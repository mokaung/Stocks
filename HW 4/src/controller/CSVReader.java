package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CSVReader implements IReader {
  private final String filename;

  public CSVReader(String fileName) {
  }

  @Override
  public Readable getReadable() {
    try {
      FileReader fileReader = new FileReader(filename);
      Scanner scanner = new Scanner(fileReader);
      while (scanner.hasNextLine()) {
        int first = scanner.nextInt();
        scanner.next();
        int sec = scanner.nextInt();
        StringBuilder builder = new StringBuilder();
        builder.append(first).append(sec);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
