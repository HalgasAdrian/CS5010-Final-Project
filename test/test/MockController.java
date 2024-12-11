package test;

import clinic.ClinicGraphicalController;
import clinic.ClinicalStaff;
import clinic.InterfaceClinicModel;
import clinic.InterfaceClinicView;
import clinic.Patient;
import clinic.Room;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

/**
 * Mock implementation of the controller for testing. 
 */
public class MockController extends ClinicGraphicalController {
  private final StringBuilder log;
  
  /**
   * Mock controller implementation for full MVC.
   * @param model Current model being used.
   * @param view Current view being used. 
   */
  public MockController(InterfaceClinicModel model, InterfaceClinicView view) {
    super(model, view);
    this.log = new StringBuilder();
  }
  
  @Override
  public void clearRecords() {
    log.append("clearRecords called");
  }
  
  @Override
  public void registerNewPatient(String firstName, String lastName, String dob) {
    log.append("registerNewPatient Name: ").append(firstName).append(" ")
    .append(lastName).append(", DOB: ").append(dob);
  }
  
  @Override
  public void assignRoom(Patient patient, Room room) {
    log.append("assignRoom Patient: ").append(patient.getFullName())
    .append(", Room: ").append(room.getRoomName());
  }
  
  @Override
  public void assignStaff(Patient patient, ClinicalStaff staff) {
    log.append("assignStaff Patient: ").append(patient.getFullName())
    .append(", Staff: ").append(staff.getFullName());
  }
  
  @Override
  public void unassignStaff(Patient patient, ClinicalStaff staff) {
    log.append("unassignStaff\n")
    .append("Patient: ").append(patient.getFullName())
    .append(", Staff: ").append(staff.getFullName()).append("\n");
  }
  
  @Override
  public void loadClinicData(String filePath) {
    log.append("loadClinicData called with: ").append(filePath);
  }
  
  @Override
  public void addInitialVisitRecord(String firstName, String lastName, 
      LocalDateTime visitTime, String chiefComplaint, double temperature) {
    log.append("addInitialVisitRecord called with: ")
    .append("Patient: ").append(firstName).append(" ").append(lastName)
    .append(", Time: ").append(visitTime)
    .append(", Complaint: ").append(chiefComplaint)
    .append(", Temperature: ").append(temperature).append("\n");
  }
  
  @Override
  public void deactivateStaff(String firstName, String lastName) {
    log.append("deactivateStaff called with: ")
    .append(firstName).append(" ").append(lastName).append("\n");  
  }
  
  @Override
  public void listActiveStaffWithPatients() {
    log.append("listActiveStaffWithPatientscalled\n");
  }
  
  @Override
  public void listInactivePatients() {
    log.append("listInactivePatients called\n"); 
  }
  
  @Override
  public void patientVisitSummaryPastYear() {
    log.append("patientVisitSummaryPastYear");
  }
  
  @Override
  public void listAllStaffAndPatientCounts() {
    log.append("listAllStaffAndPatientCounts called\n");
  }
  
  @Override
  public void displayRoomInfo(int roomIndex) {
    log.append("displayRoomInfo called with index: ").append(roomIndex).append("\n");
  }
  
  @Override
  public void execute() {
    log.append("execute called\n");
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    log.append("actionPerformed: ").append(e.getActionCommand()).append("\n");
  }
  
  /**
   * Setup for the log of actions.
   * @return Action log.
   */
  public String getLog() {
    return log.toString();
  }

}
