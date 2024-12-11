package clinic;

import java.time.LocalDateTime;

/**
 * Represents a Controller for the clinic. Handles user inputs by
 * executing them using the model. Conveys data to user.
 * Provides methods for managing patients, staff, rooms, and visit records. 
 */
public interface ClinicController {
  
  /**
   * Executes the clinic system, initializing the view and starting the application.
   * This method is the entry point for the graphical interface.
   */
  void execute();
  
  /**
   * Sets the model that the controller will interact with.
   * The model provides the data and business logic for the clinic.
   * 
   * @param model The Clinic Model.
   */
  void setModel(InterfaceClinicModel model);
  
  /**
   * Sets the view that the controller will interact with.
   * The view provides the graphical interface for the clinic system.
   * 
   * @param view The graphical view.
   */
  void setView(InterfaceView view);
  
  /**
   * Registers a new patient in the clinic.
   * New patients are automatically assigned to the waiting room.
   * 
   * @param firstName The first name of the patient.
   * @param lastName The last name of the patient.
   * @param dob The date of birth of the patient in the format "YYYY-MM-DD".
   */
  void registerNewPatient(String firstName, String lastName, String dob);

  /**
   * Registers a new clinical staff member in the clinic.
   * Clinical staff are assigned roles such as "Doctor" or "Nurse".
   * 
   * @param firstName The first name of the staff member.
   * @param lastName The last name of the staff member.
   * @param education The education details of the staff member.
   * @param npi The National Provider Identifier (NPI) of the staff member, if applicable.
   * @param role The role of the staff member (e.g., "Doctor" or "Nurse").
   */
  void registerNewClinicalStaff(String firstName, String lastName, 
      String education, String npi, String role);
  
  /**
   * Adds an initial visit record for an existing patient.
   * 
   * @param firstName The first name of the patient.
   * @param lastName The last name of the patient.
   * @param visitTime The date and time of the visit.
   * @param chiefComplaint The chief complaint reported by the patient.
   * @param temperature The body temperature of the patient during the visit.
   */
  void addInitialVisitRecord(String firstName, String lastName, 
      LocalDateTime visitTime, String chiefComplaint, double temperature);

  /**
   * Assigns a patient to a specific room in the clinic.
   * The new room assignment supersedes any previous room assignment.
   * 
   * @param patient The patient to be assigned.
   * @param room The room to which the patient is assigned.
   */
  void assignRoom(Patient patient, Room room);

  /**
   * Assigns a clinical staff member to a patient.
   * Multiple staff members can be assigned to the same patient.
   * 
   * @param patient The patient to whom the staff is assigned.
   * @param staff The clinical staff to be assigned.
   */
  void assignStaff(Patient patient, ClinicalStaff staff);

  /**
   * Unassigns a clinical staff member from a patient.
   * 
   * @param patient The patient from whom the staff is unassigned.
   * @param staff The clinical staff to be unassigned.
   */
  void unassignStaff(Patient patient, ClinicalStaff staff);

  /**
   * Deactivates a staff member in the clinic.
   * This typically indicates that the staff member is no longer active in the system.
   * 
   * @param firstName The first name of the staff member.
   * @param lastName The last name of the staff member.
   */
  void deactivateStaff(String firstName, String lastName);

  /**
   * Displays detailed information about a specific room in the clinic.
   * 
   * @param roomIndex The index of the room to display information for.
   */
  void displayRoomInfo(int roomIndex);
  
  /**
   * Lists all active clinical staff members and the patients assigned to them.
   */
  void listActiveStaffWithPatients();

  /**
   * Lists all inactive patients in the clinic.
   * A patient is considered inactive if they have no visits in the last 365 days.
   */
  void listInactivePatients();

  /**
   * Displays a summary of patient visits over the past year.
   * The summary includes the number of visits for each patient.
   */
  void patientVisitSummaryPastYear();

  /**
   * Lists all staff members along with the number of patients assigned to each.
   */
  void listAllStaffAndPatientCounts();

  /**
   * Loads clinic data from a file.
   * This can be used to initialize or update the clinic's records.
   * 
   * @param filePath The path to the file containing the clinic data.
   */
  void loadClinicData(String filePath);

  /**
   * Clears all clinic records, including patients, staff, and rooms.
   * This operation cannot be undone.
   */
  void clearRecords();
  
}
