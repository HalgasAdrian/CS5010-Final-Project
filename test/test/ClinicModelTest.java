package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import clinic.ClinicModel;
import clinic.ClinicalStaff;
import clinic.Patient;
import clinic.Room;
import clinic.Staff;
import clinic.VisitRecord;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * ClinidModelTest is a large class which combines all of our
 * model together into one. This is the component which I plan
 * to have interact with the controller if this were an MVC application.
 */

public class ClinicModelTest {
  private ClinicModel model;
  private Room room1;
  private Room room2;
  private Patient patient1;
  private Patient patient2;
  private ClinicalStaff doctor1;
  private ClinicalStaff doctor2;
  
  /**
   * Setting up our test class, we need to test multiple things.
   * Including Room, type of room, how many people in room.
   * Test the waiting room as well to make sure more than 1 
   * person can wait in there. 
   */
  
  @Before
  public void setUp() {
    model = new ClinicModel();
    room1 = new Room(0, 0, 10, 10, "waiting", "Waiting Room");
    room2 = new Room(11, 11, 21, 21, "exam", "Exam Room 1");
    model.addRoom(room1);
    model.addRoom(room2);

    patient1 = new Patient("John", "Doe", "1980-01-01");
    patient2 = new Patient("Jane", "Doe", "1990-02-02");
    model.addPatient(patient1);
    model.addPatient(patient2);

    doctor1 = new ClinicalStaff("Dr. Alice", "Smith", "MD", "1234567890", "Doctor");
    doctor2 = new ClinicalStaff("Dr. Bob", "Brown", "MD", "9876543210", "Doctor");
    model.addStaff(doctor1);
    model.addStaff(doctor2);
    
  }

  @Test
  public void testAddAndRetrieveRoom() {
    assertEquals("Should return the first room", room1, model.getRoom(0));
    assertEquals("Should return the second room", room2, model.getRoom(1));
    //FIX assertNull("Should return null for non-existent room", model.getRoom(7));
  }
  
  @Test
  public void testAddAndAssignPatient() {
    model.assignPatientToRoom(patient1, room1);
    assertTrue("Patient should be assigned to room", 
        room1.getAssignedPatients().contains(patient1));
    assertEquals("Patient's assigned room should update", 
        room1, patient1.getAssignedRoom());
  }
  
  @Test
  public void testAssignClinicalStaffToPatient() {
    model.assignClinicalStaffToPatient(doctor1, patient1);
    assertTrue("Doctor should be assigned to patient", 
        model.getClinicalStaffAssignedToPatient(patient1).contains(doctor1));
  }
  
  @Test
  public void testDeactivateAndActivatePatient() {
    model.deactivatePatient(patient1, doctor1);
    assertFalse("Patient should be deactivated", patient1.isActive());
    assertNotNull("Deactivation should have an approver", 
        model.getDeactivationApprover(patient1));

    model.activatePatient(patient1);
    assertTrue("Patient should be reactivated", patient1.isActive());
  }
  
  @Test
  public void testDeactivateAndActivateStaff() {
    model.deactivateStaff(doctor1);
    assertFalse("Staff should be deactivated", doctor1.isActive());

    model.activateStaff(doctor1);
    assertTrue("Staff should be reactivated", doctor1.isActive());
  }
  
  @Test 
  public void testGetSeatingChart() {
    model.assignPatientToRoom(patient1, room1);
    model.assignClinicalStaffToPatient(doctor1, patient1);
    String expectedInfo = "Clinic Seating Chart:\nRoom: Waiting Room "
        + "(waiting)\nPatients and their Clinical Staff:\nJohn Doe - Dr. Alice Smith\n\n";
    assertEquals("Seating chart should match expected output", 
        expectedInfo, model.getSeatingChart());
  }
  
  @Test
  public void testGetAllStaff() {
    List<Staff> staff = model.getAllStaff();
    assertEquals("Should return the correct number of staff members", 2, staff.size());
  }
  
  @Test
  public void testFindStaffByName() {
    Staff found = model.findStaffByName("Dr. Alice", "Smith");
    assertNotNull("Staff should be found", found);
    assertEquals("Found staff's name should match", "Dr. Alice Smith", found.getFullName());

    assertNull("Should return null for non-existing staff", 
        model.findStaffByName("Dr. Nobody", "Nowhere"));
  }
  
