package clinic;

import java.time.LocalDateTime;

/**
 * Interface setup for our visit record.
 */
public interface InterfaceVisitRecord {
  /**
   * Gets the visit time.
   * @return returns our visit time.
   */
  LocalDateTime getVisitTime();
  
  /**
   * Allows comments on patient health.
   * @return Chief complaint. 
   */
  String getChiefComplaint();
  
  /**
   * Documents patient body temp.
   * @return Returns body temperature.
   */
  double getBodyTemperature();
  
  /**
   * Keep track of visit status.
   * @return Is visit complete. 
   */
  boolean isVisitComplete();
  
  /**
   * Our toString method.
   * @return This returns the toString.
   */
  String toString();
}
