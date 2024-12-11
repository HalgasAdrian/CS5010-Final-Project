package clinic;

import javax.swing.SwingUtilities;

/**
 * Run the clinic application interactively on the console.
 */
public class Main {
  
  /**
   * Constructor for our main.
   * @param args Helps us run our Controller and Model.
   */
  public static void main(String[] args) {

    // Initialize the model
    ClinicModel model = new ClinicModel();
    
    // Create the GUI view
    ClinicView view = new ClinicView(model);
    
    // Set the view in the model
    model.setView(view);

    // Create the controller
    ClinicGraphicalController controller = new ClinicGraphicalController(model, view);
    
    // Launch the GUI on the Event Dispatch Thread
    SwingUtilities.invokeLater(() -> {
      view.setController(controller);
      view.initialize(); // Initialize and display the GUI
    });
  }
}