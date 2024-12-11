package test;

import clinic.ClinicalStaff;
import clinic.InterfaceClinicModel;
import clinic.Patient;
import clinic.Room;
import clinic.Staff;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Setting up the mock model for testing.
 */
public class MockClinicModel implements InterfaceClinicModel {
  private List<Patient> patients = new ArrayList<>();
  private List<Staff> staffMembers = new ArrayList<>();
  private List<Room> rooms = new ArrayList<>();
  private boolean graphicsUpdated = false;

  @Override
  public void addPatient(Patient patient) {
    patients.add(patient);
  }

  @Override
  public void addStaff(Staff staff) {
    staffMembers.add(staff);
  }

  @Override
  public void addRoom(Room room) {
    rooms.add(room);
  }

  @Override
  public Room getRoom(int index) {
    if (index >= 0 && index < rooms.size()) {
      return rooms.get(index);      
    }
    return null;
  }

  @Override
  public String getRoomDetails(int roomIndex) {
    Room room = getRoom(roomIndex);
    return room != null ? room.toString() : "Room not found";
  }

  @Override
  public List<Room> getAllRooms() {
    return rooms;
  }

  @Override
  public void activatePatient(Patient patient) {
    patient.activate();
  }

  @Override
  public void deactivatePatient(Patient patient, ClinicalStaff approver) {
    patient.deactivate();
  }

  @Override
  public void activateStaff(Staff staff) {
    staff.activate();
  }

  @Override
  public void deactivateStaff(Staff staff) {
    staff.deactivate();
  }

  @Override
  public List<Patient> getAllPatients() {
    return patients;
  }

  @Override
  public List<Staff> getAllStaff() {
    return staffMembers;
  }

  @Override
  public String getSeatingChart() {
    return String.join("\n", rooms.stream().map(Room::toString).toArray(String[]::new));
  }

  @Override
  public void assignPatientToRoom(Patient patient, Room room) {
    room.assignPatient(patient);
    patient.assignRoom(room);
  }

  @Override
  public void assignClinicalStaffToPatient(ClinicalStaff staff, Patient patient) {
    // TODO Auto-generated method stub

  }

  @Override
  public Staff findStaffByName(String firstName, String lastName) {
    return staffMembers.stream()
        .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Patient findPatientByName(String firstName, String lastName) {
    return patients.stream()
        .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Set<ClinicalStaff> getClinicalStaffAssignedToPatient(Patient patient) {
    return new HashSet<>(); 
  }

  @Override
  public ClinicalStaff getDeactivationApprover(Patient patient) {
    return patient.getDeactivationApprover();
  }

  @Override
  public List<ClinicalStaff> listActiveClinicalStaff() {
    return staffMembers.stream()
        .filter(ClinicalStaff.class::isInstance)
        .map(ClinicalStaff.class::cast)
        .filter(Staff::isActive)
        .toList();
  }

  @Override
  public List<Patient> listInactivePatients() {
    return patients.stream()
        .filter(p -> !p.isActive())
        .toList();
  }

  @Override
  public Map<Patient, Long> patientVisitSummaryPastYear() {
    Map<Patient, Long> summary = new LinkedHashMap<>();
    patients.forEach(p -> {
      long visitCount = p.getVisitsLastYear().size();
      summary.put(p, visitCount);
    });
    return summary;
  }

  @Override
  public void unassignClinicalStaff(ClinicalStaff staff) {
    patients.forEach(patient -> patient.getAssignedClinicalStaff().remove(staff));
    graphicsUpdated = true;    
  }

  @Override
  public void unassignClinicalStaffFromPatient(ClinicalStaff staff, Patient patient) {
    patient.getAssignedClinicalStaff().remove(staff);
    graphicsUpdated = true;    
  }

  @Override
  public Map<Staff, Integer> listStaffPatientCounts() {
    Map<Staff, Integer> counts = new HashMap<>();
    for (Staff staff : staffMembers) {
      if (staff instanceof ClinicalStaff) {
        ClinicalStaff clinicalStaff = (ClinicalStaff) staff;
        counts.put(clinicalStaff, clinicalStaff.getAssignedPatients().size());     
      } else {
        counts.put(staff, 0);    
      }
    }
    return counts;
  }

  @Override
  public Map<ClinicalStaff, List<Patient>> listActiveStaffWithPatients() {
    Map<ClinicalStaff, List<Patient>> result = new HashMap<>();
    for (Patient patient : patients) {
      for (ClinicalStaff staff : patient.getAssignedClinicalStaff()) {
        result.computeIfAbsent(staff, k -> new ArrayList<>()).add(patient);
      }
    }
    return result;
  }

  @Override
  public void notifyViewUpdate() {
    graphicsUpdated = true;    
  }

  @Override
  public void loadClinicData(String filePath) {
    System.out.println("Mock loading clinic data from: " + filePath);
    graphicsUpdated = true;    
  }

  @Override
  public void clearRecords() {
    patients.clear();
    staffMembers.clear();
    rooms.clear();
    graphicsUpdated = true;    
  }
  
  /**
   * Added a check for if the graphics have been updated.
   * @return Graphics updated status.
   */
  public boolean isGraphicsUpdated() {
    return graphicsUpdated;
  }
  
  /**
   * Added a reset function for the graphics update check.
   */
  public void resetGraphicsUpdated() {
    graphicsUpdated = false;
  }

}
