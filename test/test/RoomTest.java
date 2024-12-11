package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import clinic.ClinicModel;
import clinic.ClinicalStaff;
import clinic.Patient;
import clinic.Room;
import org.junit.Before;
import org.junit.Test;

/**
 * Setting up our room test.
 * This should be able to tell us what the room is,
 * who is inside.
 */

public class RoomTest {
  private Room examRoom;
  private Room waitingRoom;
  private Patient patient1;
  private Patient patient2;
  private ClinicalStaff doctor1;
  
  /**
   * Creating our testing variables.
   */
  
  @Before
  public void setUp() {
    examRoom = new Room(0, 0, 5, 5, "exam", "Exam Room 1");
    waitingRoom = new Room(10, 0, 15, 5, "waiting", "Waiting Room 1");
    patient1 = new Patient("John", "Doe", "1985-05-15");
    patient2 = new Patient("Jane", "Doe", "1990-10-12");
    doctor1 = new ClinicalStaff("Dr. Alice", "Smith", "MD", "1234567890", "Doctor"); 
  }
  
  @Test
  public void testAssignPatientToExamRoom() {
    examRoom.assignPatient(patient1);
    assertTrue("Exam room should only have one patient",
        examRoom.getAssignedPatients().contains(patient1));
    assertEquals("Exam room should have exactly one patient", 1, 
        examRoom.getAssignedPatients().size());

    // Assigning another patient should replace the first one
    examRoom.assignPatient(patient2);
    assertTrue("Exam room should replace the patient", 
        examRoom.getAssignedPatients().contains(patient2));
    assertFalse("Exam room should not contain the first patient anymore", 
        examRoom.getAssignedPatients().contains(patient1));
  }
  
  @Test
  public void testssignPatientToWaitingRoom() {
    waitingRoom.assignPatient(patient1);
    waitingRoom.assignPatient(patient2);
    assertEquals("Waiting room should allow multiple patients", 
        2, waitingRoom.getAssignedPatients().size());
  }
  
  @Test
  public void testRemovePatient() {
    waitingRoom.assignPatient(patient1);
    waitingRoom.assignPatient(patient2);
    waitingRoom.removePatient(patient1);
    assertFalse("Patient1 should be removed from the "
        + "waiting room", waitingRoom.getAssignedPatients().contains(patient1));
    assertTrue("Patient2 should still be in the waiting room", 
        waitingRoom.getAssignedPatients().contains(patient2));  
  }
  
  @Test
  public void testIsWaitingRoom() {
    assertTrue("WaitingRoom should be identified as waiting room", waitingRoom.isWaitingRoom());
    assertFalse("ExamRoom should not be identified as waiting room", examRoom.isWaitingRoom());
  }
  
  @Test
  public void testIsOccupied() {
    assertFalse("Room should initially be unoccupied", examRoom.isOccupied());
    examRoom.assignPatient(patient1);
    assertTrue("Room should be occupied after patient assignment", examRoom.isOccupied());
  }
  
  @Test
  public void testGetX1() {
    assertEquals("getX1 should return the correct x1 coordinate", 0, examRoom.getX1());
  }
  
  @Test
  public void testGetY1() {
    assertEquals("getY1 should return the correct y1 coordinate", 0, examRoom.getY1());
  }
  
  @Test
  public void testGetX2() {
    assertEquals("getX2 should return the correct x2 coordinate", 5, examRoom.getX2());
  }
  
  @Test
  public void testGetY2() {
    assertEquals("getY2 should return the correct y2 coordinate", 5, examRoom.getY2());
  }
  
  @Test 
  public void testGetRoomType() {
    assertEquals("getRoomType should return the correct room type", "exam", 
        examRoom.getRoomType());    
  }
  
  @Test
  public void testGetRoomName() {
    assertEquals("getRoomName should return the correct room name", "Exam Room 1", 
        examRoom.getRoomName());
  }
  
  @Test
  public void testToString() {
    String expectedString = "Room{x1=0, y1=0, x2=5, y2=5, roomType='exam', roomName='Exam Room 1'}";
    assertEquals("toString should return a correctly formatted string representation of the room", 
        expectedString, examRoom.toString());
  }
}
