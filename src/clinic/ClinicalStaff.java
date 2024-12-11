package clinic;

import java.util.ArrayList;
import java.util.List;

/**
 * Clinical staff is a component of staff who have
 * medical school experience and are real doctors.
 */
public class ClinicalStaff extends Staff implements InterfaceStaff {
  private String npi; // National Provider Identifier
  private String role; // Physician or nurse
  private List<Patient> assignedPatients; // Patients currently assigned to this staff member
  private List<Patient> allAssignedPatients; // All patients ever assigned
  
  /**
   * Every member of ClinicalStaff has a name, education level, and an NPI.
   * @param firstName This is the first name.
   * @param lastName This is the last name.
   * @param educationLevel This is the education level.
   * @param npi This is the NPI code.
   * @param role This is either physician or nurse. 
   * @param assignedPatients Patients currently assigned to this staff member.
   * @param allAssignedPatients All patients ever assigned. 
   */
  public ClinicalStaff(String firstName, String lastName, 
      String educationLevel, String npi, String role) {
    super(firstName, lastName, educationLevel);
    this.npi = npi;
    this.role = role;
    this.assignedPatients = new ArrayList<>();
    this.allAssignedPatients = new ArrayList<>();
  }
  
  /**
   * Gets the npi id number.
   * @return Returns the npi number.
   */
  @Override
  public String getNpi() {
    return npi;
  }
  
  /**
   * Returns the staff's role.
   * @return Staff role.
   */
  @Override
  public String getRole() {
    return role;
  }
  
  /**
   * Sets the staff member's role.
   * @param role Staff role.
   */
  @Override
  public void setRole(String role) {
    this.role = role;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ClinicalStaff{name='").append(getName())
      .append("', isActive=").append(isActive)
      .append(", educationLevel='").append(educationLevel)
      .append("', npi='").append(npi)
      .append("', role='").append(role)
      .append("'}");
    return sb.toString();
  }

  @Override
  public String getName() {
    return ("Physician".equals(role) || "Doctor".equals(role) ? "Dr. " : "Nurse ")
        + firstName + " " + lastName;
  }

  @Override
  public String getDepartment() {
    return "Medical";
  }

  @Override
  public void setDepartment(String department) {
    // Department static as "Medical", modifications not needed currently. 
  }

  @Override
  public String getStaffDetails() {
    StringBuilder sb = new StringBuilder();
    sb.append("ClinicalStaff: ").append(getName())
      .append(", Education Level: ").append(educationLevel)
      .append(", NPI: ").append(npi);
    return sb.toString();
  }

  @Override
  public void assignPatient(Patient patient) {
    if (!assignedPatients.contains(patient)) {
      assignedPatients.add(patient);
    }
    if (!allAssignedPatients.contains(patient)) {
      allAssignedPatients.add(patient);
    }
  }

  @Override
  public void unassignPatient(Patient patient) {
    assignedPatients.remove(patient);
  }

  @Override
  public List<Patient> getAssignedPatients() {
    return new ArrayList<>(assignedPatients); // Copy returned, protect internal list.
  }

  @Override
  public int getTotalAssignedPatientsCount() {
    return allAssignedPatients.size();
  } 
}