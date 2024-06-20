package view;

/**
 * Interface for displaying the program.
 */
public interface IView {
  /**
   * Adds a listener to the view.
   *
   * @param listener IViewListener that represents different views.
   */
  void addViewListener(IViewListener listener);

  /**
   * Requests the focus.
   */
  void requestFocus();

  /**
   * Displays the error message.
   *
   * @param errorMessage error that happened.
   */
  void showError(String errorMessage);

  /**
   * Initializes the program.
   *
   * @param visible if the GUI is visible or not.
   */
  void render(boolean visible);
}