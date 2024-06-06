package controller;

import java.io.FileNotFoundException;

/**
 * Interface for Readable. Each implementation of this interface has a different way of accessing
 * stock data for the program to save. ALlows for future implementation of more ways to access
 * stock data,
 */
public interface IReader {
  Readable getReadable() throws FileNotFoundException;
}
