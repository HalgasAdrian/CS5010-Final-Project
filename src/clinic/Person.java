package clinic;

/**
 * Person is a component of the Model in MVC.
 * A person connects to the ClinicalModel and 
 * to Patient.
 */

public abstract class Person {
  protected String firstName;
  protected String lastName;
  protected boolean isActive;
  
  /**
   * Every person has a first and last name.
   * @param firstName This is the first name.
   * @param lastName This is the last name.
   */
  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.isActive = true; // Defaulting to active when created
  }
  
  /**
   * Deactivates our person.
   */
  public void deactivate() {
    isActive = false;
  }
  
  /**
   * Activates the person.
   */
  public void activate() {
    isActive = true;
  }
  
  /**
   * Determines if someone is active or not.
   * @return Active status T/F.
   */
  public boolean isActive() {
    return isActive;
  }
  
  /**
   * Gets the persons full name.
   * @return Full name string.
   */
  public String getFullName() {
    return firstName + " " + lastName;
  }
  
  /**
   * Returns the persons first name.
   * @return First name string.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the persons last name.
   * @return Last name string.
   */
  public String getLastName() {
    return lastName;
  }
}