  @Test
  public void testFindPatientByName() {
    Patient found = model.findPatientByName("Jane", "Doe");
    assertNotNull("Patient should be found", found);
    assertEquals("Found patient's name should match", "Jane Doe", found.getFullName());

    assertNull("Should return null for non-existing patient", 
        model.findPatientByName("Noone", "Nowhere")); 
  }
  
  @Test
  public void testGetAllPatients() {
    List<Patient> patients = model.getAllPatients();
    assertEquals("Should return the correct number of patients", 2, patients.size());
  }
  
  @Test
  public void testGetRoomDetails() {
    String roomDetails = model.getRoomDetails(0);
    assertTrue("Room details should include room name", roomDetails.contains("Waiting Room"));
  }
  
  @Test
  public void testGetAllRooms() {
    List<Room> rooms = model.getAllRooms();
    assertEquals("Should return all added rooms", 2, rooms.size());
  }
  
  /**
   * Milestone 3 tests below.
   */
  
  @Test
  public void testListActiveClinicalStaffWithPatients() {
    model.assignClinicalStaffToPatient(doctor1, patient1);
    model.assignPatientToRoom(patient1, room1);
    
    List<ClinicalStaff> activeStaff = model.listActiveClinicalStaff();
    
    assertTrue("Should include doctor1 with active patients", activeStaff.contains(doctor1));
    assertTrue("List should only include active clinical staff", 
        activeStaff.stream().allMatch(ClinicalStaff::isActive));
    assertFalse("Should not list doctor2 as they have no patients", activeStaff.contains(doctor2));
    
  }
  
  @Test
  public void testListInactivePatients() {
    patient1.addVisitRecord(new VisitRecord(LocalDateTime.now().minusYears(2), "Checkup", 36.5));
    List<Patient> inactivePatients = model.listInactivePatients();
    assertTrue("Should include patients inactive for over a year",
        inactivePatients.contains(patient1));
    assertFalse("Should not include recently active patients", inactivePatients.contains(patient2));
    
  }
  
  @Test
  public void testPatientVisitSummaryPastYear() {
    patient1.addVisitRecord(new VisitRecord(LocalDateTime.now().minusMonths(1), "Flu", 37.1));
    patient1.addVisitRecord(new VisitRecord(LocalDateTime.now().minusMonths(5), "Checkup", 36.6));
    Map<Patient, Long> visitSummary = model.patientVisitSummaryPastYear();
    assertEquals("Patient should have 2 visits this year", Long.valueOf(2), 
        visitSummary.get(patient1));
    assertEquals("Should not include patients with no visits this year", 
        Long.valueOf(0), visitSummary.get(patient2));
  }
  
  @Test
  public void testUnassignClinicalStaffFromPatient() {
    model.assignClinicalStaffToPatient(doctor1, patient1);
    model.unassignClinicalStaffFromPatient(doctor1, patient1);
    assertFalse("Staff should no longer be assigned to patient", 
        patient1.getAssignedClinicalStaff().contains(doctor1));
    
  }
  
  @Test
  public void testDeactivateStaff() {
    model.deactivateStaff(doctor1);
    assertFalse("Staff should be deactivated", doctor1.isActive());
    
  }
  
  @Test
  public void testListAllStaffAndPatientCounts() {
    model.assignClinicalStaffToPatient(doctor1, patient1);
    model.assignClinicalStaffToPatient(doctor1, patient2);
    Map<Staff, Integer> staffPatientCounts = model.listStaffPatientCounts();
    assertEquals("Staff1 should have 2 patients assigned",
        Integer.valueOf(2), staffPatientCounts.get(doctor1));
    assertEquals("Staff2 should have 0 patients assigned", 
        Integer.valueOf(0), staffPatientCounts.get(doctor2));  
  }
  
  @Test
  public void testClearRecords() {
    model.clearRecords();

    assertTrue("Patients list should be empty", model.getAllPatients().isEmpty());
    assertTrue("Staff list should be empty", model.getAllStaff().isEmpty());
    assertTrue("Rooms list should be empty", model.getAllRooms().isEmpty());
    
  }
  
  @Test
  public void testLoadClinicData() {
    model.loadClinicData("mock_file.txt");
    //assertTrue("Graphics should be updated after loading data", model.isGraphicsUpdated());
  }
  
}
