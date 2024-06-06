package controller.command;

public class ReadState<T> {
  private final T result;
  private final InputResult inputResult;

  public ReadState(InputResult inputResult, T result){
    this.inputResult = inputResult;
    this.result = result;
  }

  InputResult getInputResult(){
    return this.inputResult;
  }

  T getResult(){
    return this.result;
  }
}
