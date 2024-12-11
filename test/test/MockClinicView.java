package test;

import clinic.InterfaceClinicView;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Mock implementation of the clinic view for testing. 
 */
public class MockClinicView implements InterfaceClinicView {
  private StringBuilder log = new StringBuilder();
  private Queue<String> simulatedInputs = new LinkedList<>();

  @Override
  public void initialize() {
    log.append("initialize\n");
  }

  @Override
  public void show() {
    log.append("show\n");
  }

  @Override
  public void updateView() {
    log.append("updateView\n");
  }

  @Override
  public void setController(ActionListener controller) {
    log.append("setController\n");
  }

  @Override
  public void promptRegisterPatient() {
    log.append("promptRegisterPatient\n");
  }

  @Override
  public void promptAssignRoom() {
    log.append("promptAssignRoom\n");
  }

  @Override
  public void promptAssignStaff() {
    log.append("promptAssignStaff\n");
  }

  @Override
  public void promptShowPatientInfo() {
    log.append("promptShowPatientInfo\n");
  }

  @Override
  public void promptUnassignStaff() {
    log.append("promptUnassignStaff\n");
  }

  @Override
  public void promptSendPatientHome() {
    log.append("promptSendPatientHome\n");
  }

  @Override
  public void promptAddRoom() {
    log.append("promptAddRoom\n");
  }

  @Override
  public void promptRegisterClinicalStaff() {
    log.append("promptRegisterClinicalStaff\n");
  }

  @Override
  public void promptDisplayRoomInfo() {
    log.append("promptDisplayRoomInfo\n");
  }

  @Override
  public void promptDeactivateStaff() {
    log.append("promptDeactivateStaff\n");
  }

  @Override
  public void promptListActiveStaffWithPatients() {
    log.append("promptListActiveStaffWithPatients\n");
  }

  @Override
  public void promptListInactivePatients() {
    log.append("promptListInactivePatients\n");
  }

  @Override
  public void promptPatientVisitSummaryPastYear() {
    log.append("promptPatientVisitSummaryPastYear\n");
  }

  @Override
  public void promptListAllStaffAndPatientCounts() {
    log.append("promptListAllStaffAndPatientCounts\n");
  }

  @Override
  public void showWelcomeScreen() {
    log.append("showWelcomeScreen\n");
  }

  @Override
  public void updateGraphics() {
    log.append("updateGraphics\n");
  }

  @Override
  public void showMessage(String message) {
    log.append("showMessage: ").append(message).append("\n");
  }

  @Override
  public void showError(String errorMessage) {
    log.append("showError: ").append(errorMessage).append("\n");
  }

  @Override
  public String getUserInput(String prompt) {
    log.append("getUserInput: ").append(prompt).append("\n");
    return simulatedInputs.isEmpty() ? "Mock Input" : simulatedInputs.poll();
  }

  @Override
  public void clearRecords() {
    log.append("clearRecords\n");
  }

  @Override
  public void loadClinicData(String filePath) {
    log.append("loadClinicData: ").append(filePath).append("\n");
  }
  
  /**
   * Sets multiple simulated user input for testing purposes.
   * 
   * @param input The input to simulate.
   */
  public void setSimulatedUserInputs(String... inputs) {
    for (String input : inputs) {
      simulatedInputs.add(input);
    }
  }
  
  /**
   * Gets the log of all recorded method calls.
   * 
   * @return The log as a string.
   */
  public String getLog() {
    return log.toString();
  }
  
  /**
   * Clears the log of recorded method calls.
   */
  public void clearLog() {
    log.setLength(0);
  }
}
