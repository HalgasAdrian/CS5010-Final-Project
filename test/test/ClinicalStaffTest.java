package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import clinic.ClinicalStaff;
import clinic.Patient;
import org.junit.Before;
import org.junit.Test;

/**
 *  The Clinical Staff Test is checking
 *  that every staff clinical staff member has a 
 *  education along with an NPI number.
 */

public class ClinicalStaffTest {
  private ClinicalStaff clinicalStaff;
  private Patient patient1;
  private Patient patient2;
  
  /**
   * Setting up our test case with a name, education and ID.
   */
  
  @Before 
  public void setUp() {
    // Assume ClinicalStaff extends Staff and accepts an education level in its constructor
    clinicalStaff = new ClinicalStaff("Alice", "Smith", "PhD", "1234567890", "Doctor");
    clinicalStaff = new ClinicalStaff("John", "Doe", "MD", "123456", "Doctor");
    patient1 = new Patient("Alice", "Smith", "1985-01-01");
    patient2 = new Patient("Bob", "Brown", "1987-02-02");
  }
  
  @Test
  public void testStaffInitialization() {
    assertEquals("Initial first name should be set", "John", clinicalStaff.getFirstName());
    assertEquals("Initial last name should be set", "Doe", clinicalStaff.getLastName());
    assertTrue("Staff should be active initially", clinicalStaff.isActive());
    assertEquals("Education level should be set", "MD", clinicalStaff.getEducationLevel());
    assertEquals("Doctor", clinicalStaff.getRole());
  }
  
  @Test
  public void testGetNpi() {
    assertEquals("Should return the correct NPI", "123456", clinicalStaff.getNpi());
  }

  @Test
  public void testToString() {
    String expectedToString = "ClinicalStaff{name='Dr. John Doe', isActive=true, "
        + "educationLevel='MD', npi='123456', role='Doctor'}";
    assertEquals(expectedToString, clinicalStaff.toString());
  }
  
  /**
   * Milestone 3 tests below.
   */

  @Test
  public void testAddPatient() {
    clinicalStaff.assignPatient(patient1);
    assertTrue(clinicalStaff.getAssignedPatients().contains(patient1));
  }
  
  @Test
  public void testRemovePatient() {
    clinicalStaff.assignPatient(patient1);
    clinicalStaff.unassignPatient(patient1);
    assertFalse(clinicalStaff.getAssignedPatients().contains(patient1));
    
  }
  
  @Test
  public void testTotalPatientsCount() {
    clinicalStaff.assignPatient(patient1);
    clinicalStaff.assignPatient(patient1);
    clinicalStaff.assignPatient(patient2);
    assertEquals(2, clinicalStaff.getTotalAssignedPatientsCount()); 
  }
  
}
