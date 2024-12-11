package clinic;

/**
 *  Staff extends person in our Model.
 */

public abstract class Staff extends Person {
  protected String educationLevel;
  
  /**
   * Every Staff member has a name and education Level.
   * @param firstName This is the first name.
   * @param lastName This is the last name.
   * @param educationLevel This is the education level.
   */
  public Staff(String firstName, String lastName, String educationLevel) {
    super(firstName, lastName);
    this.educationLevel = educationLevel;
  }
  
  /**
   * Returns the education level of our staff.
   * @return Staff education level.
   */
  public String getEducationLevel() {
    return educationLevel;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Staff{")
      .append("firstName='").append(firstName).append('\'')
      .append(", lastName='").append(lastName).append('\'')
      .append(", isActive=").append(isActive)
      .append(", educationLevel='").append(educationLevel).append('\'')
        .append('}');
    return sb.toString();
  }
}
