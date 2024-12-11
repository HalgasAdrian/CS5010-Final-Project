package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import clinic.ClinicalStaff;
import org.junit.Before;
import org.junit.Test;

/**
 * The StaffTest class should help us verify that everything related.
 * to staff works correctly, every staff member has a education level.
 */

public class StaffTest {
  private ClinicalStaff clinicalStaff;
  
  /**
   * Setting up our test clinical staff member.
   * They have an education along with an NPI.
   */
  
  @Before 
  public void setUp() {
    // Assume ClinicalStaff extends Staff and accepts an education level in its constructor
    clinicalStaff = new ClinicalStaff("Alice", "Smith", "PhD", "1234567890", "Doctor");
  }
  
  @Test
  public void testStaffInitialization() {
    assertEquals("Initial first name should be set", "Alice", clinicalStaff.getFirstName());
    assertEquals("Initial last name should be set", "Smith", clinicalStaff.getLastName());
    assertTrue("Staff should be active initially", clinicalStaff.isActive());
    assertEquals("Education level should be set", "PhD", clinicalStaff.getEducationLevel());
    assertEquals("Doctor", clinicalStaff.getRole());

  }

  @Test
  public void testToString() {
    String expectedToString = "ClinicalStaff{name='Dr. Alice Smith', isActive=true, "
        + "educationLevel='PhD', npi='1234567890', role='Doctor'}";
    assertEquals(expectedToString, clinicalStaff.toString());
  }
}
