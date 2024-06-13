package controller;

import java.io.File;
import java.io.IOException;

/**
 * Interface for parsing files int xml.
 */
public interface IParseXml {
  /**
   * Loads a xml file as a portfolio.
   *
   * @param file portfolio in xml format.
   * @throws IOException xml could not be parsed.
   */
  void convertXmlToPortfolio(File file) throws IOException;
}
