package controller;

import java.util.HashMap;
import java.util.Map;

import controller.command.ICommand;

public class ControllerImpl implements IController {
  private final Map<String, ICommand> commandMap;
  private final Appendable out;
  private final Readable in;

  public ControllerImpl(Appendable out, Readable in) {
    this.out = out;
    this.in = in;
    commandMap = new HashMap<>();
  }

  @Override
  public void go() {

  }
}
