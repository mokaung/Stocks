import java.util.Date;

import model.IModel;

public class Modelmpl implements IModel {
  private final Date time;
  private final double open;
  private final double high;
  private final double low;
  private final double close;
  private final int volume;

  public Modelmpl(Date time, double open, double high, double low, double close, int volume) {
    this.time = time;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }


  @Override
  public void populate(Readable readable) {

  }

  public Date getTime() {
    return time;
  }

  public double getOpen() {
    return open;
  }

  public double getHigh() {
    return high;
  }

  public double getLow() {
    return low;
  }

  public double getClose() {
    return close;
  }

  public int getVolume() {
    return volume;
  }
}
