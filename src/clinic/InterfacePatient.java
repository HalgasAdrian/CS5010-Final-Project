package clinic;

/**
 * Interface setup for our patient entity within the clinic.
 * Creates relationships between room and the patient. 
 */
public interface InterfacePatient extends InterfacePerson {
  /**
   * Gets the assigned room.
   * @return Returns our assigned room.
   */
  Room getAssignedRoom();
  
  /**
   * Assigns a room to a patient.
   * @param room Assigns room to patient.
   */
  void assignRoom(Room room);
  
  /**
   * Allows user to set name of patient.
   * @param name Patient name.
   */
  public void setName(String name);

}
