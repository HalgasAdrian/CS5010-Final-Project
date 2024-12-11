package clinic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;

/**
 * Graphical Controller for the Clinic Management System.
 */
public class ClinicGraphicalController implements ClinicController, ActionListener {
  private InterfaceClinicModel model;
  private InterfaceView view;
  
  /**
   * Constructs the graphical controller.
   * @param model The clinic model to interact with.
   * @param view The clinic view to interact with.
   */
  public ClinicGraphicalController(InterfaceClinicModel model, InterfaceClinicView view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    
    switch (command) {
      case "Register Patient":
        view.promptRegisterPatient();
        break;
      case "Assign Room":
        view.promptAssignRoom();
        break;
      case "Assign Staff":
        view.promptAssignStaff();
        break;
      case "Show Patient Info":
        view.promptShowPatientInfo();
        break;
      case "Unassign Staff":
        view.promptUnassignStaff();
        break;
      case "Send Patient Home":
        view.promptSendPatientHome();
        break;
      case "Add Room":
        view.promptAddRoom();
        break;
      case "Register Clinical Staff":
        view.promptRegisterClinicalStaff();
        break;
      case "Display Room Info":
        view.promptDisplayRoomInfo();
        break;
      case "Deactivate Staff":
        view.promptDeactivateStaff();
        break;
      case "List Active Staff with Patients":
        listActiveStaffWithPatients();
        break;
      case "List Inactive Patients":
        listInactivePatients();
        break;
      case "Patient Visit Summary":
        patientVisitSummaryPastYear();
        break;
      case "List All Staff and Patient Counts":
        listAllStaffAndPatientCounts();
        break;
      default:
        view.showMessage("Unknown command: " + command);
        break;  
    }
  }
  
  @Override
  public void execute() {
    if (view == null || model == null) {
      throw new IllegalStateException("View and Model must be set before executing.");
    }
    SwingUtilities.invokeLater(() -> {
      view.initialize();
      view.show();
    });  
  }
  
  @Override
  public void setModel(InterfaceClinicModel model) {
    this.model = model;    
  }
  
  @Override
  public void setView(InterfaceView view) {
    this.view = view;
    this.view.setController(this);    
  }
  
  @Override
  public void registerNewClinicalStaff(String firstName, String lastName, 
      String education, String npi, String role) {
    Staff staff;
    if (!npi.isEmpty()) {
      staff = new ClinicalStaff(firstName, lastName, education, npi, role);
      
    } else {
      staff = new NonClinicalStaff(firstName, lastName, education, "Basic CPR", "Staff");
    }
    model.addStaff(staff);
    view.updateGraphics();
    view.showMessage("Clinical staff registered: " + staff.getFullName());
  }
  
  @Override
  public void displayRoomInfo(int roomIndex) {
    Room room = model.getRoom(roomIndex);
    if (room != null) {
      view.showMessage(room.getRoomInfo(model));
    } else {
      view.showError("Room not found.");
    }
  }
  
  @Override
  public void deactivateStaff(String firstName, String lastName) {
    Staff staff = model.findStaffByName(firstName, lastName);
    if (staff != null) {
      model.deactivateStaff(staff);
      view.updateGraphics();
      view.showMessage("Staff deactivated: " + staff.getFullName());  
    } else {
      view.showError("Staff not found.");     
    }  
  }
  
  @Override
  public void listActiveStaffWithPatients() {
    Map<ClinicalStaff, List<Patient>> activeStaffWithPatients = model.listActiveStaffWithPatients();
    if (activeStaffWithPatients.isEmpty()) {
      view.showMessage("No active staff with assigned patients.");
    } else {
      StringBuilder sb = new StringBuilder("Active Staff with Patients:\n");
      activeStaffWithPatients.forEach((staff, patients) -> {
        sb.append(staff.getFullName()).append(": ")
        .append(patients.stream().map(Patient::getFullName).collect(Collectors.joining(", ")))
        .append("\n");
      });
      view.showMessage(sb.toString());
    } 
  }
  
