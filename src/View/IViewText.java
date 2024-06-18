package View;

public interface IViewText extends IView {

  void endMessage();

  void printMenu();

  void writeMessage(String message) throws IllegalArgumentException;
}
