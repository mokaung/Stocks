package model;

import java.io.IOException;

public interface IModel2 extends IModel {
  void savePortfolio(String portFolioName) throws IOException;
}
