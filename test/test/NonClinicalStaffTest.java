package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import clinic.NonClinicalStaff;
import org.junit.Before;
import org.junit.Test;

/**
 * The NonClinicalStaff test helps us make sure that
 *  the non clinical component of our staff has its CPR rating
 *  as well as contains a name and degree as all employees should.
 */

public class NonClinicalStaffTest {
  private NonClinicalStaff nonClinicalStaff;
  
  @Before
  public void setUp() {
    nonClinicalStaff = new NonClinicalStaff("Alice", "Smith", "Masters", "BLS", "Nurse");
  }
  
  @Test
  public void testNonClinicalStaffInitialization() {
    assertEquals("Should correctly set first name", "Alice", nonClinicalStaff.getFirstName());
    assertEquals("Should correctly set last name", "Smith", nonClinicalStaff.getLastName());
    assertEquals("Should correctly set education level", 
        "Masters", nonClinicalStaff.getEducationLevel());
    assertEquals("Should correctly set CPR level", "BLS", nonClinicalStaff.getCprLevel());
    assertTrue("NonClinical staff should be active initially", nonClinicalStaff.isActive());
    assertEquals("Nurse", nonClinicalStaff.getRole());

  }
  
  @Test
  public void testGetCprLevel() {
    assertEquals("Should return the correct CPR level", "BLS", nonClinicalStaff.getCprLevel());
  }
  
  @Test 
  public void testToString() {
    String expected = "NonClinicalStaff{" 
         + "firstName='Alice', lastName='Smith', isActive=true, "
         + "educationLevel='Masters', cprLevel='BLS', role='Nurse'}";
    assertEquals("toString should return a correctly formatted "
        + "string representation of the nonclinical staff",
                 expected, nonClinicalStaff.toString());
  } 
}