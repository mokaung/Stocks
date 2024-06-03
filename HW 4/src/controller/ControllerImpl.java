package controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerImpl implements IController {
  private final Map<String, ICommand> commandMap;
  private Appendable out;
  private Readable in;

  public ControllerImpl() {
    commandMap = new HashMap<>();
  }

  @Override
  public void go() {

  }
}
