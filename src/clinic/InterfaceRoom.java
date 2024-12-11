package clinic;

import java.util.Set;

/**
 * This is the interface implementation for our room entity.
 * Gives us details about the room such as who is inside.
 * Is the room occupied? And to display occupancy status.
 */
public interface InterfaceRoom {
  /**
   * Shows the room details.
   * @return Room details.
   */
  String getRoomDetails();
  
  /**
   * Shows if a room is occupied. 
   * @return T/F occupied room.
   */
  boolean isOccupied();
  
  /**
   * Sets a room to occupied. 
   * @param occupied T/F.
   */
  void setOccupied(boolean occupied);
  
  /**
   * Displays the occupancy in a room.
   * @return Occupancy. 
   */
  String displayOccupancy();
  
  /**
   * Gets the X1 coordinate in room.
   * @return X1 coordinate.
   */
  public int getX1();
  
  /**
   * Gets the Y1 coordinate in room.
   * @return Y1 coordinate.
   */
  public int getY1();
  
  /**
   * Gets the X2 coordinate in room.
   * @return X2 coordinate.
   */
  public int getX2();
  
  /**
   * Gets the Y2 coordinate in room.
   * @return Y2 coordinate.
   */
  public int getY2();
  
  /**
   * Gets the type of room being used.
   * @return Room type.
   */
  public String getRoomType();
  
  /**
   * Gets the name of the room.
   * @return Room name.
   */
  public String getRoomName();
  
  /**
   * Gets the assigned patients in room.
   * @return Assigned patients to room.
   */
  public Set<Patient> getAssignedPatients();
  
  /**
   * Checks if given room is a waiting room.
   * @return True or false.
   */
  public boolean isWaitingRoom();
  
  /**
   * Assign patient helps us put a patient
   * into a specific room.
   * @param patient The patient who will be put in a room.
   */
  public void assignPatient(Patient patient);
  
  /**
   * Removing a patient.
   * 
   * @param patient Removes assigned patient from staff.
   */
  public void removePatient(Patient patient);
  
  /**
   * Get Room Info helps us determine what type of room this is,
   * who is assigned to the room, patient and staff.
   * @param model This is part of our ClinicalModel, this ties together components.
   * @return Here we return all of the information about the room.
   */
  public String getRoomInfo(InterfaceClinicModel model);
  
}
