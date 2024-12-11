package clinic;

import java.util.HashSet;
import java.util.Set;

/**
 * This is the room class, it is part of the Model.
 * This helps us determine the size of the room, the type
 * of room, the name of the room, and who is assigned to the room.
 * 
 */
public class Room implements InterfaceRoom {
  private final int x1;
  private final int y1;
  private final int x2; 
  private final int y2;
  private String roomType;
  private String roomName;
  private Set<Patient> assignedPatients;
  
  /**
   * As Stated above, here we construct our room. This gives us
   * The dimensions, type of room and room name.
   * @param x1 Dimension for room
   * @param y1 Dimension for room
   * @param x2 Dimension for room
   * @param y2 Dimension for room
   * @param roomType Tells us which type of room it is
   * @param roomName Tells us the name of the room.
   */
  public Room(int x1, int y1, int x2, int y2, String roomType, String roomName) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.roomType = roomType;
    this.roomName = roomName;
    this.assignedPatients = new HashSet<>();
  }
  
  /**
   * Assign patient helps us put a patient
   * into a specific room.
   * @param patient The patient who will be put in a room.
   */
  @Override
  public void assignPatient(Patient patient) {
    if (!"waiting".equalsIgnoreCase(roomType)) {
      assignedPatients.clear();
    }
    if (patient != null) {
      Patient defensiveCopy = new Patient(
          new String(patient.getFirstName()), 
          new String(patient.getLastName()), 
          new String(patient.getDob())
      );
      assignedPatients.add(defensiveCopy);
    } 
  }
  
  /**
   * Removing a patient.
   * 
   * @param patient Removes assigned patient from staff.
   */
  @Override
  public void removePatient(Patient patient) {
    assignedPatients.remove(patient);
  }
  
  /**
   * Get Room Info helps us determine what type of room this is,
   * who is assigned to the room, patient and staff.
   * @param model This is part of our ClinicalModel, this ties together components.
   * @return Here we return all of the information about the room.
   */
  @Override
  public String getRoomInfo(InterfaceClinicModel model) {
    StringBuilder info = new StringBuilder();
    info.append("Room: ").append(roomName)
        .append(" (").append(roomType).append(")\n")
        .append("Patients and their details:\n");
    for (Patient patient : assignedPatients) {
      info.append(patient.getFullName()).append(" - ");
      InterfaceVisitRecord lastVisit = patient.getLatestVisit();
      String visitDetails = lastVisit != null ? "Complaint: " + lastVisit.getChiefComplaint() 
          + ", Temp: " + lastVisit.getBodyTemperature() + "°C" : "No recent visits";
      info.append(visitDetails).append("\n");
    }
    return info.toString();
  }
  
  /**
   * Checking if the room is a waiting room.
   * @return Checking if room is a waiting room.
   */
  @Override
  public boolean isWaitingRoom() {
    return "waiting".equalsIgnoreCase(roomType);
  }
  
  /**
   * Checking if a room is occupied.
   */
  public boolean isOccupied() {
    return !assignedPatients.isEmpty();
  }
  
  /**
   * Returns a copy of the assigned patients in the room to prevent 
   * external modification of internal set.
   * 
   * @return Assigned copy of patients to room.
   */
  @Override
  public Set<Patient> getAssignedPatients() {
    return new HashSet<>(assignedPatients);
  }
  
  /**
   * Room dimensions for x1.
   * @return Returns the dimensions for x1.
   */
  @Override
  public int getX1() {
    return x1;
  }
  
  /**
   * Room dimension for y1.
   */
  @Override
  public int getY1() {
    return y1;
  }
  
  /**
   * Room dimension for x2.
   * @return Room dimension for x2.
   */
  @Override
  public int getX2() {
    return x2;
  }
  
  /**
   * Room dimension for Y2.
   * @return room dimension for y2.
   */
  @Override
  public int getY2() {
    return y2;
  }
  
  /**
   * Gets our room type.
   * @return Room type.
   */
  @Override
  public String getRoomType() {
    return roomType;
  }
  
  /**
   * Gets our room name.
   * @return Returns room name. 
   */
  @Override
  public String getRoomName() {
    return roomName;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Room{")
      .append("x1=").append(x1)
      .append(", y1=").append(y1)
      .append(", x2=").append(x2)
      .append(", y2=").append(y2)
      .append(", roomType='").append(roomType)
      .append("', roomName='").append(roomName)
        .append("'}");
    return sb.toString();
  }

  @Override
  public String getRoomDetails() {
    return toString();
  }

  @Override
  public void setOccupied(boolean occupied) {
    if (!occupied) {
      assignedPatients.clear();
    }    
  }

  @Override
  public String displayOccupancy() {
    if (assignedPatients.isEmpty()) {
      return "Empty";
    } else {
      StringBuilder names = new StringBuilder();
      for (Patient patient : assignedPatients) {
        if (names.length() > 0) {
          names.append(", ");
        }
        names.append(patient.getFullName());
      }
      return names.toString();   
    }
  }
}
