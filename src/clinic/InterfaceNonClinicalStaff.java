package clinic;

/**
 * Interface for Non-Clinical Staff.
 * Focused on properties and behaviors relevant to non-clinical staff.
 */
public interface InterfaceNonClinicalStaff extends InterfacePerson {
  
  /**
   * Get the CPR level of the staff member.
   * @return CPR level as a string.
   */
  String getCprLevel();
  
  /**
   * Get the role of the staff member.
   * @return Role as a string.
   */
  String getRole();

  /**
   * Get the department of the staff member.
   * @return Department as a string.
   */
  String getDepartment();

  /**
   * Get details about the staff member.
   * @return Staff details as a string.
   */
  String getStaffDetails();

}
