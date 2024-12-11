package clinic;

/**
 *  NonClinicalStaff extends staff, these are non doctors who
 *  are working in the clinic, they have a cprLevel and other
 *  basic structures that People and Staff have like name and education.
 *  This is part of the Model.
 */
public class NonClinicalStaff extends Staff implements InterfaceNonClinicalStaff {
  private String cprLevel;
  private String role;
  
  /**
   *  As stated above, here are non clinical staff. 
   * @param firstName Non clinical staff first name.
   * @param lastName Non clinical staff last name.
   * @param educationLevel non clinical education level.
   * @param cprLevel Non Clinical CPR level.
   * @param role Role of the non-clinical staff member.
   */
  public NonClinicalStaff(String firstName, String lastName, 
      String educationLevel, String cprLevel, String role) {
    super(firstName, lastName, educationLevel);
    this.cprLevel = cprLevel;
    this.role = role != null ? new String(role) : null;
  }
  
  /**
   * Get CPR level.
   * @return this returns our CPR level.
   */
  public String getCprLevel() {
    return cprLevel;
  }
  
  /**
   * Returns the role of the staff member.
   * @return Role of the staff member.
   */
  public String getRole() {
    return role;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("NonClinicalStaff{firstName='").append(firstName)
      .append("', lastName='").append(lastName)
      .append("', isActive=").append(isActive)
      .append(", educationLevel='").append(educationLevel)
      .append("', cprLevel='").append(cprLevel)
      .append("', role='").append(role)
      .append("'}");
    return sb.toString();
  }

  @Override
  public String getName() {
    StringBuilder sb = new StringBuilder();
    sb.append("Nurse ").append(firstName).append(" ").append(lastName);
    return sb.toString();
  }

  @Override
  public String getDepartment() {
    return "Nurse Aide".equalsIgnoreCase(role) ? "Nursing" : "Administration";
  }

  @Override
  public String getStaffDetails() {
    StringBuilder sb = new StringBuilder();
    sb.append("Non-Clinical Staff: ").append(getName())
      .append(", Education Level: ").append(educationLevel)
      .append(", CPR Level: ").append(cprLevel);
    return sb.toString();
  } 
  
}
