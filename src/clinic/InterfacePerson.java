package clinic;

/**
 * Interface implementation for our person.
 */
public interface InterfacePerson {
  /**
   * Returns the persons name.
   * @return Full name of person.
   */
  String getName();
  
  /**
   * Activates the person.
   */
  void activate();
  
  /**
   * Deactivates the person.
   */
  void deactivate();
  
  /**
   * Checks if the person is active or not.
   * @return Returns true or false. 
   */
  boolean isActive();
}
