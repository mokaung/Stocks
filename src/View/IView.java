package View;

/**
 * Interface for displaying the program.
 */
public interface IView {

  void requestFocus();
  void addViewListener(IViewListener listener);
}