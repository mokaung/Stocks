package controller;

public class AlphaVantageStreamReader implements IReader {
  Appendable appendable;

  public AlphaVantageStreamReader() {

  }
  // given to model. model can use differnt reader classes so you can read from the web or csv
  // this class does the reading

  public Readable getReadable() {
    return null;
  }
}
