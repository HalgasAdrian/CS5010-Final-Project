package clinic;

import java.awt.event.ActionListener;

/**
 * Represents the interface for the graphical view.
 */
public interface InterfaceView {
  
  /**
   * Initializes the graphical view.
   */
  void initialize();
  
  /**
   * Displays graphical view to the user.
   */
  void show();
  
  /**
   * Updates graphical representation.
   */
  void updateView();
  
  /**
   * Sets the controller for view to handle user interaction.
   * @param controller Controller for the view.
   */
  void setController(ActionListener controller);
  
  /**
   * Displays a welcome screen with credits and application details.
   */
  void showWelcomeScreen();
  
  /**
   * Prompts the user to register a new patient.
   */
  void promptRegisterPatient();
  
  /**
   * Prompts the user to assign a room to a patient.
   */
  void promptAssignRoom();
  
  /**
   * Prompts the user to assign a clinical staff member to a patient.
   */
  void promptAssignStaff();
  
  /**
   * Prompts the user to view information about a specific patient.
   */
  void promptShowPatientInfo();
  
  /**
   * Prompts the user to un-assign a clinical staff member from a patient.
   */
  void promptUnassignStaff();
  
  /**
   * Prompts the user to send a patient home.
   */
  void promptSendPatientHome();
  
  /**
   * Prompts the user to add a new room.
   */
  void promptAddRoom();
  
  /**
   * Displays a message to the user.
   * @param message Message to display.
   */
  void showMessage(String message);
  
  /**
   * Allows for graphical updates.
   */
  void updateGraphics();
  
  /**
   * Shows error message.
   * @param errorMessage Error message to display.
   */
  void showError(String errorMessage);
  
  /**
   * Prompts user to register new clinical staff.
   */
  public void promptRegisterClinicalStaff();
  
  /**
   * Prompts user to display room info.
   */
  public void promptDisplayRoomInfo();
  
  /**
   * Prompts user to deactivate a staff member.
   */
  public void promptDeactivateStaff();
  
  /**
   * Prompts user to list active staff members with patients.
   */
  public void promptListActiveStaffWithPatients();
  
  /**
   * Prompts user to list inactive patients.
   */
  public void promptListInactivePatients();
  
  /**
   * Prompts user to show patient visit summary for the past year.
   */
  public void promptPatientVisitSummaryPastYear();
  
  /**
   * Prompts user to list all staff and patient counts.
   */
  public void promptListAllStaffAndPatientCounts();
  
}
