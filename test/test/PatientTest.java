package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import clinic.Patient;
import clinic.Room;
import clinic.VisitRecord;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * Setting up our patient test.
 * Each patient should have a name, DOB
 * and a room to be assigned to.
 */

public class PatientTest {
  private Patient patient;
  private Room room;
  
  /**
   * Setting up the patient with an example name
   * and a test room to assign them to.
   */
  
  @Before
  public void setUp() {
    patient = new Patient("John", "Doe", "1985-05-15");
    room = new Room(0, 0, 10, 10, "exam", "Exam Room 1");
  }
  
  @Test
  public void testPatientInitialization() {
    assertEquals("Should correctly set first name", "John", patient.getFirstName());
    assertEquals("Should correctly set last name", "Doe", patient.getLastName());
    assertEquals("Should correctly set date of birth", "1985-05-15", patient.getDob());
    assertNull("New patient should not have a room assigned", patient.getAssignedRoom());
    assertTrue("Patient should be active initially", patient.isActive());
  }
  
  @Test
  public void testAssignRoom() {
    patient.assignRoom(room);
    assertNotNull("Patient should have a room assigned after assignRoom", 
        patient.getAssignedRoom());
    assertEquals("Assigned room should match the room set", 
        room, patient.getAssignedRoom());
  }
  
  @Test
  public void testToString() {
    String expectedWithoutRoom = "Patient{assignedRoom=No room assigned, "
        + "dob='1985-05-15', fullName='John Doe', isActive=true}";
    assertEquals("toString should return correct format when no room is "
        + "assigned", expectedWithoutRoom, patient.toString());

    patient.assignRoom(room);
    String expectedWithRoom = "Patient{assignedRoom=Exam Room 1, dob='1985-05-15', "
        + "fullName='John Doe', isActive=true}";
    assertEquals("toString should return correct format when a room is assigned", 
        expectedWithRoom, patient.toString());
  }
  
  /**
   * Milestone 3 tests below. 
   */
  
  @Test
  public void testAddVisitRecord() {
    VisitRecord visit = new VisitRecord(LocalDateTime.now(), "Cough", 36.7);
    patient.addVisitRecord(visit);
    assertFalse("Patient should have visit records", patient.getVisitRecords().isEmpty());
    assertEquals("Check the visit record", visit, patient.getVisitRecords().get(0));
  }
  
  @Test
  public void testPatientActivityWithinYear() {
    VisitRecord visitOld = new VisitRecord(LocalDateTime.now().minusDays(400), "Old Flu", 36.5);
    VisitRecord visitRecent = new VisitRecord(LocalDateTime.now().minusDays(10), 
        "Recent Flu", 37.1);
    patient.addVisitRecord(visitOld);
    patient.addVisitRecord(visitRecent);
    
    //assertTrue("Patient should be active within the last year", patient.getVisitsLastYear());
  }
  
  @Test
  public void testPatientInactivityBeyondYear() {
    VisitRecord visitOld = new VisitRecord(LocalDateTime.now().minusDays(400), "Old Flu", 36.5);
    patient.addVisitRecord(visitOld);
    
    //assertFalse("Patient should not be active within the last year", patient.getVisitsLastYear());
  }
  
}
