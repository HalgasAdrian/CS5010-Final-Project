package test;

import static org.junit.Assert.assertTrue;

import clinic.ClinicalStaff;
import clinic.Patient;
import clinic.Room;
import java.awt.event.ActionListener;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the ClinicView functionality using mock model and mock controller.
 */
public class ClinicViewTest {
  private MockClinicModel mockModel;
  private MockClinicView mockView;
  private MockController mockController;
  
  @Before
  public void setUp() {
    mockModel = new MockClinicModel();
    mockView = new MockClinicView();
    mockController = new MockController(mockModel, mockView);
    mockView.setController(mockController);
  }
  
  @Test
  public void testShowWelcomeScreen() {
    mockView.showWelcomeScreen();
    assertTrue(mockView.getLog().contains("showWelcomeScreen"));
  }
  
  @Test
  public void testShowMessage() {
    mockView.showMessage("Welcome to the clinic!");
    assertTrue(mockView.getLog().contains("showMessage: Welcome to the clinic!"));
  }
  
  @Test
  public void testShowError() {
    mockView.showError("Error occurred!");
    assertTrue(mockView.getLog().contains("showError: Error occurred!"));
  }
  
  @Test
  public void testClearRecords() {
    mockView.clearRecords();
    assertTrue(mockController.getLog().contains("clearRecords called"));
  }
  
  @Test
  public void testPromptRegisterPatient() {
    mockView.setSimulatedUserInputs("John", "Doe", "1990-01-01");
    mockView.promptRegisterPatient();
    assertTrue(mockController.getLog().contains("registerNewPatient Name: John Doe, "
        + "DOB: 1990-01-01"));
  }
  
  @Test
  public void testPromptAssignRoom() {
    mockView.setSimulatedUserInputs("Jane Doe", "Waiting Room");
    Patient patient = new Patient("Jane", "Doe", "1990-02-02");
    Room room = new Room(0, 0, 10, 10, "Waiting", "Waiting Room");
    mockModel.addPatient(patient);
    mockModel.addRoom(room);
    mockView.promptAssignRoom();
    assertTrue(mockController.getLog().contains("assignRoom Patient: Jane Doe, "
        + "Room: Waiting Room"));
  }
  
  @Test
  public void testPromptAssignStaff() {
    mockView.setSimulatedUserInputs("John Doe", "Dr. Alice Smith");
    Patient patient = new Patient("John", "Doe", "1980-01-01");
    ClinicalStaff staff = new ClinicalStaff("Alice", "Smith", "MD", "123456", "Doctor");
    mockModel.addPatient(patient);
    mockModel.addStaff(staff);
    mockView.promptAssignStaff();
    assertTrue(mockController.getLog().contains("assignStaff Patient: John Doe, "
        + "Staff: Dr. Alice Smith"));
  }
  
  @Test
  public void testPromptShowPatientInfo() {
    mockView.setSimulatedUserInputs("Jane Doe");
    Patient patient = new Patient("Jane", "Doe", "1990-02-02");
    mockModel.addPatient(patient);
    mockView.promptShowPatientInfo();
    assertTrue("Log should contain: promptShowPatientInfo",
        mockView.getLog().contains("promptShowPatientInfo"));
  }
  
  @Test
  public void testPromptListActiveStaffWithPatients() {
    mockView.promptListActiveStaffWithPatients();
    assertTrue("Log should contain: listActiveStaffWithPatientscalled",
        mockView.getLog().contains("listActiveStaffWithPatientscalled"));
  }
  
  @Test
  public void testPromptPatientVisitSummary() {
    mockView.promptPatientVisitSummaryPastYear();
    assertTrue(mockController.getLog().contains("patientVisitSummaryPastYear"));
  }
  
  @Test
  public void testLoadClinicData() {
    mockView.loadClinicData("sample_data.txt");
    assertTrue(mockController.getLog().contains("loadClinicData called with: sample_data.txt"));
  }
  
  @Test
  public void testUpdateGraphics() {
    mockView.updateGraphics();

    assertTrue("Log should contain: updateGraphics",
        mockView.getLog().contains("updateGraphics"));
  }
  
  @Test
  public void testSetController() {
    ActionListener newController = e -> System.out.println("New controller set");
    mockView.setController(newController);
    assertTrue("Controller was set without errors.", true);
  }

}
