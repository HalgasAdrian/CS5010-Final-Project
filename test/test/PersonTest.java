package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import clinic.ClinicalStaff;
import clinic.Patient;
import org.junit.Before;
import org.junit.Test;

/**
 * PersonTest is testing to make sure our person 
 * component in the model is working properly.
 */

public class PersonTest {
  private Patient patient;
  private ClinicalStaff clinicalStaff;
  
  
  @Before
  public void setUp() {
    patient = new Patient("John", "Doe", "1985-05-15");
    clinicalStaff = new ClinicalStaff("Dr. Alice", "Smith", "MD", "1234567890", "Doctor");
  }
  
  @Test
  public void testPatientInitialization() {
    assertEquals("Initial first name should be set for patient", "John", patient.getFirstName());
    assertEquals("Initial last name should be set for patient", "Doe", patient.getLastName());
    assertTrue("Patient should be active initially", patient.isActive());
  }
  
  @Test
  public void testClinicalStaffInitialization() {
    assertEquals("Initial first name should be set for staff", 
        "Dr. Alice", clinicalStaff.getFirstName());
    assertEquals("Initial last name should be set for staff", 
        "Smith", clinicalStaff.getLastName());
    assertTrue("Clinical staff should be active initially", clinicalStaff.isActive());
  }
  
  @Test
  public void testActivateDeactivatePatient() {
    patient.deactivate();
    assertFalse("Patient should be deactivated", patient.isActive());

    patient.activate();
    assertTrue("Patient should be reactivated", patient.isActive());
  }
  
  @Test
  public void testActivateDeactivateClinicalStaff() {
    clinicalStaff.deactivate();
    assertFalse("Clinical staff should be deactivated", clinicalStaff.isActive());

    clinicalStaff.activate();
    assertTrue("Clinical staff should be reactivated", clinicalStaff.isActive());
  }

  
  @Test
  public void testGetfullNamePatient() {
    assertEquals("Full name should be correctly formatted for patient", 
        "John Doe", patient.getFullName());
  }
  
  @Test 
  public void testGetFullNameClinicalStaff() {
    assertEquals("Full name should be correctly formatted for clinical staff", 
        "Dr. Alice Smith", clinicalStaff.getFullName());
  }

}

