package clinic;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is our Interface for the ClinicModel.
 * It will help with testing and streamlining 
 * the Clinic Model. 
 */

public interface InterfaceClinicModel {
  /**
   * Adds a new patient.
   * @param patient Our new patient.
   */
  void addPatient(Patient patient);
  
  /**
   * Adds a new staff member.
   */
  void addStaff(Staff staff);
  
  /**
   * Adds a new room.
   * @param room Creates a new room.
   */
  void addRoom(Room room);
  
  /**
   * Gets the index of our room.
   * @param index Room number from index.
   * @return Room index.
   */
  Room getRoom(int index);
  
  /**
   * Returns who is inside each room.
   * @param roomIndex Info needed from this room.
   * @return Room details.
   */
  String getRoomDetails(int roomIndex);
  
  /**
   * List for all of the rooms.
   * @return List of all rooms.
   */
  List<Room> getAllRooms();
  
  /**
   * Activates a patient making their visit active.
   * @param patient New active patient.
   */
  void activatePatient(Patient patient);
  
  /**
   * Deactivates a patient and sends them home.
   * @param patient Patient going home.
   * @param approver Doctor sending patient home.
   */
  void deactivatePatient(Patient patient, ClinicalStaff approver);
  
  /**
   * Sets a staff member as active.
   * @param staff Our active staff member. 
   */
  void activateStaff(Staff staff);
  
  /**
   * Deactivates our staff member.
   * @param staff Our deactivated staff member.
   */
  void deactivateStaff(Staff staff);
  
  /**
   * List of all patients at clinic.
   * @return All patients in clinic system.
   */
  List<Patient> getAllPatients();
  
  /**
   * List of all staff members at the clinic.
   * @return All staff members at clinic.
   */
  List<Staff> getAllStaff();
  
  /**
   * Returns the seating chart for our clinic.
   * @return Clinic seating chart.
   */
  String getSeatingChart();
  
  /**
   * Places a patient within a room.
   * @param patient Patient to be placed.
   * @param room Room index of desired patient room.
   */
  void assignPatientToRoom(Patient patient, Room room);
  
  /**
   * Assigns a clinical staff member to a patient.
   * @param staff Staff member to assign to patient.
   * @param patient Patient which will have staff assigned to them.
   */
  void assignClinicalStaffToPatient(ClinicalStaff staff, Patient patient);
  
  /**
   * Finds a staff member using their name.
   * @param firstName First name of staff.
   * @param lastName Last name of staff.
   * @return Returns their full name.
   */
  Staff findStaffByName(String firstName, String lastName);
  
  /**
   * Finds a patient using their full name.
   * @param firstName First name of patient.
   * @param lastName Last name of patient.
   * @return Returns patient full name.
   */
  Patient findPatientByName(String firstName, String lastName);
  
  /**
   * Gets which staff member is assigned to our patient.
   * @param patient Patient who has staff assigned to them.
   * @return Which staff member is assigned to given patient.
   */
  Set<ClinicalStaff> getClinicalStaffAssignedToPatient(Patient patient);
  
  /**
   * Finds the deactivation approver for sending patient home.
   * @param patient Patient sent home.
   * @return Returns which doctor approved the patient to go home.
   */
  ClinicalStaff getDeactivationApprover(Patient patient);
  
  /**
   * Lists the active staff members.
   * @return List of active staff.
   */
  List<ClinicalStaff> listActiveClinicalStaff();
  
  /**
   * Lists inactive patients.
   * @return Shows all patients who were sent home.
   */
  List<Patient> listInactivePatients();
  
  /**
   * Shows the patient history over the last year.
   * @return One year patient history.
   */
  Map<Patient, Long> patientVisitSummaryPastYear();
  
  /**
   * Unassigns clinical staff.
   * @param staff Staff member.
   */
  void unassignClinicalStaff(ClinicalStaff staff);
  
  /**
   * Unassigns a staff member from a patient.
   * @param staff Staff to be unassigned.
   * @param patient Patient with staff assigned to them.
   */
  void unassignClinicalStaffFromPatient(ClinicalStaff staff, Patient patient);
  
  /**
   * Lists the amount of patients a staff member has.
   * @return Number of patients.
   */
  Map<Staff, Integer> listStaffPatientCounts();
  
  /**
   * Lists the active staff member who have patients.
   * @return Active staff members with patients. 
   */
  Map<ClinicalStaff, List<Patient>> listActiveStaffWithPatients();
  
  /**
   * Notify observers of the model to refresh the view.
   */
  void notifyViewUpdate();
  
  /**
   * Loading our clinic data into the program.
   * @param filePath Path of the clinic file data.
   */
  void loadClinicData(String filePath);
  
  /**
   * Clears the saved data.
   */
  void clearRecords();
  
}
