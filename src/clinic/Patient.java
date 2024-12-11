package clinic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Patient is an extension of person, every person
 * has a first and last name, but a patient has
 * a date of birth, and room to keep track of.
 */

public class Patient extends Person implements InterfacePatient {
  
  private Room assignedRoom;
  private String dob;
  private List<ClinicalStaff> assignedClinicalStaff;
  private ClinicalStaff deactivationApprover;
  private List<InterfaceVisitRecord> visitRecords;
  
  /**
   * Setting up our patient with given name and dob.
   * @param firstName First name.
   * @param lastName Last Name.
   * @param dob Date of Birth.
   */
  
  public Patient(String firstName, String lastName, String dob) {
    super(firstName, lastName);
    this.dob = dob;
    this.assignedRoom = null;
    this.assignedClinicalStaff = new ArrayList<>();
    this.visitRecords = new ArrayList<>();
  }
  
  @Override
  public void assignRoom(Room room) {
    this.assignedRoom = room;
  }
  
  @Override
  public Room getAssignedRoom() {
    return assignedRoom;
  }
  /**
   * Gets the patients date of birth.
   * @return Returns date of birth.
   */
  
  public String getDob() {
    return dob;
  }
  
  /**
   * Adding clinical staff.
   * @param staff This is the staff member.
   */
  public void addClinicalStaff(ClinicalStaff staff) {
    if (!assignedClinicalStaff.contains(staff)) {
      assignedClinicalStaff.add(staff);
    }
  }
  
  /**
   * Gets the list of clinical staff assigned to this patient.
   * @return List of clinical staff assigned to patient.
   */
  public List<ClinicalStaff> getAssignedClinicalStaff() {
    return assignedClinicalStaff;
  }
  
  /**
   * Set the clinical staff who approved de-activation of this patient.
   * @param Sets the deactivation approver.
   */
  public void setDeactivationApprover(ClinicalStaff approver) {
    this.deactivationApprover = approver;
  }
   
  /**
   * Gets the clinical staff member who approved the de-activation of this patient.
   * @return Deactivation approver.
   */
  public ClinicalStaff getDeactivationApprover() {
    return deactivationApprover;
  }
  
  /**
   * Adds a visit record to patient.
   * @param record Creates visit record.
   */
  public void addVisitRecord(InterfaceVisitRecord record) {
    visitRecords.add(record);
  }
  
  /**
   * Gets most recent visit record.
   * @return Recent visit record.
   */
  public InterfaceVisitRecord getLatestVisit() {
    return visitRecords.isEmpty() ? null : visitRecords.get(visitRecords.size() - 1);
  }
  
  /**
   * Get all active visit records.
   * @return Returns visit record.
   */
  public List<InterfaceVisitRecord> getActiveVisits() {
    return visitRecords.stream()
        .filter(v -> !v.isVisitComplete())
        .collect(Collectors.toList());
  }
  
  /**
   * Get all visit records within the last 365 days.
   * @return Last year of visit records.
   */
  public List<InterfaceVisitRecord> getVisitsLastYear() {
    LocalDateTime oneYearAgo = LocalDateTime.now().minusDays(365);
    return visitRecords.stream()
        .filter(v -> v.getVisitTime().isAfter(oneYearAgo))
        .collect(Collectors.toList());
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Patient{")
      .append("assignedRoom=")
      .append(assignedRoom != null ? assignedRoom.getRoomName() : "No room assigned")
      .append(", dob='").append(dob)
      .append("', fullName='").append(getFullName())
      .append("', isActive=").append(isActive)
        .append("}");
    return sb.toString();
  }

  @Override
  public String getName() {
    return firstName + " " + lastName;
  }

  @Override
  public void setName(String name) {
    String[] parts = name.split(" ");
    if (parts.length >= 2) {
      this.firstName = parts[0];
      this.lastName = parts[1];
    }   
  }
  
  /**
   * List of interface visit records.
   * @return Returns visit records. 
   */
  public List<InterfaceVisitRecord> getVisitRecords() {
    return visitRecords;
  }
}
