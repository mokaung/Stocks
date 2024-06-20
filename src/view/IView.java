package view;

/**
 * Interface for displaying the program.
 */
public interface IView {
  void addViewListener(IViewListener listener);

  void requestFocus();

  void showError(String errorMessage);

  void render(boolean b);
}