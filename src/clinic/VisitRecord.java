package clinic;

import java.time.LocalDateTime;

/**
 * Setting up our visit record to keep track of patients.
 */
public class VisitRecord implements InterfaceVisitRecord {
  private LocalDateTime visitTime;
  private String chiefComplaint;
  private double bodyTemperature;
  private boolean visitComplete; 
  
  /**
   * Here is the constructor for our visit record.
   * @param visitTime Time which the patient visited.
   * @param chiefComplaint Note to keep on the patient.
   * @param bodyTemperature Patient body temperature in C.
   */
  public VisitRecord(LocalDateTime visitTime, String chiefComplaint, double bodyTemperature) {
    this.visitTime = visitTime;
    this.chiefComplaint = chiefComplaint;
    this.bodyTemperature = Math.round(bodyTemperature * 10.0) / 10.0; // Round to one decimal.  
    this.visitComplete = false; // Defaulting to false.
  }
  
  @Override
  public LocalDateTime getVisitTime() {
    return visitTime;
  }

  @Override
  public String getChiefComplaint() {
    return chiefComplaint;
  }

  @Override
  public double getBodyTemperature() {
    return bodyTemperature;
  }
  /**
   * Returns our toString.
   */
  
  public String toString() {
    return String.format("Visit on %s: Complaint: %s, Temperature: %.1f°C, Completed: %s", 
        visitTime, chiefComplaint, bodyTemperature, visitComplete ? "Yes" : "No");
  }

  @Override
  public boolean isVisitComplete() {
    return visitComplete;
  }
  /**
   * Returns if the visit is complete. 
   */
  
  public void setVisitComplete(boolean visitComplete) {
    this.visitComplete = visitComplete;
  }
}