  @Override
  public void listInactivePatients() {
    List<Patient> inactivePatients = model.listInactivePatients();
    if (inactivePatients.isEmpty()) {
      view.showMessage("No inactive patients.");
    } else {
      StringBuilder sb = new StringBuilder("Inactive Patients:\n");
      inactivePatients.forEach(patient -> sb.append(patient.getFullName()).append("\n"));
      view.showMessage(sb.toString());  
    } 
  }
  
  @Override
  public void patientVisitSummaryPastYear() {
    Map<Patient, Long> summary = model.patientVisitSummaryPastYear();
    if (summary.isEmpty()) {
      view.showMessage("No visits in the past year.");   
    } else {
      StringBuilder sb = new StringBuilder("Patient Visit Summary (Past Year):\n");
      summary.forEach((patient, count) -> 
          sb.append(patient.getFullName()).append(": ").append(count).append("\n"));
      view.showMessage(sb.toString());      
    } 
  }
  
  @Override
  public void listAllStaffAndPatientCounts() {
    Map<Staff, Integer> staffPatientCounts = model.listStaffPatientCounts();
    if (staffPatientCounts.isEmpty()) {
      view.showMessage("No staff assigned to any patients.");      
    } else {
      StringBuilder sb = new StringBuilder("Staff and Patient Counts:\n");
      staffPatientCounts.forEach((staff, count) -> 
          sb.append(staff.getFullName()).append(": ").append(count).append("\n"));
      view.showMessage(sb.toString());
    }
  }
  
  @Override
  public void assignRoom(Patient patient, Room room) {
    model.assignPatientToRoom(patient, room);
    view.updateView();
    view.showMessage("Assigned " + patient.getFullName() 
        + " to room " + room.getRoomName());
  }
  
  @Override
  public void assignStaff(Patient patient, ClinicalStaff staff) {
    model.assignClinicalStaffToPatient(staff, patient);
    view.updateView();
    view.showMessage("Assigned " + staff.getFullName() 
        + " to patient " + patient.getFullName());
  }
  
  @Override
  public void unassignStaff(Patient patient, ClinicalStaff staff) {
    model.unassignClinicalStaffFromPatient(staff, patient);
    view.updateView();
    view.showMessage("Unassigned " + staff.getFullName() 
        + " from patient " + patient.getFullName());
  }
  
  @Override
  public void loadClinicData(String filePath) {
    try {
      model.loadClinicData(filePath);
      view.updateView();
      view.showMessage("Clinic data loaded successfully.");
    } catch (IllegalStateException e) {
      view.showError("Failed to load clinic data: " + e.getMessage());
    }
  }
  
  @Override
  public void clearRecords() {
    try {
      model.clearRecords();
      view.updateGraphics();
      view.showMessage("All records cleared.");
    } catch (IllegalStateException e) {
      view.showError("Failed to clear records: " + e.getMessage());
    }   
  }
  
  @Override
  public void registerNewPatient(String firstName, String lastName, String dob) {
    Patient patient = new Patient(firstName, lastName, dob);
    model.addPatient(patient);
    model.assignPatientToRoom(patient, model.getRoom(0)); // Assign to waiting room by default
    view.updateView();
    view.showMessage("Patient registered: " + patient.getFullName());
  }
  
  @Override
  public void addInitialVisitRecord(String firstName, String lastName, 
      LocalDateTime visitTime, String chiefComplaint, double temperature) {
    Patient patient = model.findPatientByName(firstName, lastName);
    if (patient == null) {
      view.showError("Patient not found.");
      return;
    }
    VisitRecord visitRecord = new VisitRecord(visitTime, chiefComplaint, temperature);
    patient.addVisitRecord(visitRecord);
    view.updateView();
    view.showMessage("Initial visit record added for " + patient.getFullName());
  }
}
