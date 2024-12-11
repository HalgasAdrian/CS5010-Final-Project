package clinic;

/**
 * Interface for the new GUI clinic view.
 */
public interface InterfaceClinicView extends InterfaceView {
  
  /**
   * Displays the welcome screen.
   */
  void showWelcomeScreen();
  
  /**
   * Updates the graphical representation of the clinic.
   */
  void updateGraphics();
  
  /**
   * Displays the message to the user.
   * @param message Message to user.
   */
  void showMessage(String message);
  
  /**
   * Displays error message to the user.
   * @param errorMessage Error message.
   */
  void showError(String errorMessage);
  
  /**
   * Requests user input from the view.
   * @param prompt The message prompting the user for input.
   * @return The user input as a String.
   */
  String getUserInput(String prompt);
  
  /**
   * Clears all records from memory and updates the view.
   */
  void clearRecords();
  
  /**
   * Loads a clinic layout or date file. 
   * @param filePath Path to the file to be loaded. 
   */
  void loadClinicData(String filePath);
  
  

}
