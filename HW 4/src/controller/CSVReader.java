package controller;

import java.io.FileReader;

public class CSVReader implements IReader {
  private final String filename;

  public CSVReader(String fileName, String filename) {
    this.filename = filename;
  }

  @Override
  public Readable getReadable() {
    try {
      return new FileReader(filename);
    } catch (Exception e) {
      throw new IllegalArgumentException("d");
    }
  }

  public static interface IReader {
    Readable getReadable();
  }
}

//      Scanner scanner = new Scanner(fileReader);
//      while (scanner.hasNextLine()) {
//        int first = scanner.nextInt();
//        scanner.next();
//        int sec = scanner.nextInt();
//        StringBuilder builder = new StringBuilder();
//        builder.append(first).append(sec);
