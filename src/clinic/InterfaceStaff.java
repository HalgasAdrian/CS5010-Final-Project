package clinic;

import java.util.List;

/**
 * Interface setup for our staff.
 */
public interface InterfaceStaff extends InterfacePerson {
  /**
   * Gets the department of staff member.
   * @return returns department. 
   */
  String getDepartment();
  
  /**
   * Allows us to set the staff member's dept.
   * @param department department name.
   */
  void setDepartment(String department);
  
  /**
   * Allows us to show details about staff member.
   * @return Name, dept, id number. 
   */
  String getStaffDetails();
  
  /**
   * Allows us to assign a patient to a staff member.
   * @param patient Patient to be assigned.
   */
  void assignPatient(Patient patient);
  
  /**
   * Allows us to unassign a patient from a staff.
   * @param patient Patient to be unassigned. 
   */
  void unassignPatient(Patient patient);
  
  /**
   * Allows us to see patients assigned to staff member.
   * @return List of assigned patients.
   */
  List<Patient> getAssignedPatients();
  
  /**
   * Allows us to see total amount of patients assigned.
   * @return Number of patients a staff member has.
   */
  int getTotalAssignedPatientsCount();
  
  /**
   * Sets the staff member's role.
   * @param role Staff role.
   */
  public void setRole(String role);
  
  /**
   * Returns the staff's role.
   * @return Staff role.
   */
  public String getRole();
  
  /**
   * Gets the npi id number.
   * @return Returns the npi number.
   */
  public String getNpi();
  
}
