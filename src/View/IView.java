package View;

/**
 * Interface for displaying the program.
 */
public interface IView {
  void render();

  String readMessage();

  void addViewListener(IViewListener listener);
}