package View;

import java.io.IOException;

/**
 * Interface for displaying the program.
 */
public interface IView {

  void render();
  void showError(String errorMessage)throws IOException;
}