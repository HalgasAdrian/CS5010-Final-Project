package test;

import static org.junit.Assert.assertTrue;

import clinic.ClinicalStaff;
import clinic.Patient;
import clinic.Room;
import java.awt.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for CLinicGraphicalController functionality using the MockController.
 */
public class ClinicGraphicalControllerTest {
  private MockClinicModel mockModel;
  private MockClinicView mockView;
  private MockController mockController;

  @Before
  public void setUp() {
    mockModel = new MockClinicModel();
    mockView = new MockClinicView();
    mockController = new MockController(mockModel, mockView);
  }
  
  @Test
  public void testRegisterNewPatient() {
    mockController.registerNewPatient("John", "Doe", "1990-01-01");
    assertTrue("Log should contain: registerNewPatient Name: John Doe, DOB: 1990-01-01",
            mockController.getLog().contains("registerNewPatient Name: John Doe, DOB: 1990-01-01"));
  }
  
  @Test
  public void testRegisterNewClinicalStaff() {
    mockController.registerNewClinicalStaff("Alice", "Smith", "MD", "12345", "Doctor");
    assertTrue("Log should contain: Clinical staff registered: Alice Smith",
            mockView.getLog().contains("Clinical staff registered: Alice Smith"));
  }
  
  @Test
  public void testAssignRoom() {
    Patient patient = new Patient("Jane", "Doe", "1990-02-02");
    Room room = new Room(0, 0, 10, 10, "Waiting", "Waiting Room");
    mockModel.addPatient(patient);
    mockModel.addRoom(room);
    mockController.assignRoom(patient, room);

    assertTrue("Log should contain: assignRoom Patient: Jane Doe, Room: Waiting Room",
            mockController.getLog().contains("assignRoom Patient: Jane Doe, Room: Waiting Room"));
    
  }
  
  @Test
  public void testAssignStaff() {
    Patient patient = new Patient("John", "Doe", "1980-01-01");
    ClinicalStaff staff = new ClinicalStaff("Alice", "Smith", "MD", "123456", "Doctor");
    mockModel.addPatient(patient);
    mockModel.addStaff(staff);

    mockController.assignStaff(patient, staff);

    assertTrue("Log should contain: assignStaff Patient: John Doe, Staff: Dr. Alice Smith",
            mockController.getLog().contains("assignStaff Patient: John Doe, "
                + "Staff: Dr. Alice Smith"));
  }
  
  @Test
  public void testUnassignStaff() {
    Patient patient = new Patient("John", "Doe", "1980-01-01");
    ClinicalStaff staff = new ClinicalStaff("Alice", "Smith", "MD", "123456", "Doctor");
    mockModel.addPatient(patient);
    mockModel.addStaff(staff);

    mockController.unassignStaff(patient, staff);

    assertTrue("Log should contain: unassignStaff Patient: John Doe, Staff: Dr. Alice Smith",
            mockController.getLog().contains("unassignStaff Patient: John Doe, "
                + "Staff: Dr. Alice Smith"));
  }
  
  @Test
  public void testDisplayRoomInfo() {
    Room room = new Room(0, 0, 10, 10, "Waiting", "Waiting Room");
    mockModel.addRoom(room);

    mockController.displayRoomInfo(0);

    assertTrue("Log should contain: Waiting Room Info", mockView.getLog().contains("Room Info"));
  }
  
  @Test
  public void testDeactivateStaff() {
    ClinicalStaff staff = new ClinicalStaff("Alice", "Smith", "MD", "123456", "Doctor");
    mockModel.addStaff(staff);

    mockController.deactivateStaff("Alice", "Smith");

    assertTrue("Log should contain: deactivateStaff called with: Alice Smith",
            mockController.getLog().contains("deactivateStaff called with: Alice Smith"));
  }
  
  @Test
  public void testListInactivePatients() {
    mockController.listInactivePatients();

    assertTrue("Log should contain: listInactivePatients called",
            mockController.getLog().contains("listInactivePatients called"));
  }
  
  @Test
  public void testPatientVisitSummaryPastYear() {
    mockController.patientVisitSummaryPastYear();

    assertTrue("Log should contain: patientVisitSummaryPastYear",
            mockController.getLog().contains("patientVisitSummaryPastYear"));
  }
  
  @Test
  public void testListAllStaffAndPatientCounts() {
    mockController.listAllStaffAndPatientCounts();

    assertTrue("Log should contain: listAllStaffAndPatientCounts called",
            mockController.getLog().contains("listAllStaffAndPatientCounts called"));
  }
  
  @Test
  public void testLoadClinicData() {
    mockController.loadClinicData("sample_data.txt");

    assertTrue("Log should contain: loadClinicData called with: sample_data.txt",
            mockController.getLog().contains("loadClinicData called with: sample_data.txt"));
  }
  
  @Test
  public void testClearRecords() {
    mockController.clearRecords();

    assertTrue("Log should contain: clearRecords called",
            mockController.getLog().contains("clearRecords called"));
  }
  
  @Test
  public void testExecute() {
    mockController.execute();

    assertTrue("Log should contain: execute called",
            mockController.getLog().contains("execute called"));
  }
  
  @Test
  public void testActionPerformed() {
    mockController.actionPerformed(new ActionEvent(this, 
        ActionEvent.ACTION_PERFORMED, "Register Patient"));

    assertTrue("Log should contain: actionPerformed: Register Patient",
            mockController.getLog().contains("actionPerformed: Register Patient"));
  }
}
